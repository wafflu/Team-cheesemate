package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.SaleDto;
import team.cheese.entity.PageHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    HoistingDao hoistingDao;
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
    @Autowired
    AddrCdDao addrCdDao;

    // 테스트하기 위해서 delete문을 한번에 담은 Class
    @Autowired
    DeleteDao deleteDao;


//   D : 테스트를 진행하기에 앞서 먼저 Sale테이블을 참조하는 테이블의 데이터를 삭제한다.
    @Test
    public void testDeleteAll() throws Exception {
        saleImgDao.deleteAll();
//        imgDao.deleteAll();
        saleTagDao.deleteAll();
        tagDao.deleteAll();
        bidingDao.deleteAll();
        hoistingDao.deleteAll();
        salehistoryDao.deleteAll();
        saleDao.deleteAll();

//        imgDao.resetAutoIncrement();
        tagDao.resetAutoIncrement();
        bidingDao.resetAutoIncrement();
        hoistingDao.resetAutoIncrement();
        salehistoryDao.resetAutoIncrement();
        saleDao.resetAutoIncrement();

        assertTrue(saleImgDao.count()== 0);
        assertTrue(imgDao.count("sale")== 0);
        assertTrue(saleTagDao.count()== 0);
        assertTrue(tagDao.count()== 0);
        assertTrue(bidingDao.count()== 0);
        assertTrue(hoistingDao.count()== 0);
        assertTrue(salehistoryDao.count()== 0);
        assertTrue(saleDao.count()== 0);
    }

    // D : 테스트를 위한 메서드를 따로 빼서 작성 후 테스트
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

        saleDto.setAddr_cd("11060710");
        saleDto.setAddr_name("서울특별시 동대문구 회기동");
        saleDto.setSeller_id("david234");
        saleDto.setSeller_nick("닉네임");
        saleDto.setSal_i_cd("016001005");
        saleDto.setSal_name("학습/사전/참고서");
        saleDto.setPro_s_cd("C");
        saleDto.setTx_s_cd("S");
        // 거래방법 1개만 작성
        saleDto.setTrade_s_cd_1("F");
        saleDto.setPrice(28000);
        saleDto.setSal_s_cd("S");
        saleDto.setTitle("서적 팔아요");
        saleDto.setContents("서적 팝니다.");
        saleDto.setBid_cd("N");
        saleDto.setPickup_addr_cd("11060710");
        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saleDto.setBrand("oo북스");
        saleDto.setReg_price(30000);
        saleDto.setFirst_id("david234");
        saleDto.setLast_id("david234");

        // 2.1. 게시글 1개 작성
        saleDao.insertSale(saleDto);

        // 2.2. 게시글 들어갔는지 확인
        assertTrue(saleDao.count() == 1);

        // 3. 게시글 전체 삭제
        deleteDao.deleteAll();

        assertTrue(saleImgDao.count()== 0);
        assertTrue(imgDao.count("sale")== 0);
        assertTrue(saleTagDao.count()== 0);
        assertTrue(tagDao.count()== 0);
        assertTrue(bidingDao.count()== 0);
        assertTrue(hoistingDao.count()== 0);
        assertTrue(salehistoryDao.count()== 0);
        assertTrue(saleDao.count()== 0);
    }

//    C : 게시글을 100 삽입한다
    @Test
    public void testInsertCount() throws Exception {
        // 1. 게시글 전체 삭제
        // 2. 반복문으로 게시글 100개 insert
        // 3. count로 게시글이 100개 들어갔는지 확인

        // 1. 게시글 전체 삭제
//        deleteDao.deleteAll();

        // 2. 반복문으로 동일한 게시글 100개 insert
        SaleDto saleDto = new SaleDto();
        saleDto.setAddr_cd("11060710");
        saleDto.setAddr_name("서울특별시 동대문구 회기동");
        saleDto.setSeller_id("asdf");
        saleDto.setSeller_nick("닉네임");
        saleDto.setSal_i_cd("016001005");
        saleDto.setSal_name("학습/사전/참고서");
        saleDto.setPro_s_cd("C");
        saleDto.setTx_s_cd("S");
        // 거래방법 1개만 작성
        saleDto.setTrade_s_cd_1("F");
        saleDto.setPrice(28000);
        saleDto.setSal_s_cd("S");
        saleDto.setTitle("서적 팔아요");
        saleDto.setContents("서적 팝니다.");
        saleDto.setBid_cd("N");
        saleDto.setPickup_addr_cd("11060710");
        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saleDto.setBrand("oo북스");
        saleDto.setReg_price(30000);
        saleDto.setFirst_id("asdf");
        saleDto.setLast_id("asdf");

        int cnt = 100;
        for(int i = 0; i < cnt; i++) {
            // 2.1. 게시글 1개 작성
            saleDao.insertSale(saleDto);
        }

        // 3. count로 게시글이 100개 들어갔는지 확인
        assertTrue(saleDao.count() == cnt);
    }

    // R : 현재 작성되어있는 게시글을 전부 불러왔을 때 count개수와 일치하는 지 확인
    @Test
    public void testselectUserAddrCd() throws Exception {
        // 1. 전체 글을 불러온다
        // 2. count를 한다
        // 3. 두 값을 비교 한다
        Map map = new HashMap();
        String addr_cd = null;
        String sal_i_cd = null;
        map.put("addr_cd", addr_cd);
        map.put("sal_i_cd", sal_i_cd);

        // 1. 전체 글을 불러온다
        List<SaleDto> list = saleDao.selectSaleList(map);
        // 2. count를 한다
        int cnt = saleDao.countUse();

        // 3. 두 값을 비교 한다
        assertTrue(list.size() == cnt);
    }

    @Test
    public void testselectUserAddrCd2() throws Exception {
//        List<AddrCdDto> addrCdList = addrCdDao.se

        String addr_cd = "11060710";
        // 1. 전체 글을 불러온다
        Map map = new HashMap();
        map.put("addr_cd", addr_cd);
        List<SaleDto> list = saleDao.selectSaleList(map);
        System.out.println(list);
        System.out.println(list.size());
    }

//    R : 게시글 하나를 선택해온다.
    @Test
    public void testSelectOne() throws Exception {
        // 1. 삭제되지 않은 한개 글을 불러온다
        // 2. 불러온 글 번호와 호출한 글 번호가 일치하는지 확인한다.

        Long no = (long) (Math.random() * saleDao.countUse() + 1);
        SaleDto saleDto = saleDao.select(no);
        System.out.println("no : " + no);
        System.out.println("getNo : " + saleDto.getNo());
        assertTrue(no.equals(saleDto.getNo()));
    }

    // U : 판매자가 자신이 작성한 글을 삭제하는 경우
    @Test
    public void testDeletSalePost() throws Exception {
        // 1. 임의의 글을 불러온다.
        // 2. 글을 삭제한다.
        //     2.1. 글 작성자와 로그인한 사람이 동일하면 삭제 성공
        //        2.1.1. 글의 상태가 'N'으로 변화했는지 확인한다.(null)
        //        2.1.2. 전체 게시글의 개수가 1개 줄어든 것을 확인한다.
        //     2.2. 글 작성자와 로그인한 사람이 다르면 삭제 실패
        //        2.2.1. 글의 상태가 'Y'인지 확인.
        //        2.2.2. 전체 게시글의 개수가 동일한 것을 확인한다.

        String ur_id = "asdf";

        Long randnum = Long.valueOf((int) (Math.random() * (saleDao.count()+1)));
        System.out.println(randnum);

        if(saleDao.select(randnum) != null) {
            SaleDto saleDto = saleDao.select(randnum);
            System.out.println("값 선택 : " + saleDto);
            int cnt = saleDao.countUse();
            if (saleDto.getSeller_id().equals(ur_id) && saleDto.getUr_state() == 'Y') {
//            saleDao.delete(randnum, ur_id);
                int result = saleDao.delete(randnum, ur_id);
                System.out.println("result : " + result);
//                System.out.println("아이디 동일 : " + saleDto);

                assertTrue(saleDao.select(randnum) == null);

                int newCnt = saleDao.countUse();
                System.out.println("cnt : " + cnt);
                System.out.println("newCnt : " + newCnt);

                assertTrue(cnt != newCnt);
                assertTrue((cnt-1) == newCnt);

            } else if(!saleDto.getSeller_id().equals(ur_id) && saleDto.getUr_state() == 'Y') {
                saleDto = saleDao.select(randnum);
                System.out.println("아이디 미동일" + saleDto);

                assertTrue(!ur_id.equals(saleDto.getSeller_id()));

            }
        }
    }

    @Test
    public void testAdminState() throws Exception {
        // 관리자가 선택한 판매글의 번호의 상태에 개입할 때 상태를 바꿔주기
        Long no = (long) (Math.random() * saleDao.countUse()+ 1);
        SaleDto saleDto = saleDao.select(no);
        saleDao.adminState(saleDto);
        System.out.println(saleDto.getAd_state());
        assertTrue(saleDto.getAd_state() == 'N');
    }

    @Test
    public void testSaleModify() throws Exception {
        // 판매글 작성자(판매자)가 판매들을 수정하는 경우
        Long no = (long) (Math.random() * saleDao.countUse()+ 1);

        SaleDto saleDto = saleDao.select(no);
        saleDto.setTitle("글 수정 테스트");
        saleDto.setContents("글 수정 테스트 중입니다");
        saleDto.setDetail_addr("상록중학교 정문");
        saleDto.setTx_s_cd("F");
        assertTrue(saleDao.update(saleDto)==1);
    }
    
    @Test
    public void testSaleInsert() throws Exception {
        // insert문 테스트

        // 1. 게시글 전체 삭제
        deleteDao.deleteAll();

        // 2. 세션에서 id, nick, addr_cd정보를 불러온다고 가정
        String seller_id = "asdf"; // user 테이블에서 가져왔다고 가정
        String seller_nick = "봄"; // user 테이블에서 가져왔다고 가정
        List<AddrCdDto> addrCdList = addrCdDao.getAddrCdByUserId(seller_id);
        // addrCdList 중 첫번째 값이 기본 주소값
        AddrCdDto addrCdDto = addrCdList.get(0);
        String addr_cd = addrCdDto.getAddr_cd();
        String addr_name = addrCdDto.getAddr_name();

        // 판매카테고리 선택한 경우
        // 판매카테고리 명 적용

        // 2. 반복문으로 동일한 게시글 100개 insert
        SaleDto saleDto = new SaleDto();

        saleDto.setAddr_cd(addr_cd);
        saleDto.setAddr_name(addr_name);
        saleDto.setSeller_id(seller_id);
        saleDto.setSeller_nick("닉네임");
        saleDto.setSal_i_cd("016001005");
        saleDto.setSal_name("학습/사전/참고서");
        saleDto.setPro_s_cd("C");
        saleDto.setTx_s_cd("S");
        // 거래방법 1개만 작성
        saleDto.setTrade_s_cd_1("F");
        saleDto.setPrice(28000);
        saleDto.setSal_s_cd("S");
        saleDto.setTitle("서적 팔아요");
        saleDto.setContents("서적 팝니다.");
        saleDto.setBid_cd("N");
        saleDto.setPickup_addr_cd("11060710");
        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saleDto.setBrand("oo북스");
        saleDto.setReg_price(30000);
        saleDto.setFirst_id(seller_id);
        saleDto.setLast_id(seller_id);

        // 3. 게시글 1개 작성
        assertTrue(saleDao.insertSale(saleDto) == 1);
    }

    // viewCnt가 증가하는지 확인하는 테스트
    @Test
    public void testViewCnt() throws Exception {
        // 1. 게시글 전체 삭제
        deleteDao.deleteAll();

        // 2. 반복문으로 동일한 게시글 100개 insert
        SaleDto saleDto = new SaleDto();
        saleDto.setAddr_cd("11060710");
        saleDto.setAddr_name("서울특별시 동대문구 회기동");
        saleDto.setSeller_id("asdf");
        saleDto.setSeller_nick("닉네임");
        saleDto.setSal_i_cd("016001005");
        saleDto.setSal_name("학습/사전/참고서");
        saleDto.setPro_s_cd("C");
        saleDto.setTx_s_cd("S");
        // 거래방법 1개만 작성
        saleDto.setTrade_s_cd_1("F");
        saleDto.setPrice(28000);
        saleDto.setSal_s_cd("S");
        saleDto.setTitle("서적 팔아요");
        saleDto.setContents("서적 팝니다.");
        saleDto.setBid_cd("N");
        saleDto.setPickup_addr_cd("11060710");
        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saleDto.setBrand("oo북스");
        saleDto.setReg_price(30000);
        saleDto.setFirst_id("asdf");
        saleDto.setLast_id("asdf");

        int cnt = 100;
        for(int i = 0; i < cnt; i++) {
            // 2.1. 게시글 1개 작성
            saleDao.insertSale(saleDto);
        }

        // 3. count로 게시글이 100개 들어갔는지 확인
        assertTrue(saleDao.count() == cnt);

        // 4. 게시물 중 랜덤으로 하나를 불러옴
        Long no = (long) (Math.random() * saleDao.count()+ 1);
        System.out.println("판매글 번호 : " + no);

        SaleDto beforeSaleDto = saleDao.select(no);
        int beforeViewCnt = beforeSaleDto.getView_cnt();
        System.out.println("이전 viewCnt : " + beforeViewCnt);

        // 5. 조회를 하였으므로 viewCnt 증가
        saleDao.increaseViewCnt(no);

        SaleDto afterSaleDto = saleDao.select(no);
        int afterViewCnt = afterSaleDto.getView_cnt();
        System.out.println("이후 viewCnt : " + afterViewCnt);

        // 6. 이전 조회수와 이후 조회수 비교
        assertTrue(beforeViewCnt == (afterViewCnt - 1));
    }

    @Test
    public void testCountSale() throws Exception {
        Map map = new HashMap();

        int count = saleDao.countSale(map);
        System.out.println(count);
        assertTrue( count != 0 );
    }

    @Test
    public void testCountAddrCdSale() throws Exception {
        Map map = new HashMap();

        map.put("addr_cd", "11060710");
        map.put("sal_i_cd", null);

        int count = saleDao.countSale(map);
        System.out.println(count);
        assertTrue( count != 0 );
    }

    @Test
    public void testAddrCdSaleList() throws Exception {

        Map map = new HashMap();
//        map.put("addr_cd", "11060710");
//        map.put("sal_i_cd", "016001005");
        int page = 2;
        int pageSize = 10;
        PageHandler ph = new PageHandler(saleDao.countSale(map), page, pageSize);
        System.out.println(ph.getBeginPage());

        map.put("offset", ph.getOffset());
        map.put("pageSize", pageSize);

        List<SaleDto> saleList = saleDao.selectSaleList(map);
        System.out.println(saleList);
        System.out.println(saleList.size());
//        assert( saleList);
    }

    @Test
    public void testUpdateSaleSCd() throws Exception {
        Long no = (long) (Math.random() * saleDao.countUse()+ 1);
        SaleDto saleDto = saleDao.select(no);
        String sal_s_cd = "C";
        Map map = new HashMap();
        map.put("no", saleDto.getNo());
        map.put("sal_s_cd", sal_s_cd);
        map.put("seller_id", saleDto.getSeller_id());

        int result = saleDao.updateSaleSCd(map);

        assertTrue(result == 1);

        SaleDto newSaleDto = saleDao.select(no);

        assertTrue(newSaleDto.getSal_s_cd().equals(sal_s_cd));
    }
}