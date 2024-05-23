package team.cheese.controller.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.*;
import team.cheese.dao.*;
import team.cheese.service.ImgService;
import team.cheese.service.sale.SaleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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
    TagDao tagDao;
    @Autowired
    AddrCdDao addrCdDao;

    @Autowired
    SaleService saleService;

    @Autowired
    TestSession testSession;

    @Autowired
    ImgService imgService;

    // 전체 게시글을 보는 경우
    @RequestMapping("/list")
    public String getList(Model model, HttpSession session, HttpServletRequest request) throws Exception {
//        HttpSession session = request.getSession();
        String ur_id = (String) session.getAttribute("userId");

        if (ur_id != null) {
            // 세션에서 주소값LIST를 가지고 옴
            List<AddrCdDto> addrCdList = (List<AddrCdDto>) session.getAttribute("userAddrCdDtoList");

            // 사용자의 기본 주소 첫번째
            model.addAttribute("addrCdList", addrCdList);
        }

        List<SaleCategoryDto> saleCategory = saleService.selectCategory1();

        model.addAttribute("saleCategory1", saleCategory);

        return "/sale/saleList";
    }

    // 게시글 리스트 중 하나를 클릭한 경우
    @RequestMapping("/read")
    public String read(Long no, Model model) throws Exception {
        Map map = saleService.read(no);
        SaleDto saleDto = (SaleDto) map.get("saleDto");

        if(saleDto == null) {
            return "/error/saleError";
        }
        List<TagDto> tagDto = (List<TagDto>) map.get("tagDto");
        List<ImgDto> imglist = imgService.read(saleDto.getGroup_no());

        model.addAttribute("Sale", saleDto); // model로 값 전달
        model.addAttribute("tagList", tagDto); // model로 값 전달
        model.addAttribute("imglist", imglist); // model로 값 전달

        return "/sale/saleBoard";
    }

    // 글쓰기 버튼 누른 경우
    @PostMapping("/write")
    public String write(@RequestParam("addr_cd") String addr_cd,
                        @RequestParam("addr_name") String addr_name, Model model, HttpServletRequest request) throws Exception {
        // 로그인 한 경우
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        String user_nick = (String) session.getAttribute("userNick");

        List<AddrCdDto> addrCdDtoList = (List<AddrCdDto>) session.getAttribute("userAddrCdDtoList");


        SaleDto saleDto = new SaleDto(addr_cd, addr_name);
        model.addAttribute("Sale", saleDto);
        model.addAttribute("saleCategory1", saleCategoryDao.selectCategory1());
        return "/sale/saleWrite";

    }

    // 수정하기 버튼을 눌렀을 때 글을 받아서 jsp로 전달
    @PostMapping("/modify")
    public String modify(@RequestParam Long no, Model model, HttpServletRequest request) throws Exception {

        Map map = saleService.modify(no);
        SaleDto saleDto = (SaleDto) map.get("saleDto");
        String tagContents = (String) map.get("tagContents");
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        String user_nick = (String) session.getAttribute("userNick");

        saleDto.setSeller_id(user_id);
        saleDto.setSeller_nick(user_nick);

        List<ImgDto> imglist = imgService.read(saleDto.getGroup_no());

        model.addAttribute("Sale", saleDto);
        model.addAttribute("Tag", tagContents);
        model.addAttribute("imglist", imglist); // model로 값 전달
        model.addAttribute("saleCategory1", saleCategoryDao.selectCategory1());

        return "/sale/saleWrite";
    }

    @RequestMapping("/remove")
    public String remove(@RequestParam Long no, Model model, HttpSession session) throws Exception {
        String seller_id = (String) session.getAttribute("userId");
        saleService.remove(no, seller_id);

        // 등록 후에는 다시 글 목록 페이지로 리다이렉트
        return "redirect:/sale/list";
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<List> handleSQLException(IndexOutOfBoundsException e) {
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}