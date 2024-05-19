package team.cheese.dao;

import team.cheese.domain.QnaCategoryDto;

import java.util.List;

public interface QnaCategoryDao {
    int count() throws Exception;

    int MajorCount() throws Exception;

    List<QnaCategoryDto> selectMajorCategory() throws Exception;

    List<QnaCategoryDto> selectSubCategory(long que_cd) throws Exception; // 변경된 부분
}