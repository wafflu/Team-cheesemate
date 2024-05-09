package team.cheese.controller.sale;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.dao.AdministrativeDao;
import team.cheese.dao.SaleCategoryDao;
import team.cheese.dao.SaleDao;
import team.cheese.dao.TagDao;
import team.cheese.domain.AdministrativeDto;
import team.cheese.domain.SaleCategoryDto;
import team.cheese.domain.SaleDto;
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
    SaleService saleService;

    // 전체 게시글을 보는 경우
    @RequestMapping("/list")
    public String getList(@RequestParam(defaultValue = "0") int check_addr_cd, Model model, HttpSession session) throws Exception {

        // 1. 세션에서 정보를 불러옴
        //  1.1. 로그인 한 경우
        //    1.1.1. 유저의 로그인 정보 첫번째 주소를 불러옴
        //  1.2. 로그인 하지 않은 경우
        //    1.2.1. addr_cd값을 null로 전달

        if(session.getAttribute("sessionId") != null) {

        } else {
            String addr_cd = (String) session.getAttribute("addr_cd");
            List<SaleDto> saleList = saleService.getList(addr_cd);
            System.out.println(saleList.size());

            // 사용자의 기본 주소 첫번째
            model.addAttribute("check_addr_cd", check_addr_cd);
            // 불러온 게시글 리스트를 모델에 담음
            model.addAttribute("saleList", saleList);

        }

        return "/login/saleList";
    }
    
    // 게시글 리스트 중 하나를 클릭한 경우
    @RequestMapping("/read")
    public String read(Long no, Model model, HttpSession session) throws Exception {
        SaleDto saleDto = saleService.read(no);
        System.out.println(saleDto);

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
//    @ResponseBody
    public String write(@RequestBody Map<String, Object> map, Model model, HttpSession sesson, HttpServletRequest request) throws Exception {
        // service 호출
        // 서비스단 작성 필요함
//        System.out.println(formData);

        // 1. 사용자가 글작성
        // 동시에 작성버튼 누르면?
        // 작성 실패하면?
        // 필수로 써야되는거 안썼으면? -> view에서 여기로 전송못하게하기

        // ObjectMapper : JSON 형태를 JAVA 객체로 변환
        System.out.println(map.get("sale"));
        System.out.println(map.get("tag"));
        ObjectMapper objectMapper = new ObjectMapper();
        SaleDto saleDto = objectMapper.convertValue(map.get("sale"), SaleDto.class);
        System.out.println("값 들어왔는지 확인 : " + saleDto);

//      List<String> tagContents = (List<String>) map.get("contents");
        Map<String, Object> tagMap = (Map<String, Object>) map.get("tag");
        List<String> tagContents = (List<String>) tagMap.get("contents");
        System.out.println("tag값 확인 : " + tagMap.size());

        // 각 해시태그를 반복하여 TagDto 객체 생성 및 tagList에 추가
        List<String> tagList = new ArrayList<>();
        for (String content : tagContents) {
            // '#' 기호를 제거하여 태그 내용만 추출
            String tagContent = content.substring(1);
            System.out.println("tagContent : " + tagContent);
            // TagDto 객체 생성
            // 생성된 TagDto를 tagList에 추가
            tagList.add(tagContent);
        }

        Map mapDto = new HashMap();
        mapDto.put("saleDto", saleDto);
        mapDto.put("tagList", tagList);

//      세션에서 ID 값을 가지고 옴
        String ur_id = "asdf";
        saleDto.setSeller_id(ur_id);
        
        // Service를 통해 글 등록 처리
        saleService.write(mapDto);

//        System.out.println("tag 값 들어왔는지 확인 : " + tagDto);
        System.out.println("글 번호 : " + saleDto.getNo());

        // 등록 후에는 다시 글 목록 페이지로 리다이렉트
        return "redirect:/sale/read?no=" + saleDto.getNo();
    }

    // 서비스로 분리
    // 수정 버튼 누른 경우
    @RequestMapping("/update")
    public String update(SaleDto saleDto, Model model, HttpSession session) throws Exception {

        saleService.modify(saleDto);

        return "redirect:/sale/read?no=" + saleDto.getNo();
    }

//  ajax 요청을 처리해주는 URL등
    @PostMapping("/saleCategory2")
    @ResponseBody
    public ResponseEntity<List<SaleCategoryDto>> getSaleCategory2(@RequestParam String category1, Model model) throws Exception {
//        System.out.println("대분류 번호 : " + category1);
//        System.out.println(saleCategoryDao.selectCategory2(category1));
        try{
            return new ResponseEntity<>(saleCategoryDao.selectCategory2(category1), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ajax 판매 카테고리 처리(중분류)
    @RequestMapping("/saleCategory3")
    @ResponseBody
    public  ResponseEntity<List<SaleCategoryDto>> getSaleCategory3(@RequestParam String category2, Model model) throws Exception {
//        System.out.println("중분류 번호 : " + category2);
//        System.out.println(saleCategoryDao.selectCategory3(category2));
        try{
            return new ResponseEntity<>(saleCategoryDao.selectCategory3(category2), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ajax 판매 카테고리 처리(소분류)
    @RequestMapping("/searchLetter")
    @ResponseBody
    public ResponseEntity<List<AdministrativeDto>> getAdministrative(@RequestParam String searchLetter, Model model) throws Exception {
        // 검색어를 이용하여 판매글을 검색
//        System.out.println("검색 내용 : " + searchLetter);
//        System.out.println(administrativeDao.searchLetter(searchLetter));
        try{
            return new ResponseEntity<>(administrativeDao.searchLetter(searchLetter), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}