package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.EventDto;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SaleDto;
import team.cheese.domain.UserInfoDTO;

import java.util.HashMap;
import java.util.List;

@Repository
public class ImgDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.ImgDao.";
    //이미지 테이블 작성
    public int insert_img(ImgDto img){
        return session.insert(namespace+"insert_img", img);
    }

    // 교차테이블 작성
    public int insert_cross(HashMap map){
        return session.update(namespace+"insert_cross", map);
    }

    // 썸네일 이미지 리스트가져오기 -> 추후 변경될수 있음
    public List<ImgDto> select_s_imglist(){
        return session.selectList(namespace+"select_s_imglist");
    }

    // 본문이미지 리스트 불러오기
    public List<ImgDto> select_w_imglist(HashMap map){
        return session.selectList(namespace+"select_w_imglist", map);
    }

    // 이벤트, 마이페이지 이미지 불러오기
    public ImgDto select_oneimg(HashMap map){
        return session.selectOne(namespace+"select_oneimg", map);
    }

    // 이미지 상태변화
    public int delete_img_state(HashMap map){
        return session.update(namespace+"delete_img_state", map);
    }

    // 썸네일 이미지 1개 불러오기
    public ImgDto select_one_simg(int no){
        return session.selectOne(namespace+"select_one_simg", no);
    }

    //-- 예외
    public int delete_cross_all(String tb_name){
        return session.delete(namespace+"delete_cross_all", tb_name);
    }
    public int delete_img_all(){
        return session.delete(namespace+"delete_img_all");
    }
    public List<ImgDto> selectAll_img(){
        return session.selectList(namespace+"select_img");
    }

    public List<SaleDto> selectsimg(){
        return session.selectList(namespace+"select_s_imglist2");
    }

    public List<EventDto> selecteimg(){
        return session.selectList(namespace+"select_e_imglist2");
    }

    public UserInfoDTO selectuimg(String user_id){
        return session.selectOne(namespace+"select_u_imglist2", user_id);
    }
}
