package team.cheese.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("LoginCheckInterceptor - preHandle 호출되었습니다.");

        // *** 로그인을 한 상태인지 체크한다 ***
        // 1. 로그인 상태 체크
        //      1.1 세션에 로그인 정보가 있는 경우 true 리턴
        //      1.2 세션에 로그인 정보가 없는 경우 false 리턴

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("userId") == null) {
            System.out.println("요청한 화면 -> " + request.getRequestURI());

            // 주소창에 직접 주소를 입력할 경우 일단 home으로 가도록 설정
            if(request.getRequestURI() == "") {
                response.sendRedirect("/loginForm" + "?from=" + "home");
            } else if(request.getRequestURI().equals("/sale/write")) {
                response.sendRedirect("/loginForm" + "?from=" + "/sale");
            }
            else {
                response.sendRedirect("/loginForm" + "?from=" + request.getRequestURI());
            }

            System.out.println("로그인 정보가 없습니다.");
            return false;
        }
        else {
            String userId = session.getAttribute("userId").toString();
            System.out.println("로그인 정보가 있습니다. -> " + userId);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("LoginCheckInterceptor - postHandle 호출되었습니다.");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoginCheckInterceptor - afterHandle 호출되었습니다.");
    }
}