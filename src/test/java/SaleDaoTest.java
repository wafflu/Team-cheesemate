//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import team.cheese.dao.ImgDao;
//import team.cheese.dao.SaleDao;
//import team.cheese.domain.ImgDto;
//import team.cheese.domain.SaleDto;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//
//import static junit.framework.TestCase.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class SaleDaoTest {
//    @Autowired
//    SaleDao saleDao;
//
//    @Autowired
//    ImgDao imgDao;
//
//    @Test
//    public void insert(){
//        SaleDto saledto = new SaleDto();
//        saledto.setAddr_cd("11010720");
//        saledto.setAddr_name("서울특별시 종로구 사직동");
//        saledto.setSeller_id("user123");
//        saledto.setSeller_nick("minyoung");
//        saledto.setSal_i_cd("001002002");
//        saledto.setSal_name("후드티/후드집업");
//        saledto.setGroup_no(21);
//        saledto.setImg_full_rt("2024_05_03/s_2024_05_03_마루1.jpeg");
//        saledto.setPro_s_cd("C");
//        saledto.setTx_s_cd("S");
//        saledto.setTrade_s_cd_1("F");
//        saledto.setTrade_s_cd_2("F");
//        saledto.setSal_s_cd("S");
//        saledto.setTitle("판매글");
//        saledto.setContents("판매테스트중");
//        saledto.setPrice(35000);
//        saledto.setBid_cd("N");
//        saledto.setPickup_addr_cd("11060710");
//        saledto.setPickup_addr_name("서울특별시 종로구 청운효자동");
//        saledto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
//        saledto.setBrand("북북");
//        saledto.setReg_price(100);
//        saledto.setBuyer_id("");
//        saledto.setBuyer_nick("");
//        saledto.setLike_cnt(0);
//        saledto.setView_cnt(0);
//        saledto.setHoist_cnt(0);
//        saledto.setBid_cnt(0);
//        saledto.setUr_state('Y');
//        saledto.setAd_state('N');
//        saledto.setFirst_id("user123");
//        saledto.setLast_id("user123");
//        saleDao.insert_sale(saledto);
//    }
//
//    @Test
//    public void select(){
//        List<SaleDto> list = saleDao.select_all();
//        assertTrue(list != null);
//
//        Iterator it= list.iterator();
//
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
//
//    }
//}
