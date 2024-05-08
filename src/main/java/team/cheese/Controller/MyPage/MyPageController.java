package team.cheese.Controller.MyPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String main(@RequestParam(required = false)String ur_id, HttpSession session, Model model) {
        if(!loginCheck(session))
            return "home";
        // 다른 페이지에서 사용자를 클릭해서 /myPage/main?ur_id=rudtlr 으로 타고들어왔을때,
        // QueryString으로 ur_id값이 들어오면 다른 사람의 myPage를 본다는 것
        // QueryString으로 ur_id값이 안들어온다는 것은 자신의 myPage를 본다는 것
        // 1. 세션에서 session_id 값 받아오기
        // String session_id = (String) session.getAttribute("id");
        // test를 위한 session_id값
        String session_id = "1";

        try {
            UserInfoDTO userInfoDTO = userInfoService.read(ur_id,session_id);
            model.addAttribute("userInfoDTO",userInfoDTO);
            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            model.addAttribute("startOfToday", startOfToday.toEpochMilli());
            model.addAttribute("msg","MyPage Read Complete");

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
