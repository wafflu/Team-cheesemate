package team.cheese.controller.MyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.MyPage.CommentPageHandler;
import team.cheese.domain.MyPage.ReviewCommentDTO;
import team.cheese.service.MyPage.ReviewCommentService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReviewCommentController {

    @Autowired
    ReviewCommentService rvService;

    // 후기글 수정
    @PatchMapping("comments/{no}")
    public ResponseEntity<String> modify(@PathVariable Integer no, @RequestBody ReviewCommentDTO reviewCommentDTO,HttpSession session) throws Exception {
        reviewCommentDTO.setNo(no);
        // 세션객체에서 buy_id 값을 가져온다
        //      String buy_id = (String)session.getAttribute("id");
        String buy_id = "1";
        reviewCommentDTO.setBuy_id(buy_id);

        rvService.modify(reviewCommentDTO);
        return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

    }
    // 후기글 작성
    @PostMapping("/comments")
    public ResponseEntity<String> write(@RequestBody ReviewCommentDTO reviewCommentDTO, HttpSession session) throws Exception{
        // 1. 세션 객체에서 buy_id값 받아오기
        //        String buy_id = (String)session.getAttribute("id");
        // 1. 세션 객체에서 buy_nick값 받아오기
        //        String buy_nick = (String)session.getAttribute("nick");
        String buy_id = "1";
        String buy_nick = "1";
        reviewCommentDTO.setBuy_id(buy_id);
        reviewCommentDTO.setBuy_nick(buy_nick);

        rvService.write(reviewCommentDTO);
        return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
    }
    // 후기글 삭제
    @DeleteMapping("/comments/{no}")
    public ResponseEntity<String> remove(@PathVariable Integer no, String sal_id, HttpSession session) throws Exception{
        // String buy_id = (String)session.getAttribute("id");
        String buy_id = "1";

        rvService.remove(sal_id,buy_id,no);
        return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
    }
    // 후기글 목록 가져오기
    @GetMapping("/comments")
    public ResponseEntity<Map<String, Object>> list(String sal_id, @RequestParam(defaultValue ="1") Integer page, @RequestParam(defaultValue ="5") Integer pageSize) throws Exception {
        List<ReviewCommentDTO> list = rvService.getPage(sal_id, page, pageSize);

        // 사용자의 후기글 갯수 가져오기 & 페이징핸들러 객체생성
        int totalCnt = rvService.getCount(sal_id);
        CommentPageHandler ph = new CommentPageHandler(totalCnt, page, pageSize);

        // 후기글 목록과 페이징 핸들러 객체를 모델에 담기
        Map<String, Object> response = new HashMap<>();
        response.put("comments", list);
        response.put("ph", ph);

        // ResponseEntity객체에 Map을 담아서 반환
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
