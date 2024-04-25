import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.domain.ImgVO;
import team.cheese.service.ImgService;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class ImgServiceTest {
    @Autowired
    ImgService imgService;

    @Test
    public void reg_img(){
        ImgVO img = new ImgVO();
        img.setTb_name("board");
        img.setFilert("/Users/jehyeon/Desktop/Team/src/main/webapp/resources/img");
        img.setU_name("s_52932408-4685-4136-b20a-cacfa1fd478a_");
        img.setO_name("아보카도");
        img.setE_name(".png");
        img.setW_size(292);
        img.setH_size(292);
        assertTrue(imgService.reg_img(img) == 1);
    }
}
