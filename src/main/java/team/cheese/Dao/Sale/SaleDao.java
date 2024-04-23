package team.cheese.dao.Sale;

import team.cheese.Domain.Sale.Sale;

import java.util.List;

public interface SaleDao{

    int count() throws Exception;

    // 판매글 전부 가져오기
    List<Sale> selectAll() throws Exception;
}
