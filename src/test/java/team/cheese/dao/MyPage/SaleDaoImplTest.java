//package team.cheese.Dao.MyPage;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import team.cheese.Domain.MyPage.SaleDto;
//import team.cheese.Domain.MyPage.SearchCondition;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class SaleDaoImplTest {
//    @Autowired
//    SaleDao saleDao;
//    @Test
//    public void insert() throws Exception {
//        // 1. 게시글 전체 삭제
//        // 2. 반복문으로 게시글 100개 insert
//        // 3. count로 게시글이 100개 들어갔는지 확인
//
//        // 1. 게시글 전체 삭제
////        saleDao.deleteAll();
//
//        // 2. 반복문으로 동일한 게시글 100개 insert
//        SaleDto saleDto = new SaleDto();
//        saleDto.setAddr_cd("11060710");
//        saleDto.setAddr_name("서울특별시 동대문구 회기동");
//        saleDto.setSeller_id("asdf");
//        saleDto.setSeller_nick("닉네임");
//        saleDto.setSal_i_cd("016001005");
//        saleDto.setSal_name("학습/사전/참고서");
//        saleDto.setPro_s_cd("C");
//        saleDto.setTx_s_cd("S");
//        // 거래방법 1개만 작성
//        saleDto.setTrade_s_cd_1("F");
//        saleDto.setPrice(28000);
//        saleDto.setSal_s_cd("C");
//        saleDto.setTitle("자바정석");
//        saleDto.setContents("서적 팝니다.");
//        saleDto.setBid_cd("N");
//        saleDto.setPickup_addr_cd("11060710");
//        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
//        saleDto.setBrand("oo북스");
//        saleDto.setReg_price(30000);
//        saleDto.setFirst_id("asdf");
//        saleDto.setLast_id("asdf");
//        saleDto.setBuyer_id("1234");
//        saleDto.setBuyer_nick("구매자닉네임");
//
//        int cnt = 10;
//        for(int i = 0; i < cnt; i++) {
//            // 2.1. 게시글 1개 작성
//            saleDao.insertSale(saleDto);
//        }
//
//        // 3. count로 게시글이 100개 들어갔는지 확인
////        assertTrue(saleDao.count() == 30);
//    }
//    @Test
//    public void selectSearchPage() throws Exception {
//        // 판매내역 - 전체
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        SearchCondition sc = new SearchCondition(1,5,"seller","","","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        List<SaleDto> list = saleDao.selectSearchPage(sc);
//        // assert : list size가 50개가 맞는지 확인
//        assertTrue(list.size()==5);
//
//
//        // 판매내역 - 예약중
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"seller","","R","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        list = saleDao.selectSearchPage(sc);
//        // assert : list size가 50개가 맞는지 확인
//        assertTrue(list.size()==5);
//        for(int i=0; i<list.size(); i++) {
//            String sal_s_cd = list.get(i).getSal_s_cd();
//            assertTrue(sal_s_cd.equals("R"));
//        }
//
//        // 판매내역 - 판매중
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"seller","","S","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        list = saleDao.selectSearchPage(sc);
//        // assert : list size가 50개가 맞는지 확인
//        assertTrue(list.size()==5);
//        for(int i=0; i<list.size(); i++) {
//            String sal_s_cd = list.get(i).getSal_s_cd();
//            assertTrue(sal_s_cd.equals("S"));
//        }
//
//        // 판매내역 - 거래완료
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"seller","","C","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        list = saleDao.selectSearchPage(sc);
//        // assert : list size가 50개가 맞는지 확인
//        assertTrue(list.size()==5);
//        for(int i=0; i<list.size(); i++) {
//            String sal_s_cd = list.get(i).getSal_s_cd();
//            assertTrue(sal_s_cd.equals("C"));
//        }
//    }
//
//
//
//
//    @Test
//    public void selectSearchCount() throws Exception {
//        // 판매내역 - 전체
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        SearchCondition sc = new SearchCondition(1,5,"seller","","","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        int rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 50개가 맞는지 확인
//        assertTrue(rowCnt==80);
//
//        // 판매내역 - 예약중
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"seller","","R","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==30);
//
//        // 판매내역 - 판매중
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"seller","","S","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==30);
//
//        // 판매내역 - 거래완료
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"seller","","C","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==20);
//
//
//        // 구매내역 - 전체
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"buyer","","","1234");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 50개가 맞는지 확인
//        assertTrue(rowCnt==80);
//
//        // 구매내역 - 예약중
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"buyer","","R","1234");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==30);
//
//        // 구매내역 - 구매중
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"buyer","","S","1234");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==30);
//
//        // 구매내역 - 구매완료
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"buyer","","C","1234");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==20);
//
//
//
//
//
//        // 제목 조건 추가
//        // 판매내역 - 전체 , 제목이 자바정석
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//         sc = new SearchCondition(1,5,"seller","자바정석","","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//         rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 30개가 맞는지 확인
//        assertTrue(rowCnt==30);
//
//        // 판매내역 - 예약중
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"seller","자바정석","R","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==10);
//
//        // 판매내역 - 판매중
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"seller","자바정석","S","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==10);
//
//        // 판매내역 - 거래완료
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"seller","자바정석","C","asdf");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==10);
//
//
//        // 구매내역 - 전체 , 제목이 자바정석
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"buyer","자바정석","","1234");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 50개가 맞는지 확인
//        assertTrue(rowCnt==30);
//
//        // 구매내역 - 예약중
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"buyer","자바정석","R","1234");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==10);
//
//        // 구매내역 - 구매중
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"buyer","자바정석","S","1234");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==10);
//
//        // 구매내역 - 구매완료
//        // given&when : SearchCondition 객체 생성 page=1,pageSize=5,option="seller",keyword="",sal_s_cd=""
//        // ,ur_id="asdf"
//        sc = new SearchCondition(1,5,"buyer","자바정석","C","1234");
//        // do : selectSearchCount메서드의 매개변수로 SearchCondition객체 전달
//        rowCnt = saleDao.selectSearchCount(sc);
//        // assert : list size가 20개가 맞는지 확인
//        assertTrue(rowCnt==10);
//
//
//    }
//}