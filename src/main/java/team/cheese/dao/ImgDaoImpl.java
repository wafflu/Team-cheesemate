package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ImgDaoImpl implements ImgDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.ImgDao.";
    //이미지 테이블 작성
    @Override
    public int insert(ImgDto img){
        return session.insert(namespace+"insert_img", img);
    }

    //이미지 그룹
    @Override
    public int insert(HashMap map){
        return session.insert(namespace+"insert_group_img", map);
    }

    //그룹 번호를 수동으로 매기기
    @Override
    public int select_group_max(){
        return session.selectOne(namespace+"select_group_max");
    }

    @Override
    public ArrayList<ImgDto> select_all_img(){
        List<ImgDto> list = session.selectList(namespace+"select_all_img");
        return new ArrayList<>(list);
    }
    @Override
    public ArrayList<ImgDto> select_img(int gno){
        List<ImgDto> list = session.selectList(namespace+"select_img", gno);
        return new ArrayList<>(list);
    }

    @Override
    public int update(HashMap map){
        return session.update(namespace+"update_img_state", map);
    }

    //테스트 영역

    @Override
    public int delete(String tb_name){
        return session.delete(namespace+"delete_table", tb_name);
    }

    @Override
    public int count(String tb_name){
        return session.selectOne(namespace+"count", tb_name);
    }

}
