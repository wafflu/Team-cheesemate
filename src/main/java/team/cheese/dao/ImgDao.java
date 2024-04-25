package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.ImgVO;

import java.util.HashMap;
import java.util.List;

@Repository
public class ImgDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.ImgDao.";

    public int insert_img(ImgVO img){
        return session.insert(namespace+"insert_img", img);
    }

    public List<ImgVO> selectAll_img(){
        return session.selectList(namespace+"select_img");
    }

    public List<ImgVO> selectImg_list(HashMap map){
        return session.selectList(namespace+"select_img_in", map);
    }

    public int update_img(HashMap map){
        return session.update(namespace+"state_change_img", map);
    }
}
