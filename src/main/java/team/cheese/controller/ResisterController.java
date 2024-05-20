package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.cheese.dao.AdministrativeDao;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.AdministrativeDto;
import team.cheese.domain.UserDto;
import team.cheese.service.AddrCdService;
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

    @Autowired
    AddrCdService addrCdService;

    @Autowired
    AdministrativeDao administrativeDao;

    // *** 회원가입 화면(resisterForm.jsp) 이동 ***
    @GetMapping("/resisterForm")
    public String resisterForm(Model m) throws Exception {

        // 회원가입 화면에 대분류 리스트를 전송
        List<AdministrativeDto> largeCategory = administrativeDao.selectLargeCategory();
        m.addAttribute("largeCategory", largeCategory);

        return "resisterForm";
    }

    @PostMapping("/createAccount")
    @Validated
    public String createAccount(@ModelAttribute @Valid UserDto inputUserDto, BindingResult result, String tradingPlace_A_small) throws Exception {
        if(result.hasErrors()) {
            System.out.println(result.toString());
            return "resisterForm";
        }

        System.out.println("HomeController에서 createAccount를 실행합니다.");

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

            System.out.println("tradingPlace_A_small : " + tradingPlace_A_small); // tradingPlace_A_small : 37390310

            System.out.println("check 1");
            AdministrativeDto administrativeDto = (AdministrativeDto) administrativeDao.selectAddrCdByAddrCd(tradingPlace_A_small);
            System.out.println("check 2");
            AddrCdDto addrCdDto = new AddrCdDto();
            System.out.println("check 3");

            addrCdDto.setUr_id(userDto.getId());
            addrCdDto.setAddr_cd(administrativeDto.getAddr_cd());
            addrCdDto.setAddr_name(administrativeDto.getAddr_name());
            addrCdDto.setState('Y');
            addrCdDto.setFirst_date(new Timestamp(System.currentTimeMillis()));
            addrCdDto.setFirst_id("admin");
            addrCdDto.setLast_date(new Timestamp(System.currentTimeMillis()));
            addrCdDto.setLast_id("admin");

            System.out.println(addrCdDto.toString());
            addrCdService.insertAddrCd(addrCdDto);
            System.out.println("addr_cd 추가 완료");

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

        if(id.trim().length() != id.length()) {
            return "아이디에 띄어쓰기를 넣을 수 없습니다.";
        }

        for(int i=0; i<AllUsersAdminsId.size(); i++) {
            if(id.equals(AllUsersAdminsId.get(i))) {
                return "이미 존재하는 아이디입니다.";
            }
        }

        return "사용 가능한 아이디입니다.";
    }

    @GetMapping(value = "/mediumCategory", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<AdministrativeDto> mediumCategory(@RequestParam("tradingPlace_A_large") String largeCategory) throws Exception {
        return administrativeDao.selectMediumCategory(largeCategory);
    }

    @GetMapping(value = "/smallCategory", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<AdministrativeDto> smallCategory(@RequestParam("tradingPlace_A_medium") String mediumCategory) throws Exception {
        return administrativeDao.selectSmallCategory(mediumCategory);
    }
}