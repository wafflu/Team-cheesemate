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
        // 임의의 사용자 ID 설정
        if (session.getAttribute("ur_id") == null) {
            session.setAttribute("ur_id", "user123");


            String currentLoginUser = (String) session.getAttribute("ur_id");
            System.out.println(currentLoginUser);
            String postOwnerUser = "user123";
            System.out.println(postOwnerUser);
            if (currentLoginUser != null && currentLoginUser.equals(postOwnerUser)) {  //사용자 ID가 일치하는 경우
                System.out.println("true세션같음");

                return true;
            } else {
                System.out.println("false세션 다름");
                //신고페이지로 이동
                //사용자의 ID가 서로 다른 경우(원래는 false임의로 true로 해놓틈)

                return false;
            }
        }
        else if(session.getAttribute("ur_id") != null) {
            String currentLoginUser = (String) session.getAttribute("ur_id");
            System.out.println(currentLoginUser);
            String postOwnerUser = "user123";
            System.out.println(postOwnerUser);
            if (currentLoginUser != null && currentLoginUser.equals(postOwnerUser)) {  //사용자 ID가 일치하는 경우
                System.out.println("true세션같음");

                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //컨트롤러가 실행된 후의 로직을 구현
        System.out.println("EditPostHandle");
        assert modelAndView != null;
        System.out.println("[ModelAndView][ model 이름 : " + modelAndView.getViewName() + "][ model 내용 :" + modelAndView.getModel() + "]" );
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //요청이 완료된 후의 로직을 구현
    }
}
