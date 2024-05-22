package team.cheese.controller.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.UserDto;
import team.cheese.service.AddrCdService;
import team.cheese.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Autowired
    AddrCdService addrCdService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (isKeepLoginState(session, request, response)) {
            return true;
        }

        if(session.getAttribute("userId") == null) {
            String redirectUri = "";
            redirectUri = "/login?from=" + request.getRequestURI();
            if(request.getRequestURI().equals("/sale/write")) {
                redirectUri = "/login?from=/sale/list";
            }
            response.sendRedirect(redirectUri);
            return false;
        }
        else {
            String userId = session.getAttribute("userId").toString();
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean isKeepLoginState(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        String keepLoginStateUserId = "";
        String keepLoginStateUserPw = "";

        if (cookies == null) {
            return false;
        }

        for(int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("keepLoginStateId")) {
                keepLoginStateUserId = cookies[i].getValue();
                keepLoginStateUserPw = cookies[i+1].getValue();

                if(!userService.getUserById(keepLoginStateUserId).getPw().equals(keepLoginStateUserPw)) {
                    return false;
                }

                cookies[i].setMaxAge(60);
                cookies[i+1].setMaxAge(60);
                cookies[i].setPath("/"); // 쿠키의 경로 설정
                cookies[i+1].setPath("/"); // 쿠키의 경로 설정
                cookies[i].setDomain(request.getServerName()); // 쿠키의 도메인 설정
                cookies[i+1].setDomain(request.getServerName()); // 쿠키의 도메인 설정
                response.addCookie(cookies[i]);
                response.addCookie(cookies[i+1]);

                UserDto userDto = userService.getUserById(keepLoginStateUserId);
                sessionSetting(session, userDto);

                return true;
            }
        }

        return false;
    }

    private void sessionSetting(HttpSession session, UserDto loginUserDto) {
        session.setAttribute("userId", loginUserDto.getId()); // -> 세션에 아이디 저장
        session.setAttribute("userNick", loginUserDto.getNick()); // -> 세션에 닉네임 저장

        List<AddrCdDto> userAddrCdDtoList = addrCdService.getAddrCdByUserId(loginUserDto.getId());
        session.setAttribute("userAddrCdDtoList", userAddrCdDtoList); // -> 세션에 유저 주소 저장
    }
}