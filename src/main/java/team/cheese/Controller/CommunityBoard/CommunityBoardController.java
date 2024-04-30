package team.cheese.Controller.CommunityBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.service.CommunityBoard.CommunityBoardService;


import java.util.List;

@Controller
//@RequestMapping(value = "/community")
public class CommunityBoardController {
    @Autowired
    CommunityBoardService communityBoardService;

    @RequestMapping(value = "/communityHome", method = RequestMethod.GET)
    public String communityBoardHome(CommunityBoardDto communityBoardDto, Model m)throws Exception{
        List<CommunityBoardDto> getTopTen = communityBoardService.getTopTen();
        List<CommunityBoardDto> list = communityBoardService.selectAll();

        m.addAttribute("getTopTen",getTopTen);
        m.addAttribute("list",list);
        return "/CommunityHome";
    }

    @RequestMapping(value="/communityList",method = RequestMethod.GET)
    public String communityBoardList(CommunityBoardDto communityBoardDto, Model m )throws Exception{

        List<CommunityBoardDto> list = communityBoardService.selectAll();
        m.addAttribute("list",list);
        return "/CommunityList";
    }

    @RequestMapping(value="/test",method = RequestMethod.GET)
    @ResponseBody
    public List test()throws Exception{
        List<CommunityBoardDto> list = communityBoardService.selectAll();
//        System.out.println(list);
        return list;
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String communityBoard()throws Exception{
        //만약 user123이라면
        //만약 아이디가 없다면
        return "/Board";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String Write(CommunityBoardDto communityBoardDto, Model m)throws Exception{

        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(2);
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setNick("skyLee");


        String title = communityBoardDto.getTitle();
        String contents = communityBoardDto.getContents();
        String commu_cd = communityBoardDto.getcommu_cd();




        communityBoardDto.setcommu_cd(commu_cd);
        communityBoardDto.setTitle(title);
        communityBoardDto.setContents(contents);

        System.out.println("연결1");


        communityBoardService.write(communityBoardDto);
        System.out.println("연결2");
        m.addAttribute("CommunityBoardDto",communityBoardDto);

        return "/Board";

    }

    @RequestMapping(value="/read", method = RequestMethod.GET)
    public String read(Integer no,Model m)throws Exception{

        CommunityBoardDto communityBoardDto = communityBoardService.read(no);
        m.addAttribute("CommunityBoardDto",communityBoardDto);
        return "/Board";
    }

    @RequestMapping(value = "/modify" ,method = RequestMethod.POST)
    public String update(CommunityBoardDto communityBoardDto, Model m)throws Exception{
        communityBoardService.modify(communityBoardDto);

        System.out.println(communityBoardDto.toString());
        m.addAttribute("CommunityBoardDto",communityBoardDto);
        return "/Board";
    }

    @RequestMapping(value="/remove", method = RequestMethod.GET)
    public String remove(Integer no,Model m,CommunityBoardDto communityBoardDto)throws Exception{

        communityBoardService.remove(no);
        m.addAttribute("CommunityBoardDto",communityBoardDto);
        return "/Board";
    }
}
