package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.dao.FaqDao;
import team.cheese.domain.FaqDto;

import java.util.List;
import java.util.Map;

@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    FaqDao faqDao;

    // faq 전체 목록을 가져온다.
    @Override
    public List<FaqDto> getList() throws Exception {
        try{
            List<FaqDto> faqs = faqDao.selectAllFaq();
            if (faqs == null || faqs.isEmpty()) {
                throw new RuntimeException("FAQ 목록이 없습니다.");
            }
            return faqs;
        } catch (Exception e) {
            throw new RuntimeException("faq 목록 조회 에러", e);
        }
    }

    // 카테고리별 faq 목록을 가져온다.
    @Override
    public List<FaqDto> getMajorFaqs(long que_id) throws Exception {
        try {
            if (que_id <= 0) {
                throw new IllegalArgumentException("유효하지 않은 카테고리 ID입니다.");
            }
            List<FaqDto> faqs = faqDao.selectMajorFaq(que_id);
            if (faqs == null || faqs.isEmpty()) {
                throw new RuntimeException("해당 카테고리에 FAQ가 없습니다.");
            }
            return faqs;
        } catch (Exception e) {
            throw new RuntimeException("faq 목록 조회 에러", e);
        }
    }

    /*
        카테고리 번호에 따라 FAQ 목록을 조회한다.
            카테고리 번호가 6인 경우 전체 목록을 조회한다.
            1-5 사이의 번호인 경우 해당 카테고리의 목록을 조회한다.
    */
    @Override
    public List<FaqDto> getFaqsByCategoryId(long categoryId) throws Exception {
        try {
            if (categoryId < 1 || categoryId > 6) {
                throw new IllegalArgumentException("유효하지 않은 카테고리 ID입니다.");
            }
            List<FaqDto> faqs;
            if (categoryId == 6) {
                faqs = faqDao.selectAllFaq();
            } else {
                faqs = faqDao.selectMajorFaq(categoryId);
            }
            if (faqs == null || faqs.isEmpty()) {
                throw new RuntimeException("해당 카테고리에 FAQ가 없습니다.");
            }
            return faqs;
        } catch (Exception e) {
            throw new RuntimeException("faq 카테고리 조회 에러", e);
        }
    }
    /*
      키워드를 입력받아 해당 키워드가 포함된 FAQ 목록을 조회한다.
            검색할 키워드를 클라이언트로부터 받는다.
            검색된 FAQ 목록을 ResponseEntity 객체로 반환한다.
            검색 결과는 List<FaqDto> 타입으로, 검색 성공 시 목록 반환.

     */
    @Override
    public List<FaqDto> searchFaqs(Map<String, Object> search) throws Exception {
        try{
            return faqDao.searchFaqs(search);
        } catch (Exception e) {
            throw new RuntimeException("faq 검색어 조회 에러", e);
        }
    }
    /*
        FAQ번호(no)를 사용하여 특정 FAQ의 내용을 조회한다.
        조회된 내용을 텍스트 형식으로 클라이언트에 반환한다.
        FAQ번호에 해당하는 내용을 문자열로 반환한다.
    */
    @Override
    public String selectContents(long no) throws Exception {
        try {
            if (no <= 0) {
                throw new IllegalArgumentException("유효하지 않은 FAQ 번호입니다.");
            }
            String content = faqDao.selectContents(no);
            if (content == null || content.trim().isEmpty()) {
                throw new RuntimeException("FAQ 내용이 없습니다.");
            }
            return content;
        } catch (Exception e) {
            throw new RuntimeException("faq 번호 조회 에러", e);
        }
    }
}
