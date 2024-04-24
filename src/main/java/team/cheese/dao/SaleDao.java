package team.cheese.dao;

import java.util.List;

import team.cheese.domain.SaleDto;

public interface SaleDao {
    int count() throws Exception;

    // 판매글 전부 가져오기
    List<SaleDto> selectAll() throws Exception;

    // 판매글 번호를 이용하여 판매글 가져오기
    SaleDto select(Integer no) throws Exception;

    // 판매글 작성하는 경우
    int insert(SaleDto saleDto) throws  Exception;
}
