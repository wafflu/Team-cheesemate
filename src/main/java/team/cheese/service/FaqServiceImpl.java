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
    public List<FaqDto> getList() {
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
    public List<FaqDto> getMajorFaqs(long que_id) {
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

    @Override
    public List<FaqDto> getFaqsByCategoryId(long categoryId) {
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

    // 검색어가 제목과 내용에 포함된 faq 목록을 가져온다.
    @Override
    public List<FaqDto> searchFaqs(Map<String, Object> search) {
        try{
            return faqDao.searchFaqs(search);
        } catch (Exception e) {
            throw new RuntimeException("faq 검색어 조회 에러", e);
        }
    }

    // faq 번호로 내용을 가져온다.
    @Override
    public String selectContents(long no) {
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
