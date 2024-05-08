package team.cheese.Controller.CommunityHeart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team.cheese.Controller.CommunityBoard.CommunityInterceptor;
import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
import team.cheese.service.CommunityHeart.CommunityHeartService;

@Controller
public class CommunityHeartController {

//    @Autowired
//    CommunityHeartService communityHeartService;
//
//    //하트 누를때
//    @RequestMapping(value="/doLike",method = RequestMethod.POST)
//    public int doLike(CommunityHeartDto communityHeartDto, Model m, RedirectAttributes redirectAttributes){
//        try{
//            communityHeartService.doLike(communityHeartDto);
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("message", e.getMessage());
//        }
//        m.addAttribute("communityHeartDto", communityHeartDto);
//        return 1;
//    }

}
