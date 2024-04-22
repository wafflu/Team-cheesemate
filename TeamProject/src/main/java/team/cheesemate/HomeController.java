package team.cheesemate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
<<<<<<< Updated upstream
        System.out.println("hello!! 또 테스트");
        return "index";

//        System.out.println("정훈 테스트");
    }

=======
        return "index";

    }
>>>>>>> Stashed changes
}
