package team.cheese.service;

import team.cheese.domain.FaqDto;

import java.util.List;
import java.util.Map;

public interface FaqService {

    List<FaqDto> getList() ;

    List<FaqDto> getMajorFaqs(long que_id); // 변경된 부분

    List<FaqDto> getFaqsByCategoryId(long categoryId); // 변경된 부분

    List<FaqDto> searchFaqs(Map<String, Object> search) ;

    String selectContents(long no);
}
