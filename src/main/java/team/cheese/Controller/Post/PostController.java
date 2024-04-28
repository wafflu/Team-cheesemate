package team.cheese.Controller.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<PostDto> getTopTen = postService.getTopTen();
        List<PostDto> list = postService.selectAll();

        m.addAttribute("getTopTen",getTopTen);
        m.addAttribute("list",list);
        return "/CommunityHome";
    }

    @RequestMapping(value="/communityList",method = RequestMethod.GET)
    public String PostList(PostDto postDto, Model m)throws Exception{
        List<PostDto> list = postService.selectAll();

        m.addAttribute("list",list);
        return "/CommunityList";
    }


    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String Post()throws Exception{
        //만약 user123이라면
        //만약 아이디가 없다면
        return "/Post";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String Write(PostDto postDto, Model m)throws Exception{

        postDto.setur_id("user123");
        postDto.setaddr_cd("11010720");
        postDto.setNo(2);
        postDto.setaddr_name("서울특별시 종로구 사직동");
        postDto.setNick("skyLee");


        String title = postDto.getTitle();
        String contents = postDto.getContents();
        String commu_cd = postDto.getcommu_cd();




        postDto.setcommu_cd(commu_cd);
        postDto.setTitle(title);
        postDto.setContents(contents);

        System.out.println("연결1");


        postService.write(postDto);
        System.out.println("연결2");
        m.addAttribute("postDto",postDto);

        return "/Post";

    }

    @RequestMapping(value="/read", method = RequestMethod.GET)
    public String read(Integer sn,Model m)throws Exception{

        PostDto postDto = postService.read(sn);
        m.addAttribute("postDto",postDto);
        return "/Post";
    }

    @RequestMapping(value = "/modify" ,method = RequestMethod.POST)
    public String update(PostDto postDto, Model m)throws Exception{
       postService.modify(postDto);

        System.out.println(postDto.toString());
        m.addAttribute("postDto",postDto);
        return "/Post";
    }

    @RequestMapping(value="/remove", method = RequestMethod.GET)
    public String remove(Integer sn,Model m,PostDto postDto)throws Exception{

        postService.remove(sn);
        m.addAttribute("postDto",postDto);
        return "/Post";
    }

}
