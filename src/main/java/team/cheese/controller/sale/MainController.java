package team.cheese.controller.sale;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.dao.*;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.AdministrativeDto;
import team.cheese.domain.SaleCategoryDto;
import team.cheese.domain.SaleDto;
import team.cheese.service.sale.SaleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    SaleDao saleDao;
    @Autowired
    SaleCategoryDao saleCategoryDao;
    @Autowired
    AdministrativeDao administrativeDao;
    @Autowired
    TagDao tagDao;
    @Autowired
    AddrCdDao addrCdDao;

    @Autowired
    SaleService saleService;

    @Autowired
    TestSession testSession;

    @RequestMapping(value = "/saleLoginTest", method = RequestMethod.GET)
    public String main(Model model, HttpSession session) throws Exception {

        return "saleLoginTest";
    }

    // 전체 게시글을 보는 경우
    @RequestMapping(value = "/sale")
    public String salePage(Model model, HttpSession session) throws Exception {
        // 1. 세션에서 정보를 불러옴
        //  1.1. 로그인 한 경우
        //    1.1.1. 유저의 로그인 정보 첫번째 주소를 불러옴
        //  1.2. 로그인 하지 않은 경우
        //    1.2.1. addr_cd값을 null로 전달

        // TestSession 클래스를 사용하여 세션을 설정
//        String ur_id = "asdf";
//        String ur_id = null;
//        session = testSession.setSession(ur_id, session);

        String ur_id = (String) session.getAttribute("userId");
        System.out.println("ur_id : " + ur_id);

//        if (ur_id != null) {
//            String addr_cd = addrCdDao.getAddrCdByUserId(ur_id).get(0).getAddr_cd();
//            model.addAttribute("addr_cd", addr_cd);
//        }
        return "redirect:/sale/list";
//        return "redirect:/sale/list?addr_cd=" + addr_cd;
    }

    private long getStartOfToday() {
        Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        return startOfToday.toEpochMilli();
    }
}