package team.cheese.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

import team.cheese.dao.SaleDao;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class SaleDaoImplTest {
    @Autowired
    SaleDao saledao;

    @Test
    public void count() throws Exception {
        // sale테이블에 게시글이 몇개 들어있는지 확인하는 테스트
        System.out.println("count : " + saledao.count());
        int cnt = saledao.count();
        assertTrue(cnt == 8);
    }

    @Test
    public void testSelectAll() throws Exception {
        // 전체 글을 selectALL해왔을 때 첫번째 게시글이 david234가 작성했는지 확인하는 테스트
        System.out.println("selectAll : " + saledao.selectAll());
        List<SaleDto> list = saledao.selectAll();
        String seller_id = list.get(list.size()-1).getSeller_id();
        System.out.println(seller_id);
        assertTrue(seller_id.equals("david234"));
    }

    @Test
    public void testSelect() throws Exception {
        // 글 하나를 선택했을 때 게시글 번호와 판매자가 일치하는지 확인하는 테스트
        System.out.println("select : " + saledao.select(1));
        SaleDto saleDto = saledao.select(1);
        String seller_id = saleDto.getSeller_id();
        System.out.println(seller_id);
        assertTrue(seller_id.equals("david234"));
    }

    @Test
    public void testInsert() throws Exception {
        // 글 작성하기 테스트
        SaleDto saledto = new SaleDto();
        saledto.setSeller_id("asdf");
        saledto.setSal_i_cd("016001005");
        saledto.setPro_s_cd('C');
        saledto.setTx_s_cd('S');
        // 거래방법 1개만 작성
        saledto.setTrade_s_cd_1('F');
//        saledto.setTrade_s_cd_2('F');
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

        System.out.println(saledto);

        int no = saledao.insert(saledto);
        System.out.println("확인 : " + saledto.getNo());
        System.out.println("성공(1)실패(0) : " + no);
//        assertTrue(no == 1);

        int cnt = saledao.count();
//        assertTrue(cnt == 10);
    }
}