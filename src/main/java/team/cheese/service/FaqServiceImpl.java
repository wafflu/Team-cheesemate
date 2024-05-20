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
            return faqDao.selectAllFaq();
        } catch (Exception e) {
            throw new RuntimeException("faq 목록 조회 에러", e);
        }
    }

    // 카테고리별 faq 목록을 가져온다.
    @Override
    public List<FaqDto> getMajorFaqs(long que_id) {
        try {
            return faqDao.selectMajorFaq(que_id);
        } catch (Exception e) {
            throw new RuntimeException("faq 목록 조회 에러", e);
        }
    }

    @Override
    public List<FaqDto> getFaqsByCategoryId(long categoryId) {
        try {
            if (categoryId == 6) {
                return faqDao.selectAllFaq();
            } else {
                return faqDao.selectMajorFaq(categoryId);
            }
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
            return faqDao.selectContents(no);
        } catch (Exception e) {
            throw new RuntimeException("faq 번호 조회 에러", e);
        }
    }
}
