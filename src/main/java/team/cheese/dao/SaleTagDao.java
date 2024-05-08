package team.cheese.dao;

import team.cheese.domain.SaleCategoryDto;
import team.cheese.domain.SaleTagDto;

import java.util.List;

public interface SaleTagDao {

    // 전체 개수
    int count() throws Exception;

    int insert(SaleTagDto saleTagDto) throws Exception;

    int deleteAll() throws Exception;

    int resetAutoIncrement() throws Exception;
}
