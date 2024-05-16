package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.AddrCdDto;

import java.util.List;

@Repository
public class AddrCdDaoImpl implements AddrCdDao {

    @Autowired
    private SqlSession sqlSession;

    private String namespace = "team.cheese.dao.AddrCdDao.";

    @Override
    public void deleteAllAddrCd() {
        sqlSession.delete(namespace + "deleteAllAddrCd");
    }

    @Override
    public int getAddrCdCnt() {
        return sqlSession.selectOne(namespace + "getAddrCdCnt");
    }

    @Override
    public List<AddrCdDto> getAllAddrCd() {
        return sqlSession.selectList(namespace + "getAllAddrCd");
    }

    @Override
    public List<AddrCdDto> getAddrCdByUserId(String userId) {
        return sqlSession.selectList(namespace + "getAddrCdByUserId", userId);
    }
}
