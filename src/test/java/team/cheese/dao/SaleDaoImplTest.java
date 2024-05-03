package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.domain.SaleDto;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class SaleDaoImplTest {
    @Autowired
    SaleDao saleDao;
    @Autowired
    SaleHistoryDao salehistoryDao;
    @Autowired
    hoistingDao hoistingDao;
    @Autowired
    BidingDao bidingDao;
    @Autowired
    SaleTagDao saleTagDao;
    @Autowired
    TagDao tagDao;
    @Autowired
    SaleImgDao saleImgDao;
    @Autowired
    ImgDao imgDao;

    // 테스트하기 위해서 delete문을 한번에 담은 Class
    @Autowired
    DeleteDao deleteDao;

//    테스트를 진행하기에 앞서 먼저 Sale테이블을 참조하는 테이블의 데이터를 삭제한다.
    @Test
    public void testDeleteAll() throws Exception {
        saleImgDao.deleteAll();
        imgDao.deleteAll();
        saleTagDao.deleteAll();
        tagDao.deleteAll();
        bidingDao.deleteAll();
        hoistingDao.deleteAll();
        salehistoryDao.deleteAll();
        saleDao.deleteAll();

        imgDao.resetAutoIncrement();
        tagDao.resetAutoIncrement();
        bidingDao.resetAutoIncrement();
        hoistingDao.resetAutoIncrement();
        salehistoryDao.resetAutoIncrement();
        saleDao.resetAutoIncrement();

        assertTrue(saleImgDao.count()== 0);
        assertTrue(imgDao.count()== 0);
        assertTrue(saleTagDao.count()== 0);
        assertTrue(tagDao.count()== 0);
        assertTrue(bidingDao.count()== 0);
        assertTrue(hoistingDao.count()== 0);
        assertTrue(salehistoryDao.count()== 0);
        assertTrue(saleDao.count()== 0);
    }

    // 테스트를 위한 메서드를 따로 빼서 작성 후 테스트
    @Test
    public void testDeleteMethod() throws Exception {
        // 1. 게시글 및 연관되어있는 테이블 전체를 삭제
        // 2. 게시글 1개 작성 후 개수 확인
        //     2.1. 게시글 1개 작성
        //     2.2. 게시글 들어갔는지 확인
        // 3. 게시글 전체 삭제

        // 1. 게시글 및 연관되어있는 테이블 전체를 삭제
        deleteDao.deleteAll();

        // 2. 게시글 1개 작성 후 개수 확인
        SaleDto saleDto = new SaleDto();

        saleDto.setSeller_id("asdf");
        saleDto.setSal_i_cd("016001005");
        saleDto.setPro_s_cd("C");
        saleDto.setTx_s_cd("S");
        // 거래방법 1개만 작성
        saleDto.setTrade_s_cd_1("F");
        saleDto.setPrice(28000);
        saleDto.setSal_s_cd("S");
        saleDto.setTitle("자바의 정석 2판 팔아요");
        saleDto.setContents("자바의 정석 2판 팔아요. 3판 구매해서 2판 팝니다.");
        saleDto.setBid_cd("N");
        saleDto.setPickup_addr_cd("11060710");
        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saleDto.setBrand("자바의 정석");
        saleDto.setReg_price(30000);
        saleDto.setCheck_addr_cd(0);

        // 2.1. 게시글 1개 작성
        saleDao.insert(saleDto);

        // 2.2. 게시글 들어갔는지 확인
        assertTrue(saleDao.count() == 1);

        // 3. 게시글 전체 삭제
        deleteDao.deleteAll();

        assertTrue(saleImgDao.count()== 0);
        assertTrue(imgDao.count()== 0);
        assertTrue(saleTagDao.count()== 0);
        assertTrue(tagDao.count()== 0);
        assertTrue(bidingDao.count()== 0);
        assertTrue(hoistingDao.count()== 0);
        assertTrue(salehistoryDao.count()== 0);
        assertTrue(saleDao.count()== 0);
    }

    @Test
    public void testCount() throws Exception {
        // 1. 게시글 전체 삭제
        // 2. 반복문으로 게시글 100개 insert
        // 3. count로 게시글이 100개 들어갔는지 확인

        // 1. 게시글 전체 삭제
        deleteDao.deleteAll();

        // 2. 반복문으로 동일한 게시글 100개 insert
        SaleDto saleDto = new SaleDto();
        saleDto.setSeller_id("asdf");
        saleDto.setSal_i_cd("016001005");
        saleDto.setPro_s_cd("C");
        saleDto.setTx_s_cd("S");
        saleDto.setTrade_s_cd_1("F");
        saleDto.setPrice(28000);
        saleDto.setSal_s_cd("S");
        saleDto.setTitle("자바의 정석 2판 팔아요");
        saleDto.setContents("자바의 정석 2판 팔아요. 3판 구매해서 2판 팝니다.");
        saleDto.setBid_cd("N");
        saleDto.setPickup_addr_cd("11060710");
        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saleDto.setBrand("자바의 정석");
        saleDto.setReg_price(30000);
        saleDto.setCheck_addr_cd(0);

        int cnt = 100;
        for(int i = 0; i < cnt; i++) {
            // 2.1. 게시글 1개 작성
            saleDao.insert(saleDto);
        }

        // 3. count로 게시글이 100개 들어갔는지 확인
        assertTrue(saleDao.count() == cnt);
    }

    // sale테이블에 게시글 여러개가 잘 들어가는지 테스트


    // 현재 작성되어있는 게시글을 전부 불러왔을 때 count개수와 일치하는 지 확인
    @Test
    public void testSelectAll() throws Exception {
        // 1. 전체 글을 불러온다
        // 2. count를 한다
        // 3. 두 값을 비교 한다

        // 1. 전체 글을 불러온다
        List<SaleDto> list = saleDao.selectAll();
        // 2. count를 한다
        int cnt = saleDao.count();

        // 3. 두 값을 비교 한다
        assertTrue(list.size() == cnt);
    }

    // 판매자가 자신이 작성한 글을 삭제하는 경우
    @Test
    public void testDeletSalePost() throws Exception {
        // 1. 임의의 글을 불러온다.
        // 2. 글을 삭제한다.
        //     2.1. 글 작성자와 로그인한 사람이 동일하면 삭제 성공
        //        2.1.1. 글의 상태가 'N'으로 변화했는지 확인한다.
        //        2.1.2. 전체 게시글의 개수가 1개 줄어든 것을 확인한다.
        //     2.2. 글 작성자와 로그인한 사람이 다르면 삭제 실패
        //        2.2.1. 글의 상태가 'Y'인지 확인.
        //        2.2.2. 전체 게시글의 개수가 동일한 것을 확인한다.

        String ur_id = "asdf";

        BigInteger randnum = BigInteger.valueOf((int) (Math.random() * (saleDao.count()+1)));
        System.out.println(randnum);

        SaleDto saleDto = saleDao.select(randnum);
        System.out.println("값 선택" + saleDto);
        int cnt = saleDao.count();

        if (saleDto.getSeller_id().equals(ur_id)) {
//            saleDao.delete(randnum, ur_id);
            saleDao.delete(saleDto);
            System.out.println("아이디 동일 : " + saleDto);

            saleDto = saleDao.select(randnum);
            char ur_state = saleDto.getUr_state();
            System.out.println("ur_state : " + ur_state);
            assertTrue(ur_state == 'N');

            int newCnt = saleDao.count();

            assertTrue(cnt != newCnt);
            assertTrue((cnt-1) == newCnt);

        } else {
            saleDto = saleDao.select(randnum);
            System.out.println("아이디 미동일" + saleDto);

            char ur_state = saleDto.getUr_state();
            System.out.println("ur_state : " + ur_state);
            assertFalse(ur_state == 'N');

            int newCnt = saleDao.count();

            assertTrue(cnt == newCnt);
        }
    }


//    @Test
//    public void testSelect() throws Exception {
//        // 글 하나를 선택했을 때 게시글 번호와 판매자가 일치하는지 확인하는 테스트
//        System.out.println("select : " + saledao.select(BigInteger.valueOf(2)));
////        SaleDto saleDto = saledao.select(Long.valueOf(1));
////        String seller_id = saleDto.getSeller_id();
////        System.out.println(seller_id);
////        assertTrue(seller_id.equals("david234"));
//    }
//
//    @Test
//    public void testInsert() throws Exception {
//        int cnt = saledao.count();
//        assertTrue(cnt == 9);
//        // 글 작성하기 테스트
//        SaleDto saledto = new SaleDto();
//        saledto.setSeller_id("asdf");
//        saledto.setSal_i_cd("016001005");
//        saledto.setPro_s_cd("C");
//        saledto.setTx_s_cd("S");
//        // 거래방법 1개만 작성
//        saledto.setTrade_s_cd_1("F");
////        saledto.setTrade_s_cd_2('F');
//        saledto.setPrice(28000);
//        saledto.setSal_s_cd("S");
//        saledto.setTitle("자바의 정석 2판 팔아요");
//        saledto.setContents("자바의 정석 2판 팔아요. 3판 구매해서 2판 팝니다.");
//        saledto.setBid_cd("N");
//        saledto.setPickup_addr_cd("11060710");
//        saledto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
//        saledto.setBrand("자바의 정석");
//        saledto.setReg_price(30000);
//        saledto.setCheck_addr_cd(0);
//
//        System.out.println(saledto);
//
//        int no = saledao.insert(saledto);
//        System.out.println("확인 : " + saledto.getNo());
//        System.out.println("성공(1)실패(0) : " + no);
//        assertTrue(no == 1);
//
//        cnt = saledao.count();
//        assertTrue(cnt == 8);
//    }
//
//    @Test
//    public void testAdminState() throws Exception {
//        // 관리자가 선택한 판매글의 번호의 상태에 개입할 때 상태를 바꿔주기
//        SaleDto saleDto = saledao.select(BigInteger.valueOf(3));
//        System.out.println(saleDto.getNo());
//        saledao.adminState(saleDto);
//        System.out.println(saleDto.getAd_state());
////        assertTrue(saleDto.getAd_state() == 'N');
//    }
//
//    @Test
//    public void testSaleState() throws Exception {
//        // 판매자가 판매글글을 삭제하였을 때 상태를 바꿔주기
//        SaleDto saleDto = saledao.select(BigInteger.valueOf(7));
//        System.out.println(saleDto.getNo());
//        saledao.delete(saleDto);
//        System.out.println(saleDto.getAd_state());
////        assertTrue(saleDto.getAd_state() == 'N');
//    }
//
//    @Test
//    public void testSaleModify() throws Exception {
//        // 판매글 작성자(판매자)가 판매들을 수정하는 경우
//        SaleDto saleDto = saledao.select(BigInteger.valueOf(7));
//        saleDto.setTitle("자바의 정석 기본편 나눔");
//        saleDto.setContents("자바의 정석 기본편 나눔합니다.");
//        saleDto.setDetail_addr("상록중학교 정문");
//        saleDto.setTx_s_cd("F");
//        assertTrue(saledao.update(saleDto)==1);
//    }

}