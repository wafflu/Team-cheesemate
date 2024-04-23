package team.cheese.Dao.Sale;

import team.cheese.Domain.Sale.SaleDto;

import java.util.List;

public interface SaleDao{

    int count() throws Exception;

    // 판매글 전부 가져오기
    List<SaleDto> selectAll() throws Exception;


    // 판매글 하나 가져오기
    SaleDto select() throws Exception;
}
