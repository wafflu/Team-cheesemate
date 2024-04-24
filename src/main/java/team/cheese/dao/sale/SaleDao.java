package team.cheese.dao.sale;

import team.cheese.domain.sale.Sale;

import java.util.List;

public interface SaleDao{

    int count() throws Exception;

    // 판매글 전부 가져오기
    List<Sale> selectAll() throws Exception;
}
