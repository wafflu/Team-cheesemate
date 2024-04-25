import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.ImgDao;
import team.cheese.domain.ImgDto;

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
        ImgDto img = new ImgDto();
        img.setTb_name("board");
        img.setTb_no(1);
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
        List<ImgDto> list = imgDao.selectAll_img();
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
        map.put("cross_tb", "sale_img");
        map.put("sal_no", 1);
        List<ImgDto> list = imgDao.simg_list(map);
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

    @Test
    public void sale_img_test(){
        assertTrue(imgDao.delete_sale_img_all() != 0);
        HashMap map = new HashMap();
        map.put("no", 1);
        map.put("cross_tb", "sale");
        int change = imgDao.insert_sale_img(map);
        assertTrue(change > 1);
    }

    @Test
    public void img_test(){
        assertTrue(imgDao.delete_sale_img_all() != 0);

        assertTrue(imgDao.delete_img_all() != 0);

        ImgDto img = new ImgDto();
        img.setTb_name("sale");
        img.setTb_no(1);
        img.setFilert("/Users/jehyeon/Desktop/Team/src/main/webapp/resources/img");
        img.setU_name("s_52932408-4685-4136-b20a-cacfa1fd478a_");
        img.setO_name("아보카도");
        img.setE_name(".png");
        img.setW_size(292);
        img.setH_size(292);
        for(int i = 0; i<5; i++){
            imgDao.insert_img(img);
        }
        assertTrue(imgDao.insert_img(img) == 1);

        List<ImgDto> list = imgDao.selectAll_img();
        assertTrue(imgDao.selectAll_img() != null);
        Iterator it = list.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }

        HashMap map = new HashMap();
        map.put("no", 1);
        map.put("cross_tb", "sale");
        int change = imgDao.insert_sale_img(map);
        assertTrue(change > 1);
    }

}
