package team.cheese.Controller.CommunityBoard;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommunityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //인증 검사 등의 로직을 구현
        System.out.println("preHandle1");
        System.out.println("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI());
        System.out.println("[handler][" + handler.toString() + "]");


        System.out.println("-------------------");

        //1.세션값에 저장된 아이디와 세션이 일치하는지 확인해아햔다.->현재 임의 설정
        HttpSession session = request.getSession(true);
        session.setAttribute("ur_id", "user123");
        String session_id = (String) session.getAttribute("ur_id");

        //2.세션값에 저장된 아이디가 일치하지않거나 없는 경우 예외처리
        if(session_id==null){
            return false;
        }



        return true;
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
