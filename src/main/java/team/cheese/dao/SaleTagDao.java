package team.cheese.dao;

import team.cheese.domain.SaleCategoryDto;
import team.cheese.domain.SaleTagDto;

import java.util.List;

public interface SaleTagDao {

    // 전체 개수
    int count() throws Exception;

    // 전체 select 하기
    List<SaleTagDto> selectAll() throws Exception;

    //
    List<SaleCategoryDto> selectCategory1() throws Exception;

    List<SaleCategoryDto> selectCategory2(String category1) throws Exception;

    List<SaleCategoryDto> selectCategory3(String category2) throws Exception;
}
