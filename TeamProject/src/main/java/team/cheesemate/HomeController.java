package team.cheesemate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        System.out.println("hello!!");
        return "index";

//        System.out.println("정훈 테스트");
    }

}
