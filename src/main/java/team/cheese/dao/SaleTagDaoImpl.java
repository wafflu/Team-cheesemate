package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.SaleCategoryDto;
import team.cheese.domain.SaleTagDto;

import javax.servlet.jsp.tagext.Tag;
import java.util.List;

@Repository
public class SaleTagDaoImpl implements SaleTagDao{
    @Autowired
    private SqlSession session;

    private static String namespace = "team.cheese.dao.SaleTagMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public List<SaleTagDto> selectSalNo(Long sal_no) throws Exception {
        return session.selectList(namespace + "selectSalNo", sal_no);
    }

    @Override
    public int insert(SaleTagDto saleTagDto) throws Exception {
        return session.insert(namespace + "insert", saleTagDto);
    }

    @Override
    public int delete(Long sal_no) throws Exception {

        return session.delete(namespace + "delete", sal_no);
    }

    @Override
    public int deleteAll() throws Exception {

        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int resetAutoIncrement() throws Exception {
        return session.update(namespace + "resetAutoIncrement");
    }



}
