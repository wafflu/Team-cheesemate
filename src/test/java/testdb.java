import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"})

public class testdb {
    @Autowired
    WebApplicationContext servletAC;
//    @Autowired
//    DataSource ds;
//
//    @Autowired
//    SqlSession session;
//
    @Test
    public void jdbcConnectionTest() throws Exception {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = a
//        c.getBean(DataSource.class);
//        System.out.println(ac);
//        System.out.println("ds = " + ds);
//        System.out.println(session);
//        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.
//
//        System.out.println("conn = " + conn);
//        assertTrue(conn!=null);
        System.out.println(servletAC);
    }

}
