package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.MyPage.SearchCondition;
import team.cheese.domain.SaleDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int countUse() throws Exception {
        return session.selectOne(namespace + "countUse");
    }

    @Override
    public int countSale(Map map) throws Exception {

        return session.selectOne(namespace + "countSale", map);
    }

    @Override
    public List<SaleDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public List<SaleDto> selectSaleList(Map map) throws Exception {
        return session.selectList(namespace + "selectSales", map);
    }

    @Override
    public SaleDto select(Long no) throws Exception {
        return session.selectOne(namespace+"select", no);
    }

    @Override
    public int increaseViewCnt(Long no) throws Exception {
        return session.update(namespace+"increaseViewCnt", no);
    }

    @Override
    public int insert(SaleDto saleDto) throws Exception {
        String tx_s_cd = saleDto.getTx_s_cd();

        return session.insert(namespace + "insert", saleDto);
    }

    @Override
    public int insertSale(SaleDto saleDto) throws Exception {

        return session.insert(namespace + "insertSale", saleDto);
    }

    @Override
    public int update(SaleDto saleDto) throws Exception {
        Long no = saleDto.getNo();

        return session.update(namespace + "update", saleDto);
    }

    @Override
    public int delete(Long no, String seller_id) throws Exception {
        Map map = new HashMap();
        map.put("no", no);
        map.put("seller_id", seller_id);

        return session.update(namespace + "delete", map);
    }

    @Override
    public int deleteAll() throws Exception {
        return session.update(namespace + "deleteAll");
    }

    public int resetAutoIncrement() throws Exception {
        return session.update(namespace + "resetAutoIncrement");
    }


    @Override
    public int adminState(SaleDto saleDto) throws Exception {

        return session.update(namespace + "adminState", saleDto);
    }

    @Override
    public List<SaleDto> selectStandardAddr(String ur_id, int check_addr_cd) throws Exception {
        Map map = new HashMap();
		map.put("ur_id", ur_id);
        map.put("check_addr_cd", check_addr_cd);
        return session.selectList(namespace + "selectStandardAddr");
    }

    @Override
    public List<SaleDto> selectList(Map map) throws Exception {
        return session.selectList(namespace + "selectPage", map);
    }

    @Override
    public List<SaleDto> selectSearchPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace+"selectSearchPage",sc);
    }
    @Override
    public int selectSearchCount(SearchCondition sc) throws Exception {
        return session.selectOne(namespace+"selectSearchCount",sc);
    }


}
