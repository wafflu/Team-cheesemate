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

        if(userDto != null) {
            sessionSetting(session, userDto);

            if(rememberAccountBnt) {
                Cookie userIdCookie = new Cookie("userId", userDto.getId());

                userIdCookie.setMaxAge(60);
                userIdCookie.setPath("/");

                res.addCookie(userIdCookie);
            }

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

            if(fromUri.isEmpty()) {
                return "redirect:home";
            }
            else {
                return "redirect:" + fromUri;
            }
        }

        AdminDto adminDto = adminService.login(inputId, inputPw);
        if(adminDto != null) {
            session.setAttribute("adminId", adminDto.getId());

            return request.getParameter("from");
        }
        else {
            m.addAttribute("loginErrorMSG", "아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
            return "loginForm";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        removeKeepLoginStateCookie(request, response);

        session.invalidate();

        return "home";
    }

    private void sessionSetting(HttpSession session, UserDto loginUserDto) {
        session.setMaxInactiveInterval(24*60*60*7);
        session.setAttribute("userId", loginUserDto.getId()); // -> 세션에 아이디 저장
        session.setAttribute("userNick", loginUserDto.getNick()); // -> 세션에 닉네임 저장

        List<AddrCdDto> userAddrCdDtoList = addrCdService.getAddrCdByUserId(loginUserDto.getId());
        session.setAttribute("userAddrCdDtoList", userAddrCdDtoList); // -> 세션에 유저 주소 저장
    }

    // *** 로그아웃 누룰 시 로그인 상태 유지 쿠키 제거 ***
    private void removeKeepLoginStateCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}