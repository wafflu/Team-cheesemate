package team.cheese.service;

import team.cheese.domain.FaqDto;

import java.util.List;
import java.util.Map;

public interface FaqService {

    List<FaqDto> getList() throws Exception;
    List<FaqDto> getMajorFaqs(Integer que_id) throws Exception;

    List<FaqDto> getFaqsByCategoryId(Integer categoryId);

    List<FaqDto> searchFaqs(Map<String, Object> search) throws Exception;
    String selectContents(Integer no);
}
