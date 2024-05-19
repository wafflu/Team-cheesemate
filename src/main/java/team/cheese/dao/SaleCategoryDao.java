package team.cheese.dao;

import team.cheese.domain.SaleCategoryDto;

import java.util.List;

public interface SaleCategoryDao {
    int count() throws Exception;

    List<SaleCategoryDto> selectAll() throws Exception;

    List<SaleCategoryDto> selectCategory1() throws Exception;

    List<SaleCategoryDto> selectCategory2(String category1) throws Exception;

    List<SaleCategoryDto> selectCategory3(String category2) throws Exception;
}
