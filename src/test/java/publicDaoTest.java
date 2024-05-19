import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.ImgDao;
import team.cheese.dao.PublicDao;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class publicDaoTest {
    @Autowired
    PublicDao pdao;

    @Autowired
    ImgDao imgDao;

    public void select(int no){
        HashMap map = new HashMap();
        map.put("state", "ad_state");
        map.put("tablename", "sale");
        map.put("no", no);
        System.out.println(pdao.select_state_check(map));
    }

    @Test
    public void update(){
        select(29);
        HashMap map = new HashMap();
        map.put("tablename", "sale");
        map.put("state", "ad_state");
        map.put("no", 29);
        assertTrue(pdao.updateState(map) == 1);
        select(29);
    }
}
