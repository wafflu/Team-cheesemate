package team.cheese.controller.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.dao.*;
import team.cheese.service.sale.SaleService;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Controller
public class MainController {
    @Autowired
    SaleDao saleDao;
    @Autowired
    team.cheese.dao.SaleCategoryDao saleCategoryDao;
    @Autowired
    team.cheese.dao.AdministrativeDao administrativeDao;
    @Autowired
    team.cheese.dao.TagDao tagDao;
    @Autowired
    team.cheese.dao.AddrCdDao addrCdDao;

    @Autowired
    SaleService saleService;

    @Autowired
    team.cheese.dao.TestSession testSession;

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