package team.cheese.controller.MyPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team.cheese.domain.UserDto;
import team.cheese.service.UserService;
import team.cheese.service.sale.SaleService;
import team.cheese.entity.PageHandler;

import team.cheese.domain.MyPage.SearchCondition;
import team.cheese.domain.MyPage.UserInfoDTO;
import team.cheese.domain.SaleDto;
import team.cheese.service.MyPage.UserInfoService;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    SaleService saleService;

    @Autowired
    UserService userService;

    @Autowired
    public MyPageController(UserInfoService userInfoService, SaleService saleService){
        this.userInfoService = userInfoService;
        this.saleService = saleService;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("msg", "MyPage Read Error: " + ex.getMessage());
        return "home";
    }

    // 마이페이지 메인화면
    @GetMapping("/main")
    public String main(@RequestParam(required = false)String ur_id, HttpSession session, Model model) throws Exception{
        UserInfoDTO userInfoDTO = null;
        // 로그인이 안되어있을떄
        if(!loginCheck(session)) {
            // ur_id값도 null이면 로그인 폼으로
            if(ur_id==null)
                return "loginForm";
            userInfoDTO = userInfoService.read(ur_id);
            model.addAttribute("userInfoDTO",userInfoDTO);
        // 로그인이 되어있을떄
        }else {
            // 1. 세션에서 session_id 값 받아오기
            String session_id = (String) session.getAttribute("userId");
//            String session_id = "asdf";
            model.addAttribute("session_id",session_id);
            // 소개글 읽어오기
            userInfoDTO = userInfoService.read(ur_id,session_id,session);
            System.out.println("userdto : "+userInfoDTO);
            model.addAttribute("userInfoDTO",userInfoDTO);
        }
        // 다른 페이지에서 사용자를 클릭해서 /myPage/main?ur_id=rudtlr 으로 타고들어왔을때,
        // QueryString으로 ur_id값이 들어오면 다른 사람의 myPage를 본다는 것
        // QueryString으로 ur_id값이 안들어온다는 것은 자신의 myPage를 본다는 것


        // 오늘날짜 모델에 담기
        Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        model.addAttribute("startOfToday", startOfToday.toEpochMilli());
        model.addAttribute("msg","MyPage Read Complete");
        return "myPage";
    }

    // 마이페이지 판매,구매내역 화면
    @RequestMapping("/saleInfo")
    public String saleInfo(HttpSession session, Model model) throws Exception {
        // 1. 세션에서 session_id 값 받아오기
        String session_id = (String) session.getAttribute("userId");
        model.addAttribute("ur_id",session_id);

        return "saleInfo";
    }

    // 조건객체에 따른 목록과 페이징정보를 Map에 담아 전달
    @PostMapping("/saleHistorys")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(@RequestBody SearchCondition sc) throws Exception {
        // 조건객체에 따른 목록전체 카운트수
        int totalCnt = saleService.getSearchCnt(sc);

        // 페이징 객체 생성 각 매개변수에 값 전달
        PageHandler ph = new PageHandler(totalCnt,sc.getPage(),sc.getPageSize());
        List<SaleDto> list = saleService.getSearchPage(sc);

        // 목록과 페이징을 하기 위해 PageHandler객체를 Map에 저장
        Map<String, Object> response = new HashMap<>();
        response.put("list", list);
        response.put("ph", ph);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // *** 내 정보 수정 페이지로 이동 ***
    @GetMapping("/editMyInfo")
    public String editMyInfoForm(HttpSession session, Model model) throws Exception {

        UserDto userDto = userService.getUserById(session.getAttribute("userId").toString());

        Map<String, String> userInfoMap = new HashMap<>();
        userInfoMap.put("id", userDto.getId());
        userInfoMap.put("nick", userDto.getNick());
        userInfoMap.put("phone_num", userDto.getPhone_num());
        userInfoMap.put("email", userDto.getEmail());

        model.addAttribute("userDto",userDto);

        return "editMyInfo";
    }

    // *** 내 정보 수정 ***
    @PostMapping("/editMyInfo")
    public String editMyInfo(@RequestParam Map<String, String> userInfoMap, RedirectAttributes redirectAttributes, Model model) throws Exception {

        UserDto userDto = userService.getUserById(userInfoMap.get("id"));
        model.addAttribute("userDto",userDto);

        if(userDto != null) {
            userService.updateUser(userInfoMap);
            redirectAttributes.addAttribute("resultMSG", "정상적으로 정보가 변경 되었습니다.");
        }
        else {
            redirectAttributes.addAttribute("resultMSG", "변경 에러");
        }

        return "redirect:/myPage/editMyInfo";
    }


    // *** 비밀번호 변경 폼으로 이동 ***
    @GetMapping("/changeMyPw")
    public String changeMyPwForm(HttpSession session, Model model) throws Exception {

        UserDto userDto = userService.getUserById(session.getAttribute("userId").toString());

        model.addAttribute("userDto",userDto);

        return "changeMyPw";
    }

    // *** 비밀번호 변경 ***
    @PostMapping("/changeMyPw")
    public String changeMyPw(HttpSession session, Model model, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes) throws Exception {

        UserDto userDto = userService.getUserById(session.getAttribute("userId").toString());

        map.put("id", userDto.getId());
        map.put("pw", map.get("newPw"));
        model.addAttribute("userDto",userDto);

        if(userService.updateUserPw(map) > 0) {
            redirectAttributes.addAttribute("resultMSG", "비밀번호가 정상적으로 변경 되었습니다.");
        }
        else {
            redirectAttributes.addAttribute("resultMSG", "변경 에러");
        }

        return "redirect:/myPage/changeMyPw";
    }

    // *** 회원탈퇴 폼으로 이동 ***
    @GetMapping("/leaveAccountForm")
    public String leaveAccountForm(HttpSession session, Model model) throws Exception {

        UserDto userDto = userService.getUserById(session.getAttribute("userId").toString());

        model.addAttribute("userDto",userDto);

        return "leaveAccount";
    }

    // *** 회원탈퇴 기능 ***
    @GetMapping("/leaveAccount")
    public String leaveAccount(HttpSession session) throws Exception {

        UserDto userDto = userService.getUserById(session.getAttribute("userId").toString());

        Map<String, String> map = new HashMap<>();
        map.put("s_cd", "F");
        map.put("id", userDto.getId());

        userService.updateUser_s_cd(map);

        session.invalidate();

        return "redirect:/";
    }

    private boolean loginCheck(HttpSession session) {
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("userId")!=null;
    }

}
