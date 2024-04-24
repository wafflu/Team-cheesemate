package team.cheese.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.cheese.domain.SaleDto;

@Repository
public class SaleDaoImpl implements SaleDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.SaleMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public List<SaleDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public SaleDto select(Integer no) throws Exception {
        return session.selectOne(namespace+"select", no);
    }

    @Override
    public int insert(SaleDto saleDto) throws Exception {
        char tx_s_cd = saleDto.getTx_s_cd();

        if(tx_s_cd == 'S') {    // 판매하려는 경우
            return session.insert(namespace + "insertSale", saleDto);
        }
        // 나눔하려는 경우
        return session.insert(namespace + "insertFree", saleDto);
    }

}
