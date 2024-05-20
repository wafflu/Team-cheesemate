package team.cheese.controller.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.cheese.dao.*;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SaleDto;
import team.cheese.domain.TagDto;
import team.cheese.service.ImgService;
import team.cheese.service.sale.HoistingService;
import team.cheese.service.sale.SaleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class HoistingController {
    @Autowired
    SaleDao saleDao;
    @Autowired
    HoistingDao hoistingDao;

    @Autowired
    SaleService saleService;

    @Autowired
    HoistingService hoistingService;

    // 끌어올리기 하는 경우
    @RequestMapping("/hoisting")
    public String hoisting(Long no, HttpSession session, HttpServletRequest request) throws Exception {
        String user_id = (String) session.getAttribute("userId");

        SaleDto saleDto = saleService.getSale(no);
        String seller_id = saleDto.getSeller_id();

        if(user_id.equals(seller_id)) {
            hoistingService.hoistingSale(saleDto);
        }

        return "redirect:/sale/list";
    }
}