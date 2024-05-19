package team.cheese.dao.MyPage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.MyPage.ReviewCommentDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewCommentDaoImpl implements ReviewCommentDao {
    @Autowired
    private SqlSession sqlSession;
    private static String namespace ="team.cheese.dao.MyPage.ReviewCommentDao.";

    @Override
    public int count(String sal_id) throws Exception {
        return sqlSession.selectOne(namespace+"count",sal_id);
    }

    @Override
    public ReviewCommentDTO select(Integer no) throws Exception {
        return sqlSession.selectOne(namespace+"select",no);
    }
    @Override
    public List<ReviewCommentDTO> selectAll(String sal_id) throws Exception {
        return sqlSession.selectList(namespace+"selectAll",sal_id);
    }

    @Override
    public List<ReviewCommentDTO> selectPage(Map map) throws Exception {
        return sqlSession.selectList(namespace+"selectPage",map);
    }

    @Override
    public int insert(ReviewCommentDTO reviewCommentDTO) throws Exception {
        return sqlSession.insert(namespace+"insert",reviewCommentDTO);
//        throw new Exception("test222");
    }
    @Override
    public int update(ReviewCommentDTO reviewCommentDTO) throws Exception {
        return sqlSession.update(namespace+"update",reviewCommentDTO);
    }
    @Override
    public int stateChange(String buy_id) throws Exception {
        return sqlSession.update(namespace+"stateChange",buy_id);
    }

    @Override
    public int delete(String buy_id, Integer no) throws Exception {
        Map map = new HashMap();
        map.put("buy_id",buy_id);
        map.put("no",no);
        return sqlSession.delete(namespace+"delete",map);
    }
    @Override
    public int deleteAll(String sal_id) throws Exception {
        return sqlSession.delete(namespace+"deleteAll",sal_id);
    }

}
