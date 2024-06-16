package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SaleHistoryDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.SaleHistoryMapper.";

    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    public int deleteAll() throws Exception {

        return session.delete(namespace + "deleteAll");
    }

    public int resetAutoIncrement() throws Exception {
        return session.update(namespace + "resetAutoIncrement");
    }

}
