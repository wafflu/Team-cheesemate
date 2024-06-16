package team.cheese.service;

import team.cheese.domain.FaqDto;

import java.util.List;
import java.util.Map;

public interface FaqService {

    List<FaqDto> getList() throws Exception ;

    List<FaqDto> getMajorFaqs(long que_id) throws Exception;

    List<FaqDto> getFaqsByCategoryId(long categoryId) throws Exception;

    List<FaqDto> searchFaqs(Map<String, Object> search) throws Exception ;

    String selectContents(long no) throws Exception;
}
