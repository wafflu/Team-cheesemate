package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.SaleDto;

import java.util.HashMap;

@Repository
public class PublicDao {
    @Autowired
    SqlSession session;

    private static String namespace = "team.cheese.dao.PublicDao.";

    public int updateState(HashMap map){
        return session.update(namespace+"StateChange", map);
    }

    public SaleDto select_state_check(HashMap map){
        return session.selectOne(namespace+"select_state_check", map);
    }
}
