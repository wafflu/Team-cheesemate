package team.cheese.Dao.MyPage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.Domain.MyPage.ReviewCommentDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewCommentDaoImpl implements ReviewCommentDao {
    @Autowired
    private SqlSession sqlSession;
    private static String namespace ="team.cheese.Dao.MyPage.ReviewCommentDao.";

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
    public int insert(ReviewCommentDTO reviewCommentDTO) throws Exception {
        return sqlSession.insert(namespace+"insert",reviewCommentDTO);
    }
    @Override
    public int update(ReviewCommentDTO reviewCommentDTO) throws Exception {
        return sqlSession.update(namespace+"update",reviewCommentDTO);
    }
    @Override
    public int incrementLikeCnt(Integer no) throws Exception {
        return sqlSession.update(namespace+"incrementLikeCnt",no);
    }
    @Override
    public int UserStateChange(ReviewCommentDTO reviewCommentDTO) throws Exception {
        Map map = new HashMap();
        map.put("ur_state",reviewCommentDTO.getUr_state());
        map.put("no",reviewCommentDTO.getNo());
        System.out.println(map);
        return sqlSession.update(namespace+"UserStateChange",map);
    }
    @Override
    public int AdminStateChange(ReviewCommentDTO reviewCommentDTO) throws Exception {
        Map map = new HashMap();
        map.put("ad_state",reviewCommentDTO.getAd_state());
        map.put("no",reviewCommentDTO.getNo());
        System.out.println(map);
        return sqlSession.update(namespace+"AdminStateChange",map);
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
