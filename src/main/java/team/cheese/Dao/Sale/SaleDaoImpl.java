package team.cheese.Dao.Sale;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.Dao.Sale.SaleDao;
import team.cheese.Domain.Sale.SaleDto;

import java.util.List;

@Repository
public class SaleDaoImpl implements SaleDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.Dao.Sale.SaleDao.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public List<SaleDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public SaleDto select() throws Exception {
        return session.selectOne(namespace + "select");
    }


}
