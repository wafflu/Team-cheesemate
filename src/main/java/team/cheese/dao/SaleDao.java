package team.cheese.dao;

import java.util.List;

import team.cheese.domain.SaleDto;

public interface SaleDao {
    int count() throws Exception;

    int countUse() throws  Exception;

    // 판매글 전부 가져오기
    List<SaleDto> selectAll() throws Exception;

    // 판매글 번호를 이용하여 판매글 가져오기
    SaleDto select(Integer no) throws Exception;

    // 판매글 작성하는 경우
    int insert(SaleDto saleDto) throws  Exception;

    // 판매글 수정하는 경우
    int update(SaleDto saleDto) throws Exception;

    // 판매글 사용자가 삭제하는 경우
    int delete(SaleDto saleDto) throws Exception;

    // 판매글 관리자가 삭제하는 경우
    int adminState(SaleDto saleDto) throws  Exception;



}
