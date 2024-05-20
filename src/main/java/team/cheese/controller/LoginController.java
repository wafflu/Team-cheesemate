package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.AdminDto;
import team.cheese.domain.UserDto;
import team.cheese.service.AddrCdService;
import team.cheese.service.AdminService;
import team.cheese.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    AddrCdService addrCdService;

    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public String loginForm(HttpSession session, HttpServletRequest request) {
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(Model m, HttpSession session, String inputId, String inputPw, boolean rememberAccountBnt, boolean keepLoginState, HttpServletRequest request, HttpServletResponse res) {

        UserDto userDto = userService.login(inputId, inputPw);

        if(userDto != null) { // 1.1 유저가 로그인한 경우
            sessionSetting(session, userDto);

            // *** 아이디 기억 쿠키 생성 ***
            if(rememberAccountBnt) {
                Cookie userIdCookie = new Cookie("userId", userDto.getId());

                userIdCookie.setMaxAge(60);
                userIdCookie.setPath("/");

                res.addCookie(userIdCookie);
            }

            // *** 로그인 유지 쿠키 생성 ***
            if(keepLoginState) {
                Cookie keepLoginStateCookieId = new Cookie("keepLoginStateId", userDto.getId());
                Cookie keepLoginStateCookiePw = new Cookie("keepLoginStatePw", userDto.getPw());

                keepLoginStateCookieId.setMaxAge(60);
                keepLoginStateCookieId.setPath("/");

                keepLoginStateCookiePw.setMaxAge(60);
                keepLoginStateCookiePw.setPath("/");

                res.addCookie(keepLoginStateCookieId);
                res.addCookie(keepLoginStateCookiePw);
            }

            String fromUri = request.getParameter("from");
            System.out.printf("fromUri=%s\n", fromUri);

            if(fromUri.isEmpty()) {
                System.out.println("fromUri에는 아무것도 없음으로 home으로 이동합니다.");
                return "redirect:home";
            }
            else {
                System.out.println("fromUri에는 " + fromUri + " 이 있어 이동합니다.");
                return "redirect:" + fromUri;
            }
        }

        AdminDto adminDto = adminService.login(inputId, inputPw);
        if(adminDto != null) { // 1.2 관리자가 로그인한 경우
            session.setAttribute("adminId", adminDto.getId());

            return request.getParameter("from");
        }
        else { // 1.3 로그인 실패할 경우

            m.addAttribute("loginErrorMSG", "아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
            return "loginForm";
        }

    }

    // *** 로그아웃 기능, 홈(home.jsp)으로 이동 ***
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        System.out.println("*** 로그아웃을 진행합니다. ***");

        System.out.println("1. 로그인상태유지를 종료합니다.");
        removeKeepLoginStateCookie(request, response);

        System.out.println("2. 세션을 종료합니다.");
        session.invalidate();

        return "home";
    }

    // *** 유저가 로그인 성공한 경우 ***
    // 1. 유저의 정보를 세션에 저장한다
    private void sessionSetting(HttpSession session, UserDto loginUserDto) {
        session.setAttribute("userId", loginUserDto.getId()); // -> 세션에 아이디 저장
        session.setAttribute("userNick", loginUserDto.getNick()); // -> 세션에 닉네임 저장

        List<AddrCdDto> userAddrCdDtoList = addrCdService.getAddrCdByUserId(loginUserDto.getId());
        session.setAttribute("userAddrCdDtoList", userAddrCdDtoList); // -> 세션에 유저 주소 저장
    }

    // *** 로그아웃 누룰 시 로그인 상태 유지 쿠키 제거 ***
    private void removeKeepLoginStateCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        for(int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("keepLoginStateId")) {
                cookies[i].setMaxAge(0);
                cookies[i+1].setMaxAge(0);
                cookies[i].setPath("/");
                cookies[i+1].setPath("/");
                response.addCookie(cookies[i]);
                response.addCookie(cookies[i+1]);
                return;
            }
        }
    }
}