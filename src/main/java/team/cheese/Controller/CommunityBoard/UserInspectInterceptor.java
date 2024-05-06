package team.cheese.Controller.CommunityBoard;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInspectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //인증 검사 등의 로직을 구현
        System.out.println("preHandle1");
        System.out.println("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI());
        System.out.println("[handler][" + handler.toString() + "]");

        HttpSession session = request.getSession(true);
        if (session == null) {
            String sessionUserId = (String)session.getAttribute("userId");
            String requestUserId = request.getParameter("ur_id");

            if(sessionUserId != null && sessionUserId.equals(requestUserId)) {  //사용자 ID가 일치하는 경우
                return true;
            }else{
                return false;   //사용자의 ID가 서로 다른 경우
            }
        } else{
                return false;   //세션 자체가 없는 경우
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //컨트롤러가 실행된 후의 로직을 구현
        System.out.println("postHandle1");
        System.out.println("[ModelAndView][ model 이름 : " + modelAndView.getViewName() + "][ model 내용 :" + modelAndView.getModel() + "]" );
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //요청이 완료된 후의 로직을 구현
    }
}
