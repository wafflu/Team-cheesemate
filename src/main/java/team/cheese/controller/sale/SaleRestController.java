package team.cheese.controller.sale;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.dao.*;
import team.cheese.domain.*;
import team.cheese.entity.PageHandler;
import team.cheese.service.sale.SaleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.*;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping(value = "/sale")
public class SaleRestController {
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


    // 글쓰기 완료하고 글을 등록하는 경우
    @PostMapping("/insert")
    public ResponseEntity<String> write(@RequestBody Map<String, Object> map, Model model, HttpSession session, HttpServletRequest request) throws Exception {

        String seller_id = (String) session.getAttribute("userId");
        String seller_nick = (String) session.getAttribute("userNick");

        // ObjectMapper : JSON 형태를 JAVA 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        SaleDto saleDto = objectMapper.convertValue(map.get("sale"), SaleDto.class);
        saleDto.setSeller_id(seller_id);
        saleDto.setSeller_nick(seller_nick);

        // 유효성 검사 진행
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<SaleDto>> violations = validator.validate(saleDto);

        Map<String, Object> tagMap = (Map<String, Object>) map.get("tag");
        List<String> tagContents = (List<String>) tagMap.get("contents");

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

        String page = "/sale/read?no=" + sal_no;

        // 등록 후에는 다시 글 목록 페이지로 리다이렉트
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    // 글 수정을 완료하고 글을 등록하는 경우
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Map<String, Object> map, Model model, HttpSession session, HttpServletRequest request) throws Exception {
        System.out.println("POST update");

        String seller_id = (String) session.getAttribute("userId");
        String seller_nick = (String) session.getAttribute("userNick");

        // ObjectMapper : JSON 형태를 JAVA 객체로 변환
        System.out.println(map.get("sale"));
        System.out.println(map.get("tag"));
        ObjectMapper objectMapper = new ObjectMapper();
        SaleDto saleDto = objectMapper.convertValue(map.get("sale"), SaleDto.class);

        saleDto.setAddrSeller(seller_id, seller_nick);

        // 유효성 검사 진행
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<SaleDto>> violations = validator.validate(saleDto);

        Map<String, Object> tagMap = (Map<String, Object>) map.get("tag");
        List<String> tagContents = (List<String>) tagMap.get("contents");

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


    //  ajax 요청을 처리해주는 URL등
    @PostMapping("/saleCategory2")
    public ResponseEntity<List<SaleCategoryDto>> getSaleCategory2(@RequestParam String category1, Model model) throws Exception {
        List<SaleCategoryDto> saleCategoryDto = saleService.selectCategory2(category1);
        return new ResponseEntity<>(saleCategoryDto, HttpStatus.OK);
    }

    // ajax 판매 카테고리 처리(중분류)
    @RequestMapping("/saleCategory3")
    public ResponseEntity<List<SaleCategoryDto>> getSaleCategory3(@RequestParam String category2, Model model) throws Exception {
        List<SaleCategoryDto> saleCategoryDto = saleService.selectCategory3(category2);
        return new ResponseEntity<>(saleCategoryDto, HttpStatus.OK);
    }

    // ajax 주소 검색
    @RequestMapping("/searchLetter")
    public ResponseEntity<List<AdministrativeDto>> getAdministrative(@RequestParam String searchLetter, Model model) throws Exception {
        // 검색어를 이용하여 주소를 검색
        return new ResponseEntity<>(administrativeDao.searchLetter(searchLetter), HttpStatus.OK);
    }

    // ajax로 판매글 list 읽어옴
    @RequestMapping("/salePage")
    public ResponseEntity<Map<String, Object>> getSearchList(@RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                             @RequestParam(required = false) String addr_cd,
                                                             @RequestParam(required = false) String sal_i_cd,
                                                             HttpSession session) throws Exception {

        // 선택하는 지역에 따른 List 반환
        if (addr_cd.equals("null")) {
            addr_cd = null;
        }
        if (sal_i_cd.equals("null")) {
            sal_i_cd = null;
        }

        Map map = new HashMap();
        map.put("addr_cd", addr_cd);
        map.put("sal_i_cd", sal_i_cd);

        int totalCnt = saleService.getCount(map);
        System.out.println("totalCnt : " + totalCnt);

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
    }

    // ajax 주소 검색
    @RequestMapping("/updateSaleSCd")
    public ResponseEntity<String> updateSaleSCd(@RequestParam Long no,
                                                @RequestParam String sal_s_cd,
                                                @RequestParam String seller_id) throws Exception {
        // 검색어를 이용하여 주소를 검색
        saleService.updateSaleSCd(no, sal_s_cd, seller_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public long getStartOfToday() {
        Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        return startOfToday.toEpochMilli();
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
        return new ResponseEntity<>("중복 키 오류가 발생하였습니다: " + e.getMessage(), headers, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
        return new ResponseEntity<>("제약 조건 위반 오류가 발생하였습니다: " + e.getMessage(), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
        return new ResponseEntity<>("SQL 오류가 발생하였습니다: " + e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
        return new ResponseEntity<>("데이터 접근 오류가 발생하였습니다: " + e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        // Validation 오류
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
        return new ResponseEntity<>("등록 중 오류가 발생하였습니다: " + e.getMessage(), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
        return new ResponseEntity<>("잘못된 인자가 전달되었습니다: " + e.getMessage(), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
        return new ResponseEntity<>("내부 서버 오류가 발생하였습니다: " + e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // DuplicateKeyException : 중복 키 예외 -> HttpStatus.CONFLICT
    // ConstraintViolationException : 제약 조건 위반 예외 -> HttpStatus.BAD_REQUEST
    // SQLException : SQL 예외 -> HttpStatus.INTERNAL_SERVER_ERROR
    // DataAccessException : 데이터 접근 예외 -> HttpStatus.INTERNAL_SERVER_ERROR
    // NullPointerException : Null값 시 발생하는 예외 -> HttpStatus.BAD_REQUEST
    // IllegalArgumentException : 런타임 에러 시 발생하는 예외 -> HttpStatus.BAD_REQUEST
    // Exception -> HttpStatus.INTERNAL_SERVER_ERROR
}