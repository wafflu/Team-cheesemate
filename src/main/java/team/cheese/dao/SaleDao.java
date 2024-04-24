package team.cheese.dao;

import java.util.List;

import team.cheese.domain.SaleDto;

public interface SaleDao {
    int count() throws Exception;

    // 판매글 전부 가져오기
    List<SaleDto> selectAll() throws Exception;
}
