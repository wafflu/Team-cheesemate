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



    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String Post()throws Exception{
        //만약 user123이라면
        //만약 아이디가 없다면
        return "/Post";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String Post(PostDto postDto, Model m)throws Exception{

        postDto.setur_id("user123");
        postDto.setaddr_cd("11010720");
        postDto.setNo(2);
        postDto.setcommu_cd("commu_B");
        postDto.setaddr_name("서울특별시 종로구 사직동");
        postDto.setNick("skyLee");

        postService.write(postDto);
        m.addAttribute("postDto",postDto);
        return "/Post";

    }


}
