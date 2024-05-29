import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testng.annotations.Test;
import team.cheese.dao.SaleDao;
import team.cheese.domain.SaleDto;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class ImgDaoTest {
    @Autowired
    SaleDao saleDao;
    @Test
    public void testUpdateSaleSCdRandomRCode() throws Exception {
        assertTrue(saleDao.count() != 0);
        for (int i=0; i<25; i++) {
            Long no = (long) (Math.random() * saleDao.count() + 1);
            System.out.println(no);
            SaleDto saleDto = saleDao.select(no);
            String sal_s_cd = "C";
            Map<String, Object> map = new HashMap<>();
            if(!saleDto.getSeller_id().equals("i1234")) {
                saleDto.setBuyer_id("asdf");
                saleDto.setBuyer_nick("asdf");
                saleDto.setSal_s_cd(sal_s_cd);
                assertTrue(saleDao.buySale(saleDto) == 1);
            }
        }
    }
}
