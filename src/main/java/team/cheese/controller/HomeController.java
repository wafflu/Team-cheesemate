package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.AdminDto;
import team.cheese.domain.UserDto;
import team.cheese.service.AddrCdService;
import team.cheese.service.AdminService;
import team.cheese.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    AddrCdService addrCdService;


    // *** 홈(home.jsp)으로 이동 ***
    @GetMapping("/")
    public String index() {
        return "home";
    }

    // *** 홈(home.jsp)으로 이동 ***
    @GetMapping("/home")
    public String home() { return "home"; }

    // *** 로그인 화면(loginFrom.jsp)으로 이동 ***
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    // *** 로그인 기능 ***
    // 1. 입력된 아이디로 유저/관리자 로그인이 가능한지 확인
    //      1.1 유저가 로그인한 경우
    //          1.1.1 세션에 유저의 정보 저장(Id, Nick, addr_cd)
    //          1.1.2 홈(home.jsp)으로 이동
    //      1.2 관리자가 로그인한 경우
    //          1.2.1 세션에 관리자의 정보 저장
    //          1.2.2 홈(home.jsp)으로 이동
    //      1.3 로그인 실패할 경우
    //          1.3.1 로그인 화면(loginForm.jsp)으로 이동
    @PostMapping("/login")
    public String login(Model m, HttpSession session, String inputId, String inputPw, boolean rememberAccountBnt, boolean keepLoginState, HttpServletResponse res) {

        UserDto userDto = userService.login(inputId, inputPw);
        AdminDto adminDto = adminService.login(inputId, inputPw);

        if(userDto != null) { // 1.1 유저가 로그인한 경우
            session.setAttribute("userId", userDto.getId()); // -> 세션에 아이디 저장
            session.setAttribute("userNick", userDto.getNick()); // -> 세션에 닉네임 저장

            List<AddrCdDto> userAddrCdDtoList = addrCdService.getAddrCdByUserId(userDto.getId());
            session.setAttribute("userAddrCdDtoList", userAddrCdDtoList); // -> 세션에 유저 주소 저장

            // *** 쿠키로 자동 로그인 만들기 ***
            // 1. 체크박스 체크 여부 불러오기
            // 2. 체크한 경우 입력된 아이디와 비밀번호를 쿠키로 저장하기
            // 3. 다시 로그인 폼으로 갔을 때, 아이디와 비밀번호를 쿠키에서 불러와 자동입력되도록 만들기
            if(rememberAccountBnt) {
                Cookie cookieUserId = new Cookie("userId", userDto.getId());

                cookieUserId.setMaxAge(60*60*24*7);

                cookieUserId.setPath("/");

                res.addCookie(cookieUserId);
            }

            // *** 로그인 유지 쿠키 생성 ***
            if(keepLoginState) {

            }

            return "home";
        }
        else if(adminDto != null) { // 1.2 관리자가 로그인한 경우
            session.setAttribute("adminId", adminDto.getId());

            return "home";
        }
        else { // 1.3 로그인 실패할 경우

            m.addAttribute("loginErrorMSG", "아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
            return "loginForm";
        }

    }

    // *** 로그아웃 기능, 홈(home.jsp)으로 이동 ***
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "home";
    }

    // *** 보드(board.jsp)로 이동 ***
    @GetMapping("/board")
    public String board() {
        return "board";
    }

    // *** 회원가입 화면(resisterForm.jsp) 이동 ***
    @GetMapping("/resisterForm")
    public String resisterForm(Model m) {

        // *** 아이디 중복 체크를 위해 모든 ID를 가져와 모델로 넘겨준다 ***
        // 1. 관리자 아이디을 모두 불러온다
        // 2. 유저 아이디을 모두 불러온다(관리자/유저가 합쳐짐)
        // 3. 모든 아이디를 모델에 추가한다.

        List<String> adminIdList = adminService.getAllAdminsId();
        List<String> AllUsersAdminsId = userService.getAllUsersAdminsId(adminIdList);

        m.addAttribute("allUsersAdminsId", AllUsersAdminsId);

        return "resisterForm";
    }

    // *** 유저가 입력한 정보를 DB에 저장, 로그인 화면(loginForm.jsp)으로 이동 ***
    @PostMapping("/createAccount")
    @Validated
    public String createAccount(@ModelAttribute @Valid UserDto inputUserDto, BindingResult result) {
        if(result.hasErrors()) {
            System.out.println(result.toString());
            return "resisterForm";
        }

        System.out.println("HomeController에서 createAccount를 실행합니다.");

        // *** 전달 받은 유저의 정보를 DB에 저장 ***
        System.out.println(inputUserDto.getForeigner());

        UserDto userDto = new UserDto(inputUserDto.getId(),
                                    inputUserDto.getPw(),
                                    inputUserDto.getName(),
                                    inputUserDto.getNick(),
                                    inputUserDto.getBirth(),
                                    inputUserDto.getGender(),
                                    inputUserDto.getPhone_num(),
                            "",
                                    inputUserDto.getForeigner(),
                                    inputUserDto.getEmail(),
                                'O',
                                    inputUserDto.getAddr_det(),
                                    new Timestamp(System.currentTimeMillis()),
                                "",
                                    new Timestamp(System.currentTimeMillis()),
                                ""
        );

        userService.insertNewUser(userDto);

        return "loginForm";
    }
}
