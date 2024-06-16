package team.cheese.dao;

import team.cheese.domain.ImgDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ImgDao {
    //이미지 테이블 작성
    int insert(ImgDto img);

    //이미지 그룹
    int insert(HashMap map);

    //그룹 번호를 수동으로 매기기
    int select_group_max();

    ArrayList<ImgDto> select_all_img();

    ArrayList<ImgDto> select_img(int gno);

    int update(HashMap map);

    int delete(String tb_name);

    int count(String tb_name);

    List<ImgDto> select_css(String imgtype);
}
