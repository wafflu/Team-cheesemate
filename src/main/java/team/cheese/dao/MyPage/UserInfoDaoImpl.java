package team.cheese.Dao.MyPage;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.Domain.MyPage.UserInfoDTO;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {
    @Autowired
    private SqlSession sqlSession;
    private static String namespace ="team.cheese.Dao.MyPage.UserInfoDao.";

    @Override
    public int count() throws Exception {
        return sqlSession.selectOne(namespace+"count");
    }

    @Override
    public UserInfoDTO select(String ur_id) throws Exception {
        return sqlSession.selectOne(namespace+"select",ur_id);
    }
    @Override
    public int insert(UserInfoDTO userInfoDTO) throws Exception {
        return sqlSession.insert(namespace+"insert",userInfoDTO);
    }
    @Override
    public int update(UserInfoDTO userInfoDTO) throws Exception {
        return sqlSession.update(namespace+"update",userInfoDTO);
    }

    @Override
    public int incrementViewCnt(String ur_id) throws Exception {
        return sqlSession.update(namespace+"incrementViewCnt",ur_id);
    }
    @Override
    public int incrementCompleteCnt(String ur_id) throws Exception {
        return sqlSession.update(namespace+"incrementCompleteCnt",ur_id);
    }
    @Override
    public int updateRvCmtCnt(String ur_id, int cnt) throws Exception {
        Map map = new HashMap();
        map.put("cnt",cnt);
        map.put("ur_id",ur_id);
        return sqlSession.update(namespace+"updateRvCmtCnt",map);
    }

    @Override
    public int incrementRptCnt(String ur_id) throws Exception {
        return sqlSession.update(namespace+"incrementRptCnt",ur_id);
    }

    @Override
    public Double starAverage(String ur_id) throws Exception {
        return sqlSession.selectOne(namespace+"starAverage",ur_id);
    }

    @Override
    public int delete(String ur_id) throws Exception {
        return sqlSession.delete(namespace+"delete",ur_id);
    }
    @Override
    public int deleteAll() throws Exception {
        return sqlSession.delete(namespace+"deleteAll");
    }

    @Override
    public int stateChange(String ur_id) throws Exception {
        return sqlSession.update(namespace+"stateChange",ur_id);
    }
}
