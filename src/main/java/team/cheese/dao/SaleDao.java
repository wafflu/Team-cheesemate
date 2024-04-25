package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.SaleDto;

import java.util.HashMap;

@Repository
public class SaleDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.SaleDao.";

    public int insert_sale(SaleDto saleDto){
        return session.insert(namespace+"insert_sale", saleDto);
    }
}
