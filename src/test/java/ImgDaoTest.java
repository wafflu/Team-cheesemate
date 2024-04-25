import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.ImgDao;
import team.cheese.domain.ImgVO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class ImgDaoTest {
    @Autowired
    ImgDao imgDao;

    @Test
    public void insertImg(){
        ImgVO img = new ImgVO();
        img.setTb_name("board");
        img.setFilert("/Users/jehyeon/Desktop/Team/src/main/webapp/resources/img");
        img.setU_name("s_52932408-4685-4136-b20a-cacfa1fd478a_");
        img.setO_name("아보카도");
        img.setE_name(".png");
        img.setW_size(292);
        img.setH_size(292);
        assertTrue(imgDao.insert_img(img) == 1);
        System.out.println(imgDao.insert_img(img));
    }

    @Test
    public void img_selectAll(){
        List<ImgVO> list = imgDao.selectAll_img();
        assertTrue(imgDao.selectAll_img() != null);
        Iterator it = list.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }
    }


    @Test
    public void img_selectList(){
        HashMap map = new HashMap();
        map.put("tb_name", "board");
        map.put("sal_no", 1);
        List<ImgVO> list = imgDao.selectImg_list(map);
        assertTrue(list != null);
        Iterator it = list.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void state_change_img(){
        HashMap map = new HashMap();
        map.put("state", "Y");
        map.put("no", 4);
        int change = imgDao.update_img(map);
        assertTrue(change == 1);
    }
}
