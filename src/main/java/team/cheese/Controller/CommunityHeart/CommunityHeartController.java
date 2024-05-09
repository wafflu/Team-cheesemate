package team.cheese.Controller.CommunityHeart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team.cheese.Controller.CommunityBoard.CommunityInterceptor;
import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
import team.cheese.service.CommunityHeart.CommunityHeartService;

import javax.servlet.http.HttpSession;

@RequestMapping(value = "/community" )
@Controller
public class CommunityHeartController {

    @Autowired
    CommunityHeartService communityHeartService;

    //하트 누를때
    @RequestMapping(value="/doLike", method=RequestMethod.PATCH)
    public ResponseEntity<?> doLike(@RequestBody CommunityHeartDto communityHeartDto, HttpSession session){
       //세션아이디가 존재하는지 확인 -> Interceptor

        System.out.println("controller" + session.getAttribute("ur_id"));
        communityHeartDto.setUr_id(session.getAttribute("ur_id").toString());

        try{
            communityHeartService.doLike(communityHeartDto);
            return ResponseEntity.ok("좋아요가 성공적으로 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            // 예외 메시지를 클라이언트로 직접 반환
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // 서버 오류 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }



}
