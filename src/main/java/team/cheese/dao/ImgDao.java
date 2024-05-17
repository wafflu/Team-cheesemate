package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.ImgDto;

import java.util.ArrayList;
import java.util.HashMap;

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
}
