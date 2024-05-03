package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.SaleDto;

import java.util.HashMap;
import java.util.List;

@Repository
public class SaleDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.SaleDao.";

    public int insert_sale(SaleDto saleDto){
        return session.insert(namespace+"insert", saleDto);
    }

    public List<SaleDto> select_all(){
        return session.selectList(namespace+"select_all");
    }

    public SaleDto select(int no){
        return session.selectOne(namespace+"select", no);
    }
}
