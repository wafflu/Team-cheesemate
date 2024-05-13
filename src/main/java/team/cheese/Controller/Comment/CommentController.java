package team.cheese.Controller.Comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import team.cheese.Domain.Comment.CommentDto;
import team.cheese.service.Comment.CommentService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/community")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comments" , method = RequestMethod.POST)
    public ResponseEntity<String> write(@RequestBody CommentDto commentDto, HttpSession session) throws Exception {
        //1.세션 객체에서 ur_id 갖고오기
        //2.세션 객체에서 nick 갖고오기

        commentService.write(commentDto);
        return null;
    }

}
