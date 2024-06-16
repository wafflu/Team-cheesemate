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

@Controller
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;

    //자주묻는 질문으로 이동
    @GetMapping("/list")
    public String list() {return "faqBoardList";}

    //FAQ 목록 조회하기
    @GetMapping("/major")
    public ResponseEntity<List<FaqDto>> getFaqsByCategoryId(@RequestParam("queId") Integer queId) throws Exception {
        List<FaqDto> faqs = faqService.getFaqsByCategoryId(queId);
        return ResponseEntity.ok(faqs);
    }

    //카테고리에서 title과 contents를 검색
    @GetMapping("/search")
    public ResponseEntity<List<FaqDto>> searchFaqs(@RequestParam("keyword") String keyword) throws Exception {
        Map<String, Object> searchKeywords = new HashMap<>();
        searchKeywords.put("keyword", keyword);
        List<FaqDto> searchResults = null;
        searchResults = faqService.searchFaqs(searchKeywords);
        return ResponseEntity.ok(searchResults);
    }


//    title 클릭 시 content 조회하기
    @GetMapping("/getContents")
    public ResponseEntity<String> getContents(@RequestParam("no") Integer no) throws Exception {
        String content = faqService.selectContents(no);
        return ResponseEntity.ok()
                .contentType(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8))
                .body(content);
        //return ResponseEntity.ok().body(content); // 코드 시 문자열 인코딩 문제발생
    }

}
