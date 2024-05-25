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
        AdministrativeDto administrativeDto = (AdministrativeDto) administrativeDao.selectAddrCdByAddrCd(tradingPlace_A_small);
        AddrCdDto addrCdDto = new AddrCdDto();

        addrCdDto.setUr_id(userDto.getId());
        addrCdDto.setAddr_cd(administrativeDto.getAddr_cd());
        addrCdDto.setAddr_name(administrativeDto.getAddr_name());
        addrCdDto.setState('Y');
        addrCdDto.setFirst_date(new Timestamp(System.currentTimeMillis()));
        addrCdDto.setFirst_id("admin");
        addrCdDto.setLast_date(new Timestamp(System.currentTimeMillis()));
        addrCdDto.setLast_id("admin");

        if(userService.insertNewUser(userDto, addrCdDto) == 1) {
            return "loginForm";
        }
        else {
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
        else if(hasMiddleSpace(id)) {
            return "아이디에 띄어쓰기를 넣을 수 없습니다.";
        }
        else if(isNumeric(id)) {
            return "아이디에는 반드시 영어와 숫자가 섞여있어야 합니다.";
        }
        else if(isAlphabetic(id)) {
            return "아이디에는 반드시 영어와 숫자가 섞여있어야 합니다.";
        }
        else if(containsSpecialCharacter(id)) {
            return "아이디에 한글 또는 특수문자는 불가능합니다.";
        }
        else {
            for(int i=0; i<AllUsersAdminsId.size(); i++) {
                if(id.equals(AllUsersAdminsId.get(i))) {
                    return "이미 존재하는 아이디입니다.";
                }
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

    public boolean hasMiddleSpace(String str) {
        return str.matches(".+\\s+.+");
    }

    public boolean isNumeric(String str) {
        return str.matches("^[0-9]+$");
    }

    public boolean isAlphabetic(String str) {
        return str.matches("^[A-Za-z]+$");
    }

    public boolean containsSpecialCharacter(String str) {
        return str.matches(".*[^A-Za-z0-9].*");
    }
}