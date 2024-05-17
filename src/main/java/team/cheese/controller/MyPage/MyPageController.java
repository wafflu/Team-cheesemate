package team.cheese.controller.MyPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.cheese.domain.MyPage.CommentPageHandler;
import team.cheese.domain.MyPage.UserInfoDTO;
import team.cheese.service.MyPage.ReviewCommentService;
import team.cheese.service.MyPage.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

    UserInfoService userInfoService;
    ReviewCommentService reviewCommentService;

    @Autowired
    public MyPageController(UserInfoService userInfoService, ReviewCommentService reviewCommentService){
        this.userInfoService = userInfoService;
        this.reviewCommentService = reviewCommentService;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("msg", "MyPage Read Error: " + ex.getMessage());
        return "home";
    }

    // 마이페이지 메인화면
    @RequestMapping("/main")
    public String main(@RequestParam(required = false)String ur_id, HttpSession session, Model model) throws Exception{
        if(!loginCheck(session))
            return "home";
        // 다른 페이지에서 사용자를 클릭해서 /myPage/main?ur_id=rudtlr 으로 타고들어왔을때,
        // QueryString으로 ur_id값이 들어오면 다른 사람의 myPage를 본다는 것
        // QueryString으로 ur_id값이 안들어온다는 것은 자신의 myPage를 본다는 것
        // 1. 세션에서 session_id 값 받아오기
        // String session_id = (String) session.getAttribute("id");
        // test를 위한 session_id값
        String session_id = "1";
        // 소개글 읽어오기
        UserInfoDTO userInfoDTO = userInfoService.read(ur_id,session_id);
        model.addAttribute("userInfoDTO",userInfoDTO);
        // 오늘날짜 모델에 담기
        Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        model.addAttribute("startOfToday", startOfToday.toEpochMilli());
        model.addAttribute("msg","MyPage Read Complete");
        return "main";
    }

    // 마이페이지 판매,구매내역 화면
    @RequestMapping("/saleInfo")
    public String saleInfo() throws Exception {
        return "saleInfo";
    }


    private boolean loginCheck(HttpSession session) {
        // 1. 세션을 얻어서
//        HttpSession session = request.getSession();
//        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
//        return session.getAttribute("id")!=null;
        return true;
    }

}
