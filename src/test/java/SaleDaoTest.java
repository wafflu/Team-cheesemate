import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.ImgDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SaleDto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class SaleDaoTest {
    @Autowired
    SaleDao saleDao;

    @Autowired
    ImgDao imgDao;

}
