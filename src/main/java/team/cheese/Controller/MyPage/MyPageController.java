package team.cheese.Controller.MyPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import team.cheese.Domain.MyPage.UserInfoDTO;
import team.cheese.Service.MyPage.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/main")
    public String main(HttpSession session,Model model) {
        if(!loginCheck(session))
            return "home";


//        String ur_id = (String) session.getAttribute("id");
        String ur_id = "user123";
        UserInfoDTO userInfoDTO = null;
        try {
            if(userInfoService.read(ur_id)==null) {
                userInfoDTO = new UserInfoDTO(ur_id,"");
                int rowCnt = userInfoService.write(userInfoDTO);
                if(rowCnt!=1) {
                    throw new Exception("Read ERR");
                }
                System.out.println(rowCnt);
            }
            userInfoDTO = userInfoService.read(ur_id);
            System.out.println(ur_id);
            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            model.addAttribute("startOfToday", startOfToday.toEpochMilli());

            model.addAttribute("msg","MyPage Read Complete");
            model.addAttribute("userInfoDTO",userInfoDTO);
            return "main";
        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg","MyPage Read Error");
            return "home";
        }
    }

    private boolean loginCheck(HttpSession session) {
        // 1. 세션을 얻어서
//        HttpSession session = request.getSession();
//        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
//        return session.getAttribute("id")!=null;
        return true;
    }

}
