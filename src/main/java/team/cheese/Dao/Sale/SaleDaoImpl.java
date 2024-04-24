package team.cheese.Dao.Sale;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.Domain.Sale.Sale;

import java.util.List;

@Repository
public class SaleDaoImpl implements SaleDao{
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.SaleMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public List<Sale> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }
}
