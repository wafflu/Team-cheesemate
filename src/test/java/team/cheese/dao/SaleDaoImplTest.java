package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.myapp.Sale;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class SaleDaoImplTest {
    @Autowired
    SaleDao saledao;

    private static String namespace = "team.cheese.dao.SaleMapper.";

    @Test
    public void count() throws Exception {
        System.out.println("count : " + saledao.count());
    }

    @Test
    public void testSelectAll() throws Exception {
        System.out.println("selectAll : " + saledao.selectAll());
        List<Sale> list = saledao.selectAll();
        System.out.println(list.get(1).getSeller_id());
    }
}