package team.cheese.dao;

import team.cheese.domain.FaqDto;

import java.util.List;
import java.util.Map;

public interface FaqDao {
    int count();

    //전체 목록 조회
    List<FaqDto> selectAllFaq();
    //부분 목록 조회
    List<FaqDto> selectMajorFaq(Integer que_id);
    //검색어 조회
    List<FaqDto> searchFaqs(Map<String, Object> search);
    //title 클릭시 내용 조회
    String selectContents(Integer no);

    //faq글 삭제(관리자)
    int deleteAdmin(Integer id);
    //faq글 추가(관리자)
    int insertAdmin(FaqDto faq);
}
