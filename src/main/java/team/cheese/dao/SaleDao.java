package team.cheese.dao;

import java.util.List;
import java.util.Map;

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
}
