package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.UserDto;
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
    public String index(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model m) {
        return isKeepLoginState(session, request, response, m);
    }


    // *** 홈(home.jsp)으로 이동 ***
//    @GetMapping("/home")
//    public String home(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model m) {
//        return isKeepLoginState(session, request, response, Model m);
//    }

    // *** 보드(board.jsp)로 이동 ***
    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @GetMapping("/board_2")
    public String board_2() {
        return "board_2";
    }

    // *** 로그인 상태 유지 쿠키가 있는 경우 ***
    // 1. 쿠키에 있는 아이디를 가져온다
    // 2. 해당 아이디로 로그인 처리
    // 3. home으로 이동
    private String isKeepLoginState(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model m) {

        Cookie[] cookies = request.getCookies();
        String keepLoginStateUserId = "";

        if (cookies == null) {
            return "home";
        }

        for(int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("keepLoginState")) {
                keepLoginStateUserId = cookies[i].getValue();
                cookies[i].setMaxAge(60);
                response.addCookie(cookies[i]);

                UserDto userDto = userService.getUserById(keepLoginStateUserId);
                sessionSetting(session, userDto);

                System.out.println("쿠키 다시 설정");

                return "home";
            }
        }

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
}