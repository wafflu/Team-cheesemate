package team.cheese.Controller.Sale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import team.cheese.dao.Sale.SaleDao;
import team.cheese.Domain.Sale.Sale;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/sale")
public class SaleController {
    @Autowired
    SaleDao saleDao;

    @RequestMapping("/read")
    public String read(Integer baddr_no, Model model, HttpSession session) throws Exception {
        List<Sale> list = saleDao.selectAll();
        Sale sale = list.get(0);
        System.out.println("sale.jsp로 전달");
        System.out.println("판매자 아이디 : " + sale.getSeller_id());


        model.addAttribute("Sale", sale);

//		return "redirect:/board/write";
        return "/login/sale";
    }
}
