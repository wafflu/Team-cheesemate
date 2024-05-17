package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.AdminDto;

import java.util.List;

@Repository
public class AdminImpl implements AdminDao {

    @Autowired
    private SqlSession sqlSession;

    private String namespace = "team.cheese.dao.AdminDao.";

    @Override
    public int getAdminCnt() {
        return sqlSession.selectOne(namespace + "getAdminCnt");
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return sqlSession.selectList(namespace + "getAllAdmins");
    }

    @Override
    public AdminDto getAdminById(String id) {
        return sqlSession.selectOne(namespace + "getAdminById", id);
    }

    @Override
    public List<String> getAllAdminsId() {
        return sqlSession.selectList(namespace + "getAllAdminsId");
    }
}
