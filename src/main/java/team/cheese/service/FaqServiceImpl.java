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
        return faqDao.selectAllFaq();
    }

    // 카테고리별 faq 목록을 가져온다.
    @Override
    public List<FaqDto> getMajorFaqs(long que_id) { // 변경된 부분
        return faqDao.selectMajorFaq(que_id); // 변경된 부분
    }

    @Override
    public List<FaqDto> getFaqsByCategoryId(long categoryId) { // 변경된 부분
        if (categoryId == 6) {
            return faqDao.selectAllFaq();
        } else {
            return faqDao.selectMajorFaq(categoryId); // 변경된 부분
        }
    }

    // 검색어가 제목과 내용에 포함된 faq 목록을 가져온다.
    @Override
    public List<FaqDto> searchFaqs(Map<String, Object> search) {
        return faqDao.searchFaqs(search);
    }

    // faq 번호로 내용을 가져온다.
    @Override
    public String selectContents(long no) { // 변경된 부분
        return faqDao.selectContents(no); // 변경된 부분
    }
}
