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


    @Test
    public void saleTest() throws Exception {
        // 글 작성하기 테스트
        SaleDto saledto = new SaleDto();
        saledto.setSeller_id("user123");
        saledto.setSal_i_cd("016001005");
        saledto.setPro_s_cd("C");
        saledto.setTx_s_cd("S");
        // 거래방법 1개만 작성
        saledto.setTrade_s_cd_1("F");
        saledto.setTrade_s_cd_2("F");
        saledto.setPrice(28000);
        saledto.setSal_s_cd("S");
        saledto.setTitle("자바의 정석 팔아요");
        saledto.setContents("자바의 정석 2판 팔아요.");
        saledto.setBid_cd("N");
        saledto.setPickup_addr_cd("11060710");
        saledto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saledto.setBrand("자바의 정석");
        saledto.setReg_price(30000);
        saledto.setCheck_addr_cd(0);

        int no = saleDao.insert_sale(saledto);
        System.out.println(saledto.getNo());
        System.out.println("성공(1)실패(0) : " + no);

        // 위에 판매글 겨우 작성함
//        assertTrue(imgDao.delete_cross_all("sale_img") != 0);
//
//        assertTrue(imgDao.delete_img_all() != 0);

        ImgDto img = new ImgDto();

        img.setTb_name("sale");
        img.setTb_no(saledto.getNo());
        img.setImgtype("s");
        img.setFilert("/Users/jehyeon/Desktop/Team/src/main/webapp/resources/img");
        img.setU_name("s_52932408-4685-4136-b20a-cacfa1fd478a_");
        img.setO_name("아보카도");
        img.setE_name(".png");
        img.setW_size(292);
        img.setH_size(292);

        for(int i = 0; i<5; i++){
            imgDao.insert_img(img);
        }
        img.setTb_no(saledto.getNo()+1);
        imgDao.insert_img(img);
        assertTrue(imgDao.insert_img(img) == 1);

        List<ImgDto> list = imgDao.selectAll_img();
        assertTrue(imgDao.selectAll_img() != null);
        Iterator it = list.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }

        HashMap map = new HashMap();

        map.put("no", saledto.getNo());
        map.put("col_name", "sal_no");
        map.put("no_name", "no");
        map.put("cross_tb_name", "sale");
        map.put("tb_name", "sale_img");

        int change = imgDao.insert_img(map);
        assertTrue(change > 1);
    }

}
