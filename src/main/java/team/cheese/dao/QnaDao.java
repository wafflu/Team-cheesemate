package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.QnaDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QnaDao {

    @Autowired
    SqlSession session;

    String namespace = "team.cheese.dao.QnaDao.";

    //모든 사용자가 작성한 목록을 표출
    public List<QnaDto> selectAll(){
        return session.selectList(namespace+"selectAll");
    }

    //사용자가 등록한 문의글을 번호로 문의글을 조회한다.
    public QnaDto selectByNo(Long no){
        return session.selectOne(namespace+"selectByNo", no);
    }

    //사용자가 작성한 목록을 표출
    public List<QnaDto> select(String ur_id){
        return session.selectList(namespace+"selectByUserId", ur_id);
    }

    //사용자가 등록한 문의글을 취소(삭제)한다.
    public int delete(Long no, String ur_id){
        Map map = new HashMap();
        map.put("no", no);
        map.put("ur_id", ur_id);
        return session.delete(namespace+"delete", map);
    }
    //사용자가 1:1문의하기를 통해 문의를 등록한다.
    public int insert(QnaDto qnaDto){
        return session.insert(namespace+"insert", qnaDto);
    }

    //사용자가 등록한 문의글을 수정한다.
    public int update(QnaDto qnaDto){
        return session.update(namespace+"update", qnaDto);
    }

    //관리자가 등록된 모든 qna를 조회한다.
    public List<QnaDto> selectAllQnaAdmin(){
        return session.selectList(namespace+"selectAllQnaAdmin");
    }


}
