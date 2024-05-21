package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team.cheese.domain.QnaCategoryDto;
import team.cheese.domain.QnaDto;
import team.cheese.entity.PageHandler;
import team.cheese.service.QnaService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    private QnaService qnaService;

    @GetMapping("/new")
    public String qnaForm() {
        return "qnaForm";
    }

    // 대분류 목록 불러오기
    @GetMapping("/major")
    public ResponseEntity<List<QnaCategoryDto>> getMajorCategories() {
        List<QnaCategoryDto> categories = qnaService.getMajorCategories();
        return ResponseEntity.ok(categories);
    }

    // 선택된 대분류에 따른 상세 유형 목록 불러오기
    @GetMapping("/sub/{majorId}")
    public ResponseEntity<List<QnaCategoryDto>> getSubCategories(@PathVariable long majorId) {
        List<QnaCategoryDto> subCategories = qnaService.getSubCategories(majorId);
        return ResponseEntity.ok(subCategories);
    }

    /*
    1:1 문의 등록하기 (insert)
        1:1 문의하기에서 작성된 양식을 받는다.
        로그인 되어있는 아이디를 확인한다.
        작성된 데이터와 필요한 데이터를 Dto에 담는다.
        서비스에 write() 메서드를 호출하여 db에 저장한다.
        완료되면 나의 문의목록으로 페이지를 이동시킨다.
     */
    @PostMapping("/send")
    public String write(@Valid @ModelAttribute QnaDto qnaDto, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        // 유효성 검사 실패 시 "QnaForm"로 리다이렉트
        if (result.hasErrors()) {
            System.out.println("유효성검사에 걸림");
            System.out.println("========");
            System.out.println(qnaDto.toString());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.qnaDto", result);
            redirectAttributes.addFlashAttribute("qnaDto", qnaDto);
            return "redirect:/qna/new";
        }
        qnaService.write(qnaDto);
        // 성공 페이지로 리다이렉트
        return "redirect:/qna/list";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, RedirectAttributes redirectAttributes) {
        // 예외 발생 시 에러 메시지를 플래시 속성에 추가
        redirectAttributes.addFlashAttribute("errorMessage", "양식을 다시 작성해주세요. 오류: " + ex.getMessage());
        return "redirect:/qna/new"; // 예외 발생 시 리다이렉트할 페이지
    }

    /*
    나의 문의 내역 조회하기
        로그인 여부를 세션으로 확인한다.
            로그인 되어 있지 않은 경우 로그인 페이지로 리다이렉트한다.
            로그인 되어 있는 경우 문의 내역을 조회한다.
        로그인 사용자의 아이디를 세션에서 가져온다.
        문의 내역을 조회하는 서비스의 메서드를 호출하여 결과를 리스트에 담는다.
        조회된 문의 내역을 모델에 담아 뷰에 전달한다.
        에러 발생 시 에러 페이지로 리다이렉트한다.
     */
    @GetMapping("/list")
    public String listUserQnas(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, Model model, HttpSession session) {
        String ur_id = (String) session.getAttribute("userId");

        int totalCnt = qnaService.countQnasByUserId(ur_id);
        PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("ur_id", ur_id);
        map.put("offset", pageHandler.getOffset());
        map.put("pageSize", pageSize);
        List<QnaDto> myQnaList = qnaService.selectPageByUserId(map);

        model.addAttribute("qnaList", myQnaList);
        model.addAttribute("ph", pageHandler);
        return "qnaBoardList";
    }

    /*
    나의 문의 내역 읽기(read)
        로그인 체크
        목록에서 내가 쓴 문의글을 클릭하면 qnaBoard페이지로 이동
            제목, 최종 수정일시, 카테고리, 내용이 표출된다.
            답변이 존재할 경우 답변일시 답변 내용이 표출된다.
     */
    @GetMapping("/read")
    public String read(@RequestParam("no") long no, Model model) {
        QnaDto qnaDto = qnaService.read(no);
        model.addAttribute("qna", qnaDto);
        return "qnaBoard"; // 문의글 상세 정보를 보여줄 JSP 페이지
    }

    /*
    나의 문의 내역 삭제하기
        qnaBoard페이지에서 삭제 버튼을 누른다.
            alert으로 삭제 여부를 묻는다.
            확인을 누르면 삭제된다.
            삭제 완료 후 /list로 이동한다.
     */
    @PostMapping("/delete")
    public String deleteQna(@RequestParam("no") long no, HttpSession session) {
        String ur_id = (String) session.getAttribute("userId");
        qnaService.remove(no, ur_id);
        return "redirect:/qna/list";
    }

    /*
    나의 문의 내역 수정하기
        로그인 체크
        qnaBoard페이지에서 수정 버튼을 누른다.
        readonly가 false된다.
        해당 제목과 내용을 수정한다.
        저장 버튼을 클릭 시 업데이트가 된다.
        현재 게시글 read 상태로 이동한다.
     */
    @PostMapping("/modify")
    public String modify(QnaDto qnaDto, BindingResult result, HttpSession session, RedirectAttributes rattr) {
        String ur_id = (String) session.getAttribute("userId");
        qnaDto.setUr_id(ur_id);
        qnaDto.setLast_id(ur_id);

        int rowCnt = qnaService.modify(qnaDto);
        if (rowCnt == 1) {
            rattr.addFlashAttribute("msg", "수정 완료하였습니다.");
            return "redirect:/qna/read?no=" + qnaDto.getNo(); // 수정이 성공하면 다시 게시글 조회 페이지로 리다이렉트
        }
        return "redirect:/qna/read?no=" + qnaDto.getNo();
    }
}
