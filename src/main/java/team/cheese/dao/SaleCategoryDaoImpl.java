package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.SaleCategoryDto;

import java.util.List;

@Repository
public class SaleCategoryDaoImpl implements SaleCategoryDao{
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.SaleCategoryMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public List<SaleCategoryDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public List<SaleCategoryDto> selectCategory1() throws Exception {
        return session.selectList(namespace + "selectCategory1");
    }

    @Override
    public List<SaleCategoryDto> selectCategory2() throws Exception {
        return session.selectList(namespace + "selectCategory2");
    }

    @Override
    public List<SaleCategoryDto> selectCategory3() throws Exception {
        return session.selectList(namespace + "selectCategory3");
    }
}
