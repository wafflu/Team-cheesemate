package team.cheese.Controller.MyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.cheese.Domain.MyPage.ReviewCommentDTO;
import team.cheese.Service.MyPage.ReviewCommentService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ReviewCommentController {

    @Autowired
    ReviewCommentService rvService;

//    @PatchMapping("comments/like/{no}")
//    public ResponseEntity<String> changeLikeCnt(@PathVariable Integer no, HttpSession session) {
////      String buy_id = (String)session.getAttribute("id");
//        if(!rvService.clickedLike(no))
//            return new ResponseEntity<>("MOD_ERR", HttpStatus.INTERNAL_SERVER_ERROR);
//
//        return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
//    }

    @PatchMapping("comments/{no}")
    public ResponseEntity<String> modify(@PathVariable Integer no, @RequestBody ReviewCommentDTO reviewCommentDTO,HttpSession session) {
        reviewCommentDTO.setNo(no);
        // 세션객체에서 buy_id 값을 가져온다
        //      String buy_id = (String)session.getAttribute("id");
        String buy_id = "1";
        reviewCommentDTO.setBuy_id(buy_id);

        if(!rvService.modify(reviewCommentDTO))
            return new ResponseEntity<>("MOD_ERR", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

    }
    @PostMapping("/comments")
    public ResponseEntity<String> write(@RequestBody ReviewCommentDTO reviewCommentDTO, HttpSession session) {
        // 1. 세션 객체에서 buy_id값 받아오기
        //        String buy_id = (String)session.getAttribute("id");
        // 1. 세션 객체에서 buy_nick값 받아오기
        //        String buy_nick = (String)session.getAttribute("nick");
        String buy_id = "1";
        String buy_nick = "1";
        reviewCommentDTO.setBuy_id(buy_id);
        reviewCommentDTO.setBuy_nick(buy_nick);
        try {
            if(!rvService.write(reviewCommentDTO))
                throw new Exception("Write Failed");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("WRT_ERR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/comments/{no}")
    public ResponseEntity<String> remove(@PathVariable Integer no, String sal_id, HttpSession session){
//        String buy_id = (String)session.getAttribute("id");
        String buy_id = "1";
        try {
            if(!rvService.remove(sal_id,buy_id,no))
                throw new Exception("Remove Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<List<ReviewCommentDTO>> list(String sal_id) {
        List<ReviewCommentDTO> list = null;
        try {
            list = rvService.getList(sal_id);
            return new ResponseEntity<List<ReviewCommentDTO>>(list, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<ReviewCommentDTO>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
