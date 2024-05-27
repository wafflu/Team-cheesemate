package team.cheese.dao.MyPage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.MyPage.JjimDTO;
import team.cheese.domain.MyPage.UserInfoDTO;
import team.cheese.domain.SaleDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JjimDaoImpl implements JjimDao {
    @Autowired
    private SqlSession sqlSession;
    private static String namespace ="team.cheese.dao.MyPage.JjimDao.";

    @Override
    public JjimDTO findLike(JjimDTO jjimDTO) throws Exception {
        return sqlSession.selectOne(namespace+"findLike",jjimDTO);
    }

    @Override
    public int insertLike(JjimDTO jjimDTO) throws Exception {
        return sqlSession.insert(namespace+"insertLike",jjimDTO);
    }

    @Override
    public int deleteLike(JjimDTO jjimDTO) throws Exception {
        return sqlSession.delete(namespace+"deleteLike",jjimDTO);
    }

    @Override
    public int countAll(String buyer_id) throws Exception {
        return sqlSession.selectOne(namespace+"countAll",buyer_id);
    }

    @Override
    public int countLikes(Map map) throws Exception {
        return sqlSession.selectOne(namespace+"countLikes",map);
    }

    @Override
    public List<SaleDto> selectAllLike(Map map) throws Exception {
        return sqlSession.selectList(namespace+"selectAll",map);
    }

    @Override
    public int deleteAllLike(String buyer_id) throws Exception {
        return sqlSession.delete(namespace+"deleteAll",buyer_id);
    }

    @Override
    public int deleteSelectedSales(Map map) throws Exception {
        return sqlSession.delete(namespace+"deleteSelectedSales",map);
    }
}
