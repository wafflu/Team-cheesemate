package team.cheese.controller.sale;

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
import team.cheese.service.sale.SaleService;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    SaleService saleService;

    // 전체 게시글을 보는 경우
    @RequestMapping("/list")
    public String getList(@RequestParam(defaultValue = "0") int check_addr_cd, Model model, HttpSession session) throws Exception {
        List<SaleDto> saleList = saleService.getList();

        System.out.println(saleList.size());
    
        // 사용자의 기본 주소 첫번째
        model.addAttribute("check_addr_cd", check_addr_cd);
        
        // 불러온 게시글 리스트를 모델에 담음
        model.addAttribute("saleList", saleList);

        return "/login/saleList";
    }
    
    // 게시글 리스트 중 하나를 클릭한 경우
    @RequestMapping("/read")
    public String read(Long no, Model model, HttpSession session) throws Exception {
        SaleDto saleDto = saleService.read(no);

        model.addAttribute("Sale", saleDto); // model로 값 전달

        return "/login/saleBoard";
    }

    // 글쓰기 버튼 누른 경우
    @GetMapping("/write")
    public String write(Model model, HttpSession session) throws Exception {

        model.addAttribute("saleCategory1", saleCategoryDao.selectCategory1());

        return "/login/saleWrite";
    }

    // 서비스로 분리
    // 글쓰기 완료하고 글을 등록하는 경우
    @PostMapping("/write")
    @ResponseBody
    public String write(@RequestBody SaleDto formData, Model model, HttpSession sesson,  HttpServletRequest request) throws Exception {
        // service 호출
        // 서비스단 작성 필요함
//        System.out.println(formData);

        // 1. 사용자가 글작성
        // 동시에 작성버튼 누르면?
        // 작성 실패하면?
        // 필수로 써야되는거 안썼으면? -> view에서 여기로 전송못하게하기
        
//        Integer no = saleDto.getNo();

//        return "success";
//        return "redirect:/sale/read?no=" + no;

        System.out.println("Request : " + request);
        System.out.println("값 들어왔는지 확인 : " + formData);
        // Service를 통해 글 등록 처리
        saleService.write(formData);

        // 등록 후에는 다시 글 목록 페이지로 리다이렉트
        return "redirect:/sale/list";
    }

    // 서비스로 분리
    // 수정 버튼 누른 경우
    @RequestMapping("/update")
    public String update(SaleDto saleDto, Model model, HttpSession session) throws Exception {

        saleService.modify(saleDto);

        return "redirect:/board/read?no=" + saleDto.getNo();
    }

//  ajax 요청을 처리해주는 URL등
    @PostMapping("/saleCategory2")
    @ResponseBody
    public List<SaleCategoryDto> getSaleCategory2(@RequestParam String category1, Model model) throws Exception {
        System.out.println("대분류 번호 : " + category1);
        System.out.println(saleCategoryDao.selectCategory2(category1));
        return saleCategoryDao.selectCategory2(category1);
    }

    // ajax 판매 카테고리 처리(중분류)
    @RequestMapping("/saleCategory3")
    @ResponseBody
    public List<SaleCategoryDto> getSaleCategory3(@RequestParam String category2, Model model) throws Exception {
        System.out.println("중분류 번호 : " + category2);
        System.out.println(saleCategoryDao.selectCategory3(category2));
        return saleCategoryDao.selectCategory3(category2);
    }

    // ajax 판매 카테고리 처리(소분류)
    @RequestMapping("/searchLetter")
    @ResponseBody
    public List<AdministrativeDto> getAdministrative(@RequestParam String searchLetter, Model model) throws Exception {
        // 검색어를 이용하여 판매글을 검색
        System.out.println("검색 내용 : " + searchLetter);
        System.out.println(administrativeDao.searchLetter(searchLetter));
        return administrativeDao.searchLetter(searchLetter);
    }
}