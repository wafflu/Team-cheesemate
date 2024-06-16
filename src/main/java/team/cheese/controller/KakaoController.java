package team.cheese.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import team.cheese.service.KakaoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class KakaoController {

    @Autowired
    private KakaoService kakaoService;

    public KakaoController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @RequestMapping(value = "/kakaoLogin", method = RequestMethod.GET)
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model, HttpServletRequest request) throws Throwable {
        String accessToken = kakaoService.getAccessTokenFromKakao("ec5fdbcb41c390cfce95bec959757c95", code);

        // *** 인가코드 받기 (GET)
        // 카카오톡 서버 측에서 인가코드 넘겨줌. (-> String code)
        // code: h-Rsy9FNCPNEP9jhUsDgkRVTZwaAOe5Hzcctp6_gPGIYFxFecsY4yYn3NQYKPXUZAAABjxmtomH7Ewsnpgvovw

        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);

        // 임시로 카카오톡 닉네임을 유저 이름으로 출력
        Object nickname = userInfo.get("nickname");
        model.addAttribute("userName", nickname.toString());

        // 임시로 로그인 상태 세션에 저장하여 로그인 상태 유지
        HttpSession session = request.getSession();
        session.setAttribute("loginState", "Y");

        return "main";
    }
}