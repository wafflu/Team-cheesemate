import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.SaleDao;
import team.cheese.domain.SaleDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

@Import(dbtest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class dbtest {
    @Autowired
    DataSource ds; // 컨테이너로부터 자동 주입받는다.

    @Autowired
    SqlSessionFactoryBean sf; // 컨테이너로부터 자동 주입받는다.

    @Autowired
    SaleDao saleDao;

    @Test
    public void jdbcConnectionTest() throws Exception {

        System.out.println("ds = " + ds);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn!=null);
    }

    @Test
    public void ttt() throws Exception {
        for (int i=0; i<25; i++) {
            Long no = (long) (Math.random() * saleDao.count() + 1);
            System.out.println(no);
            SaleDto saleDto = saleDao.select(no);
            String sal_s_cd = "R";
            Map<String, Object> map = new HashMap<>();
            if(!saleDto.getSeller_id().equals("i1234")) {
                saleDto.setBuyer_id("asdf");
                saleDto.setBuyer_nick("asdf");
                saleDto.setSal_s_cd(sal_s_cd);
                Assertions.assertTrue(saleDao.buySale(saleDto) == 1);
            }
        }
    }
}
