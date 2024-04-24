package team.cheese.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import team.cheese.dao.SaleDao;
import team.cheese.domain.SaleDto;

@Controller
@RequestMapping(value = "/sale")
public class SaleController {
    @Autowired
    SaleDao saleDao;

    @RequestMapping("/read")
    public String read(Integer bno, Model model, HttpSession session) throws Exception {
        List<SaleDto> list = saleDao.selectAll();
        SaleDto sale = list.get(0);

//        SaleDto sale = saleDao.selec();

        System.out.println("sale.jsp로 전달");
        System.out.println("판매자 아이디 : " + sale.getSeller_id());

        model.addAttribute("Sale", sale);

//		return "redirect:/board/write";
        return "/login/sale";
    }
}