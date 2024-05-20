package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.UserDto;
import team.cheese.service.AddrCdService;
import team.cheese.service.AdminService;
import team.cheese.service.UserService;
import team.cheese.service.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    AddrCdService addrCdService;

    @Autowired
    AdminService adminService;

    @Autowired
    ImgService imgService;

    // *** 홈(home.jsp)으로 이동 ***
    @GetMapping({"/", "/home"})
    public String index(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        return isKeepLoginState(session, request, response);
    }

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @GetMapping("/board_2")
    public String board_2() {
        return "board_2";
    }

    private String isKeepLoginState(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        String keepLoginStateUserId = "";

        if (cookies == null) {
            return "home";
        }

        for(int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("keepLoginStateId")) {
                keepLoginStateUserId = cookies[i].getValue();
                cookies[i].setMaxAge(60);
                response.addCookie(cookies[i]);

                UserDto userDto = userService.getUserById(keepLoginStateUserId);
                sessionSetting(session, userDto);

                return "home";
            }
        }

        return "home";
    }

    private void sessionSetting(HttpSession session, UserDto loginUserDto) {
        System.out.println("@@@ 세션 설정 됩니다.");
        session.setAttribute("userId", loginUserDto.getId()); // -> 세션에 아이디 저장
        session.setAttribute("userNick", loginUserDto.getNick()); // -> 세션에 닉네임 저장

        List<AddrCdDto> userAddrCdDtoList = addrCdService.getAddrCdByUserId(loginUserDto.getId());
        session.setAttribute("userAddrCdDtoList", userAddrCdDtoList); // -> 세션에 유저 주소 저장
    }
}