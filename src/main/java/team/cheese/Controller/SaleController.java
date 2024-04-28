package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.dao.AdministrativeDao;
import team.cheese.dao.SaleCategoryDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.AdministrativeDto;
import team.cheese.domain.SaleCategoryDto;
import team.cheese.domain.SaleDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/sale")
public class SaleController {
    @Autowired
    SaleDao saleDao;
    @Autowired
    SaleCategoryDao saleCategoryDao;
    @Autowired
    AdministrativeDao administrativeDao;

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

        model.addAttribute("saleCategory1", saleCategoryDao.selectCategory1());
//        model.addAttribute("saleCategoryMsg", "대분휴>중분류>소분류를 선택하세요.");

        return "/login/saleWrite";
    }

    @PostMapping("/saleCategory2")
    @ResponseBody
    public List<SaleCategoryDto> getSaleCategory2(@RequestParam String category1, Model model) throws Exception {
        System.out.println("대분류 번호 : " + category1);
        System.out.println(saleCategoryDao.selectCategory2(category1));
        return saleCategoryDao.selectCategory2(category1);
    }

    @RequestMapping("/saleCategory3")
    @ResponseBody
    public List<SaleCategoryDto> getSaleCategory3(@RequestParam String category2, Model model) throws Exception {
        System.out.println("중분류 번호 : " + category2);
        System.out.println(saleCategoryDao.selectCategory3(category2));
        return saleCategoryDao.selectCategory3(category2);
    }

    @RequestMapping("/searchLetter")
    @ResponseBody
    public List<AdministrativeDto> getAdministrative(@RequestParam String searchLetter, Model model) throws Exception {
        // 검색어를 이용하여 판매글을 검색
        System.out.println("검색 내용 : " + searchLetter);
        System.out.println(administrativeDao.searchLetter(searchLetter));
        return administrativeDao.searchLetter(searchLetter);
    }
}