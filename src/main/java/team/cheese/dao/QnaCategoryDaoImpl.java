package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.QnaCategoryDto;

import java.util.List;

@Repository
public class QnaCategoryDaoImpl implements QnaCategoryDao {

    @Autowired
    private SqlSession session;

    private String namespace = "team.cheese.dao.QnaCategoryDao.";

    @Override
    public int count() throws Exception{return session.selectOne(namespace+"count");}
    @Override
    public int MajorCount() throws Exception{return session.selectOne(namespace+"MajorCount");}
    @Override
    public List<QnaCategoryDto> selectMajorCategory() throws Exception{return session.selectList(namespace+"selectMajorCategory");}
    @Override
    public List<QnaCategoryDto> selectSubCategory(Integer que_cd)throws Exception{return session.selectList(namespace+"selectSubCategory",que_cd);}
}