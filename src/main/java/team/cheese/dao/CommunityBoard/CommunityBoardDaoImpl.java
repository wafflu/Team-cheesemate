package team.cheese.dao.CommunityBoard;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import team.cheese.domain.CommunityBoard.CommunityBoardDto;
import team.cheese.domain.MyPage.ReviewCommentDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class CommunityBoardDaoImpl implements CommunityBoardDao {

  @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.CommunityBoard.BoardMapper.";


    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }



    @Override
    public int insert(CommunityBoardDto communityBoardDto ) throws Exception {
        return session.insert(namespace + "insert_community_board", communityBoardDto);
    }



    @Override
    public List<CommunityBoardDto> selectAll(Character ur_state) throws Exception {
        return session.selectList(namespace +"selectAll",ur_state);
    }

    @Override
    public List<CommunityBoardDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage",map);
    }

    @Override
    public CommunityBoardDto select(Integer no) throws Exception {
        return session.selectOne(namespace + "select", no);
    }


    @Override
    public int update(CommunityBoardDto communityBoardDto) throws Exception {
        return session.update(namespace + "update", communityBoardDto);
    }

    @Override
    public int delete(Integer no) throws Exception {
        return session.delete(namespace + "delete", no);
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace +"deleteAll");
    }

    @Override
    public int increaseViewCnt(Integer no) throws Exception {
        return session.update(namespace+"increaseViewCnt", no);
    }

    @Override
    public List<CommunityBoardDto> getTopTen() throws Exception {
        return session.selectList(namespace + "getTopTen");
    }

    @Override
    public int userChangeState(CommunityBoardDto communityBoardDto) throws Exception {
        return session.update(namespace + "userChangeState", communityBoardDto);
    }

    @Override
    public int totalLikeCount(Integer no) throws Exception {
        return session.update(namespace + "increaseLikeCnt", no);
    }

    @Override
    public int updateCommentCnt(Integer no,int cnt) throws Exception {
        HashMap map = new HashMap();
        map.put("no", no);
        map.put("cnt", cnt);
        return session.update(namespace + "updateCommentCnt", map);
    }

    public List<CommunityBoardDto> selectPageByCategory(Map<String, Object> params) {
        return session.selectList(namespace + "selectPageByCategory", params);
    }

    @Override
    public int selectCountByCategory(String category) {
        return session.selectOne(namespace + "selectCountByCategory", category);

    }

}
