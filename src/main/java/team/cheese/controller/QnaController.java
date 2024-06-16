package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String qnaForm(HttpSession session, Model model) {
        // 세션에서 userId를 확인하여 모델에 추가
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        }
        model.addAttribute("userId", userId);
        return "qnaForm"; // qnaForm.jsp로 이동
    }

    // faq에서 버튼 클릭시 로그인체크
    @GetMapping("/checkLogin")
    @ResponseBody
    public Map<String, Boolean> checkLogin(HttpSession session) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("loggedIn", session.getAttribute("userId") != null);
        return response;
    }

    // 대분류 목록 불러오기
    @GetMapping("/major")
    public ResponseEntity<List<QnaCategoryDto>> getMajorCategories()  throws Exception{
        // 대분류 목록을 서비스에서 가져온다.
        List<QnaCategoryDto> categories = qnaService.getMajorCategories();
        return ResponseEntity.ok(categories);
    }

    // 선택된 대분류에 따른 상세 유형 목록 불러오기
    @GetMapping("/sub/{majorId}")
    public ResponseEntity<List<QnaCategoryDto>> getSubCategories(@PathVariable long majorId) throws Exception {
        // 대분류 ID에 따른 상세 유형 목록을 가져온다
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
    @ResponseBody
    public Map<String, Object> write(@Valid @ModelAttribute QnaDto qnaDto, BindingResult result, HttpSession session) throws Exception {
        Map<String, Object> response = new HashMap<>();

        // 유효성 검사 실패 시 오류 메시지를 반환
        if (result.hasErrors()) {
            response.put("success", false);
            response.put("message", "유효성 검사에 실패했습니다.");
            return response;
        }

        // 세션에서 사용자 ID를 가져와서 Dto에 설정
        String userId = (String) session.getAttribute("userId");
        qnaDto.setUr_id(userId);
        qnaService.write(qnaDto);

        response.put("success", true);
        response.put("message", "문의글이 등록되었습니다.");
        return response;
    }
    // 예외 처리 핸들러
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, RedirectAttributes redirectAttributes)  throws Exception{
        redirectAttributes.addFlashAttribute("errorMessage", "양식을 다시 작성해주세요. 오류: " + ex.getMessage());
        return "redirect:/qna/new"; // 예외 발생 시 에러페이지로 이동
    }
    // 나의 문의 내역 조회하기
    @GetMapping("/list")
    public String listUserQnas(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, Model model, HttpSession session) throws Exception {
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
    public String read(@RequestParam("no") long no, Model model) throws Exception {
        QnaDto qnaDto = qnaService.read(no);
        model.addAttribute("qna", qnaDto);
        return "qnaBoard";
    }

    /*
    나의 문의 내역 삭제하기
        qnaBoard페이지에서 삭제 버튼을 누른다.
            alert으로 삭제 여부를 묻는다.
            확인을 누르면 삭제된다.
            삭제 완료 후 /list로 이동한다.
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam("no") long no, HttpSession session) throws Exception {
        Map<String, Object> response = new HashMap<>();

        // 세션에서 사용자 ID를 가져와서 삭제 요청
        String ur_id = (String) session.getAttribute("userId");
        int result = qnaService.remove(no, ur_id);
        if (result == 1) {
            response.put("success", true);
            response.put("message", "삭제 완료하였습니다.");
        } else {
            response.put("success", false);
            response.put("message", "삭제에 실패했습니다.");
        }
        return response;
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
    @ResponseBody
    public Map<String, Object> modify(@ModelAttribute QnaDto qnaDto, BindingResult result, HttpSession session) throws Exception {
        Map<String, Object> response = new HashMap<>();
        String ur_id = (String) session.getAttribute("userId");
        qnaDto.setUr_id(ur_id);
        qnaDto.setLast_id(ur_id);

//        if (result.hasErrors()) {
//            response.put("success", false);
//            response.put("message", "유효성 검사에 실패했습니다.");
//            return response;
//        }

        int rowCnt = qnaService.modify(qnaDto);
        if (rowCnt == 1) {
            response.put("success", true);
            response.put("message", "수정 완료하였습니다.");
            response.put("qnaNo", qnaDto.getNo());
        } else {
            response.put("success", false);
            response.put("message", "수정에 실패했습니다.");
        }
        return response;
    }
}

