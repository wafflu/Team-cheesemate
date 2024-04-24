package team.cheese.Controller.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import team.cheese.Domain.Post.PostDto;
import team.cheese.dao.Post.PostDao;
import team.cheese.service.Post.PostService;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/communityHome", method = RequestMethod.GET)
    public String PostHome(PostDto postDto, Model m)throws Exception{
        List<PostDto> list = postService.selectAll();
        m.addAttribute("list",list);
        return "/PostHome";
    }

}
