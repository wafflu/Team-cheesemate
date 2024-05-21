package team.cheese.dao;

import java.util.List;
import java.util.Map;

import team.cheese.domain.MyPage.SearchCondition;
import team.cheese.domain.SaleDto;

public interface SaleDao {
    int count() throws Exception;

    int countUse() throws  Exception;

    int countSale(Map map) throws  Exception;

    // 판매글 전부 가져오기
    List<SaleDto> selectAll() throws Exception;

    // 사용자가 속한 지역, 카테고리의 판매글 전부 가져오기
    List<SaleDto> selectSaleList(Map map) throws Exception;

    // 판매글 번호를 이용하여 판매글 가져오기
    SaleDto select(Long no) throws Exception;

    // 판매글 조회수 증가시키기
    int increaseViewCnt(Long no) throws  Exception;

    // 판매글 끌어올리기 횟수 증가시키기
    int increaseHoistingCnt(Long no) throws Exception;

    // 판매글 작성하는 경우(세션고려X)
    int insert(SaleDto saleDto) throws  Exception;

    // 판매글 작성하는 경우(세션고려O)
    int insertSale(SaleDto saleDto) throws  Exception;

    // 판매글 수정하는 경우
    int update(SaleDto saleDto) throws Exception;

    // 판매글 사용자가 삭제하는 경우
    int delete(Long no, String seller_id) throws Exception;

    int deleteAll() throws Exception;

    int resetAutoIncrement() throws Exception;

    // 판매글 관리자가 삭제하는 경우
    int adminState(SaleDto saleDto) throws  Exception;

    List<SaleDto> selectList(Map map) throws Exception;

    int updateSaleSCd(Map map) throws Exception;

    // 조건대로 판매글 목록 조회
    List<SaleDto> selectSearchPage(SearchCondition sc) throws Exception;

    int selectSearchCount(SearchCondition sc) throws Exception;

    // 판매글 끌어올리기
    int hoistingSale(Map map) throws Exception;

    //새로 추가!!!! 후기글 작성시 상태 업데이트
    int reviewState(Long no) throws Exception;

    //구매자가 판매글 예약/구매시
    int buySale(SaleDto saleDto) throws Exception;
}
