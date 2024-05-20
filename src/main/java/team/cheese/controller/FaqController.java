package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.cheese.domain.FaqDto;
import team.cheese.service.FaqService;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//만약 FAQ 글이 활성화 STATE = Y이면 표출, N면 표출안함

@Controller
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;

    //자주묻는 질문을 이동
    @GetMapping("/list")
    public String list() {return "faqBoardList";}

/*
FAQ 목록 조회하기
    카테고리 번호에 따라 FAQ 목록을 조회한다.
    카테고리 번호가 6인 경우 전체 목록을 조회하고, 1-5 사이의 번호인 경우 해당 카테고리의 목록을 조회한다.
    조회된 목록을 ResponseEntity 객체로 반환한다.
*/
@GetMapping("/major")
public ResponseEntity<List<FaqDto>> getFaqsByCategoryId(@RequestParam("queId") Integer queId) {
        List<FaqDto> faqs = faqService.getFaqsByCategoryId(queId);
        return ResponseEntity.ok(faqs);
}

    /*
     * 키워드를 입력받아 해당 키워드가 포함된 FAQ 목록을 조회한다.
     * 검색할 키워드를 클라이언트로부터 받는다.
     * 검색된 FAQ 목록을 ResponseEntity 객체로 반환한다.
     * 검색 결과는 List<FaqDto> 타입으로, 검색 성공 시 HTTP 상태 코드 200과 함께 목록 반환.
     * 검색 실패 시 예외를 콘솔에 출력하고 빈 목록 반환 가능.
     */
    //카테고리에서 title과 contents를 검색

    @GetMapping("/search")
    public ResponseEntity<List<FaqDto>> searchFaqs(@RequestParam("keyword") String keyword) {
        Map<String, Object> searchKeywords = new HashMap<>();
        searchKeywords.put("keyword", keyword);
        List<FaqDto> searchResults = null;
        searchResults = faqService.searchFaqs(searchKeywords);
        return ResponseEntity.ok(searchResults);
    }

    /*
    title 클릭 시 content 조회하기
        FAQ번호(no)를 사용하여 특정 FAQ의 내용을 조회한다.
        조회된 내용을 텍스트 형식으로 클라이언트에 반환한다.
        FAQ번호에 해당하는 내용을 문자열로 반환한다.
    */

    @GetMapping("/getContents")
    public ResponseEntity<String> getContents(@RequestParam("no") Integer no) {
        String content = faqService.selectContents(no);
        return ResponseEntity.ok()
                .contentType(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8))
                .body(content);
        //return ResponseEntity.ok().body(content); // 위 코드 시 문자열 인코딩 문제발생
    }
}
