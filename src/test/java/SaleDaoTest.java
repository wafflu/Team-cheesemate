import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.SaleDao;
import team.cheese.domain.SaleDto;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class SaleDaoTest {
    @Autowired
    SaleDao saleDao;


    @Test
    public void testInsert() throws Exception {
        // 글 작성하기 테스트
        SaleDto saledto = new SaleDto();
        saledto.setSeller_id("user123");
        saledto.setSal_i_cd("016001005");
        saledto.setPro_s_cd('C');
        saledto.setTx_s_cd('S');
        // 거래방법 1개만 작성
        saledto.setTrade_s_cd_1('F');
        saledto.setTrade_s_cd_2('F');
        saledto.setPrice(28000);
        saledto.setSal_s_cd('S');
        saledto.setTitle("자바의 정석 팔아요");
        saledto.setContents("자바의 정석 2판 팔아요.");
        saledto.setBid_cd('N');
        saledto.setPickup_addr_cd("11060710");
        saledto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saledto.setBrand("자바의 정석");
        saledto.setReg_price(30000);
        saledto.setCheck_addr_cd(0);

        int no = saleDao.insert_sale(saledto);
        System.out.println(saledto.getNo());
        System.out.println("성공(1)실패(0) : " + no);

        // 위에 판매글 겨우 작성함

    }
}
