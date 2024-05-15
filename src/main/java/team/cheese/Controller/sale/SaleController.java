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
import team.cheese.entity.PageHandler;
import team.cheese.service.sale.SaleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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

    // 전체 게시글을 보는 경우
    @RequestMapping("/list")
    public String getList(Model model, HttpSession session) throws Exception {
        System.out.println("/list들어옴");

        String ur_id = (String) session.getAttribute("userId");

        String addr_cd = addrCdDao.getAddrCdByUserId(ur_id).get(0).getAddr_cd();

        if(ur_id == null) {
            model.addAttribute("addrCdList", administrativeDao.selectAll());
        } else {
            // 세션에서 주소값LIST를 가지고 옴
            List<AddrCdDto> addrCdList = (List<AddrCdDto>) session.getAttribute("userAddrCdDtoList");

            // 사용자의 기본 주소 첫번째
            model.addAttribute("addrCdList", addrCdList);
        }

        model.addAttribute("saleCategory1", saleCategoryDao.selectCategory1());

        return "/saleList";
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
    @PostMapping("/write")
    public String write(@RequestParam String addr_cd, Model model, HttpSession session) throws Exception {
        System.out.println("POST write");
        // 로그인 한 경우

        String ur_id = (String) session.getAttribute("userId");

        List<AddrCdDto> addrCdDtoList = (List<AddrCdDto>) session.getAttribute("userAddrCdDtoList");
        String addr_name = "";
        for(AddrCdDto addrCdDto : addrCdDtoList) {
            if (addr_cd.equals(addrCdDto.getAddr_cd())) {
                addr_name = addrCdDto.getAddr_name();
                break;
            }
        }

        if(ur_id != null) {
            SaleDto saleDto = new SaleDto(addr_cd, addr_name);
            model.addAttribute("Sale", saleDto);
            model.addAttribute("saleCategory1", saleCategoryDao.selectCategory1());
            return "/login/saleWrite";
        } else {
            // 로그인 안한 경우
            return "main";
        }
    }

    // 수정하기 버튼을 눌렀을 때 글을 받아서 jsp로 전달
    @PostMapping("/modify")
    public String modify(@RequestParam Long no, Model model, HttpSession session, HttpServletRequest request) throws Exception {

        Map map = saleService.modify(no);
        SaleDto saleDto = (SaleDto) map.get("saleDto");
        String tagContents = (String) map.get("tagContents");

        model.addAttribute("Sale", saleDto);
        model.addAttribute("Tag", tagContents);
        model.addAttribute("saleCategory1", saleCategoryDao.selectCategory1());

        return "/login/saleWrite";
    }

    // 서비스로 분리
    // 글쓰기 완료하고 글을 등록하는 경우
    @PostMapping("/insert")
    @ResponseBody
    public ResponseEntity<String> write(@Valid @RequestBody Map<String, Object> map, Model model, HttpSession session, HttpServletRequest request) throws Exception {
        System.out.println("POST write");
        // service 호출
        // 서비스단 작성 필요함
//        System.out.println(formData);

        // 1. 사용자가 글작성
        // 동시에 작성버튼 누르면?
        // 작성 실패하면?
        // 필수로 써야되는거 안썼으면? -> view에서 여기로 전송못하게하기

        String seller_id = (String) session.getAttribute("userId");
        String seller_nick = (String) session.getAttribute("userNick");

        // ObjectMapper : JSON 형태를 JAVA 객체로 변환
        System.out.println(map.get("sale"));
        System.out.println(map.get("tag"));
        ObjectMapper objectMapper = new ObjectMapper();
        SaleDto saleDto = objectMapper.convertValue(map.get("sale"), SaleDto.class);


        saleDto.setAddrSeller(seller_id, seller_nick);
        System.out.println("값 들어왔는지 확인 : " + saleDto);

        Map<String, Object> tagMap = (Map<String, Object>) map.get("tag");
        List<String> tagContents = (List<String>) tagMap.get("contents");
        System.out.println("tag값 확인 : " + tagMap.size());

        // 각 해시태그를 반복하여 TagDto 객체 생성 및 tagList에 추가
        List<String> tagList = new ArrayList<>();
        for (String content : tagContents) {
            // '#' 기호를 제거하여 태그 내용만 추출
            String tagContent = content.substring(1);
            // TagDto 객체 생성
            // 생성된 TagDto를 tagList에 추가
            tagList.add(tagContent);
        }

        Map mapDto = new HashMap();
        mapDto.put("saleDto", saleDto);
        mapDto.put("tagList", tagList);

        // Service를 통해 글 등록 처리
        Long sal_no = saleService.write(mapDto);

        System.out.println("글 번호 : " + sal_no);
        String page = "/sale/read?no=" + sal_no;

        // 등록 후에는 다시 글 목록 페이지로 리다이렉트
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

// 글 수정을 완료하고 글을 등록하는 경우
@PostMapping("/update")
@ResponseBody
public ResponseEntity<String> update(@Valid @RequestBody Map<String, Object> map, Model model, HttpSession session, HttpServletRequest request) throws Exception {
    System.out.println("POST update");

    String seller_id = (String) session.getAttribute("userId");
    String seller_nick = (String) session.getAttribute("userNick");

    // ObjectMapper : JSON 형태를 JAVA 객체로 변환
    System.out.println(map.get("sale"));
    System.out.println(map.get("tag"));
    ObjectMapper objectMapper = new ObjectMapper();
    SaleDto saleDto = objectMapper.convertValue(map.get("sale"), SaleDto.class);


    saleDto.setAddrSeller(seller_id, seller_nick);
    System.out.println("값 들어왔는지 확인 : " + saleDto);

    Map<String, Object> tagMap = (Map<String, Object>) map.get("tag");
    List<String> tagContents = (List<String>) tagMap.get("contents");
    System.out.println("tag값 확인 : " + tagMap.size());

    // 각 해시태그를 반복하여 TagDto 객체 생성 및 tagList에 추가
    List<String> tagList = new ArrayList<>();
    for (String content : tagContents) {
        // '#' 기호를 제거하여 태그 내용만 추출
        String tagContent = content.substring(1);
        // TagDto 객체 생성
        // 생성된 TagDto를 tagList에 추가
        tagList.add(tagContent);
    }

    Map mapDto = new HashMap();
    mapDto.put("saleDto", saleDto);
    mapDto.put("tagList", tagList);

    // Service를 통해 글 등록 처리
    Long sal_no = saleService.update(mapDto);

    System.out.println("글 번호 : " + sal_no);
    String page = "/sale/read?no=" + sal_no;

    // 등록 후에는 다시 글 목록 페이지로 리다이렉트
    return new ResponseEntity<>(page, HttpStatus.OK);
}

    @RequestMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam Long no, Model model, HttpSession session) throws Exception {
        String seller_id = (String) session.getAttribute("userId");
        saleService.remove(no, seller_id);

        // 등록 후에는 다시 글 목록 페이지로 리다이렉트
        String page = "/sale/list";
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

//  ajax 요청을 처리해주는 URL등
    @PostMapping("/saleCategory2")
    @ResponseBody
    public ResponseEntity<List<SaleCategoryDto>> getSaleCategory2(@RequestParam String category1, Model model) throws Exception {
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
        // 검색어를 이용하여 주소를 검색
        try{
            return new ResponseEntity<>(administrativeDao.searchLetter(searchLetter), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ajax 지역에 따른 List 반환
    @RequestMapping("/salePage")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getSearchList(@RequestParam(defaultValue ="1") int page,
                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                             @RequestParam(required = false) String addr_cd,
                                                             @RequestParam(required = false) String sal_i_cd,
                                                             HttpSession session) throws Exception {

        System.out.println("page : " + page);
        System.out.println("pageSize : " + pageSize);

        // 선택하는 지역에 따른 List 반환
        try {
            if(addr_cd.equals("null")) {
                addr_cd = null;
            }
            if(sal_i_cd.equals("null")) {
                sal_i_cd = null;
            }

            Map map = new HashMap();
            map.put("addr_cd", addr_cd);
            map.put("sal_i_cd", sal_i_cd);

            int totalCnt = saleService.getCount(map);

            PageHandler ph = new PageHandler(totalCnt, page, pageSize);

            map.put("offset", ph.getOffset());
            map.put("pageSize", pageSize);

            List<SaleDto> saleList = saleService.getList(map);

            System.out.println(saleList);

            Map result = new HashMap();

            long startOfToday = getStartOfToday();

            result.put("ph", ph);
            result.put("saleList", saleList);
            result.put("startOfToday", startOfToday);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Collections.emptyMap(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public long getStartOfToday() {
        Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        return startOfToday.toEpochMilli();
    }


}