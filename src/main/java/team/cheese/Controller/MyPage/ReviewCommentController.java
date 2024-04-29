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

    @PatchMapping("comments/like/{no}")
    public ResponseEntity<String> changeLikeCnt(@PathVariable Integer no, HttpSession session) {
//      String buy_id = (String)session.getAttribute("id");
        try {
            int rowCnt = rvService.ClickedLike(no);

            if (rowCnt != 1)
                throw new Exception("Modify failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("comments/{no}")
    public ResponseEntity<String> modify(@PathVariable Integer no, @RequestBody ReviewCommentDTO reviewCommentDTO,HttpSession session) {
        reviewCommentDTO.setNo(no);
//      String buy_id = (String)session.getAttribute("id");
        String buy_id = "1";
        reviewCommentDTO.setBuy_id(buy_id);
        System.out.println(reviewCommentDTO);
        try {
            int rowCnt = rvService.modify(reviewCommentDTO);

            if (rowCnt != 1)
                throw new Exception("Modify failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/comments")
    public ResponseEntity<String> write(@RequestBody ReviewCommentDTO reviewCommentDTO, HttpSession session) {
//        String buy_id = (String)session.getAttribute("id");
        String buy_id = "1";
        reviewCommentDTO.setBuy_id(buy_id);
        System.out.println(reviewCommentDTO);
        try {
            int rowCnt = rvService.write(reviewCommentDTO);

            if (rowCnt != 1)
                throw new Exception("Write Failed");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/comments/{no}")
    public ResponseEntity<String> remove(@PathVariable Integer no, String sal_id, HttpSession session) {
//        String buy_id = (String)session.getAttribute("id");
        String buy_id = "1";
        try {
            int rowCnt = rvService.remove(sal_id,buy_id,no);

            if (rowCnt != 1)
                throw new Exception("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<List<ReviewCommentDTO>> list(String sal_id) {
        List<ReviewCommentDTO> list = null;
        try {
            System.out.println(sal_id);
            list = rvService.getList(sal_id);
            System.out.println(list);
            return new ResponseEntity<List<ReviewCommentDTO>>(list, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<ReviewCommentDTO>>(list, HttpStatus.BAD_REQUEST);
        }
    }
}
