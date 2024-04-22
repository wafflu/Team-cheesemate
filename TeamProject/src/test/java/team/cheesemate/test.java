package team.cheesemate;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;

public class test {
    @Test
    public void test (){
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = null; // 데이터베이스의 연결을 얻는다.
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("conn = " + conn);
        assertTrue(conn!=null);
    }
}
