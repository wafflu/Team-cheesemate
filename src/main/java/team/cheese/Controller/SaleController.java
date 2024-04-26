package team.cheese.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import team.cheese.dao.SaleCategoryDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.SaleDto;

@Controller
@RequestMapping(value = "/sale")
public class SaleController {
    @Autowired
    SaleDao saleDao;
    @Autowired
    SaleCategoryDao saleCategoryDao;

    @RequestMapping("/list")
    public String read(@RequestParam(defaultValue = "0") int check_addr_cd, Model model, HttpSession session) throws Exception {
        // check_addr_cd : 사용자의 첫번째 주소값(기본), 사용자의 선택에 따라서 값이 바뀜
        List<SaleDto> saleList = saleDao.selectAll();
//        SaleDto sale = list.get(0); // 화면에 전체를 뿌려줄때 get으로 고정시켜 둔거 삭제하기

        System.out.println("sale.jsp로 전달");
//        System.out.println("판매자 아이디 : " + sale.getSeller_id());
        System.out.println(saleList.size());

        model.addAttribute("check_addr_cd", check_addr_cd);
        model.addAttribute("saleList", saleList);

//		return "redirect:/board/write";
        return "/login/saleList";
    }

    @RequestMapping("/read")
    public String read(Integer bno, Model model, HttpSession session) throws Exception {
//        List<SaleDto> list = saleDao.selectAll();
//        SaleDto sale = list.get(0);

        SaleDto sale = saleDao.select(1); // 판배글을 클릭했을 때 값을 담아줘야 됨(parameter값으로 받아올 것)

        System.out.println("sale.jsp로 전달");
        System.out.println("판매자 아이디 : " + sale.getSeller_id());

        model.addAttribute("Sale", sale); // model로 값 전달

//		return "redirect:/board/write";
        return "/login/saleBoard";
    }

    @RequestMapping("/write")
    public String write(Model model, HttpSession session) throws Exception {
        model.addAttribute("categories", saleCategoryDao.selectAll());
        return "/login/saleWrite";
    }
}