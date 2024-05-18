package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.UserDto;
import team.cheese.service.AdminService;
import team.cheese.service.UserService;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ResisterController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    // *** 회원가입 화면(resisterForm.jsp) 이동 ***
    @GetMapping("/resisterForm")
    public String resisterForm(Model m) {
        return "resisterForm";
    }

    // *** 유저가 입력한 정보를 DB에 저장, 로그인 화면(loginForm.jsp)으로 이동 ***
    @PostMapping("/createAccount")
    @Validated
    public String createAccount(@ModelAttribute @Valid UserDto inputUserDto, BindingResult result) throws NoSuchAlgorithmException {
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

        if(userService.insertNewUser(userDto) == 1) {
            System.out.println("회원가입 정상 완료");
            return "loginForm";
        }
        else {
            System.out.println("회원가입 실패");
            return "resisterForm";
        }
    }

    @GetMapping(value = "/checkIdDuplication", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String checkIdDuplication(@RequestParam("id") String id) {
        List<String> adminIdList = adminService.getAllAdminsId();
        List<String> AllUsersAdminsId = userService.getAllUsersAdminsId(adminIdList);

        for(int i=0; i<AllUsersAdminsId.size(); i++) {
            if(id.equals(AllUsersAdminsId.get(i))) {
                return "이미 존재하는 아이디입니다.";
            }
        }

        return "사용 가능한 아이디입니다.";
    }
}