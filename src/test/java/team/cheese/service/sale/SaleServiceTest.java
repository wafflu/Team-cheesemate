package team.cheese.service.sale;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.*;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.SaleDto;
import team.cheese.domain.SaleTagDto;
import team.cheese.domain.TagDto;
import team.cheese.entity.PageHandler;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class SaleServiceTest extends TestCase {
    @Autowired
    SaleDao saleDao;
    @Autowired
    SaleCategoryDao saleCategoryDao;
    @Autowired
    AdministrativeDao administrativeDao;

    @Autowired
    TagDao tagDao;
    @Autowired
    AddrCdDao addrCdDao;
    @Autowired
    SaleTagDao saleTagDao;
    @Autowired
    SaleService saleService;
    @Autowired
    DeleteDao deleteDao;

    @Autowired
    TestSession testSession;

    @Test
    public void testDiffIdRemove() throws Exception {
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
        saleDao.insertSale(saleDto);
        Long no = saleDto.getNo();

        SaleDto selectSale = saleDao.select(no);
        char bf_state = selectSale.getUr_state();

//        세션ID와 글작성자가 다른 경우 삭제 테스트
        HttpSession session = new MockHttpSession(); // HttpSession 객체 생성
        session.setAttribute("userId", "david234");
        String ur_id = (String) session.getAttribute("userId");

        assertTrue(saleDao.delete(no, ur_id) == 0);
    }

    @Test
    public void testSameIdRemove() throws Exception {
//        세션ID와 글작성자가 다른 경우 삭제 테스트
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
        saleDao.insertSale(saleDto);
        Long no = saleDto.getNo();

        SaleDto selectSale = saleDao.select(no);
        char bf_state = selectSale.getUr_state();

        assertTrue(bf_state == 'Y');

//        세션ID와 글작성자가 다른 경우 삭제 테스트
        HttpSession session = new MockHttpSession(); // HttpSession 객체 생성
        session.setAttribute("userId", "asdf");
        String ur_id = (String) session.getAttribute("userId");

        assertTrue(saleDao.delete(no, ur_id) == 1);
        assertTrue(saleDao.select(no) == null);
    }

    @Test
    public void testWrite() throws Exception {
        HttpSession session = new MockHttpSession(); // HttpSession 객체 생성
        SaleDto saleDto = saleInsert(session);

        List<String> tagList = new ArrayList<>();
        tagList.add("태그테스트");
        tagList.add("태그중복");
        tagList.add("중복테스트");

        Map mapDto = new HashMap();
        mapDto.put("saleDto", saleDto);
        mapDto.put("tagList", tagList);

        Long sal_no = saleService.write(mapDto);

        System.out.println(sal_no);

        Long num = saleDao.select(sal_no).getNo();

        assertTrue(sal_no == num);
    }

    @Test
    public void testGetList() throws Exception {
        String addr_cd = "11060710";
        String sal_i_cd = null;

        Map map = new HashMap();

        map.put("addr_cd", addr_cd);
        map.put("sal_i_cd", sal_i_cd);

        List<SaleDto> list = saleService.getList(map);
        System.out.println(list.size());

        assertTrue(list.size() != 0);
    }

    public void testGetUserAddrCdList() {
    }

    @Test
    public void testRead() throws Exception {
        Long no = Long.valueOf(1);
        SaleDto before = saleDao.select(no);
        int beforeViewCnt = before.getView_cnt();
        System.out.println("이전 viewcnt : " + beforeViewCnt);
        Map map = saleService.read(no);

        SaleDto saleDto = (SaleDto) map.get("saleDto");

        System.out.println(saleDto.getNo());
        assertTrue(saleDto.getNo().equals(no));

        int afterViewCnt = saleDto.getView_cnt();
        System.out.println("이후 viewcnt : " + afterViewCnt);

        assertTrue(beforeViewCnt == (afterViewCnt-1));
    }

    // 메서드 분리해서 sale, tag, sale_tag 테이블에 데이터 삽입
    // 판매자가 판매 게시글을 작성할 때
    @Test
    public void testWriteMethod() throws Exception {
//        deleteDao.deleteAll();

        // 글을 작성
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
        saleDto.setBuyer_id("1234");
        saleDto.setBuyer_nick("asdf");
        saleDto.setSal_s_cd("C");
        saleDto.setTitle("딥다이브");
        saleDto.setContents("서적 팝니다.");
        saleDto.setBid_cd("N");
        saleDto.setPickup_addr_cd("11060710");
        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saleDto.setBrand("oo북스");
        saleDto.setReg_price(30000);
        saleDto.setFirst_id("asdf");
        saleDto.setLast_id("asdf");
        for(int i=0; i<12; i++) {
            saleDao.insertSale(saleDto);
        }

//        Long sal_no = saleDto.getNo();
//        String ur_id = saleDto.getSeller_id();
//
//        List<String> tagList =  tagList();
//
//        int insertTagTx = insertTagTx(sal_no, ur_id, tagList);
    }

    @Test
    public void testWriteMethodTagDuplicate() throws Exception {
        deleteDao.deleteAll();

        // 글을 작성
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
        saleDao.insertSale(saleDto);

        assertTrue(saleDao.count() == 1);
        Long sal_no = saleDto.getNo();
        String ur_id = saleDto.getSeller_id();

        List<String> tagList =  tagListDuplication();

        int insertTagTx = insertTagTx(sal_no, ur_id, tagList);
    }


    @Test
    public void testWriteMethodTagNone() throws Exception {
        deleteDao.deleteAll();

        // 글을 작성
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
        saleDao.insertSale(saleDto);

        assertTrue(saleDao.count() == 1);
        Long sal_no = saleDto.getNo();
        String ur_id = saleDto.getSeller_id();

        List<String> tagList =  tagListNone();

        int insertTagTx = insertTagTx(sal_no, ur_id, tagList);
    }

    // tag 데이터를 insert하는 트렌젝션 문
    public int insertTagTx(Long sal_no, String ur_id, List<String> tagList) throws Exception {
        System.out.println("insertTagTx 들어옴");
        int insertTagTx = 0;
        int resultSaleTag = 0;
        for (String contents : tagList) {
            TagDto tagDto = tagDao.selectTagContents(contents);
            if (tagDto == null) { // contents가 중복값이 없는 경우
                tagDto = new TagDto(contents, ur_id); // 새로운 객체 생성
                insertTagTx = tagDao.insert(tagDto);
                assertTrue(insertTagTx == 1);
            } else { // contents가 중복값이 있는 경우
                tagDto.setLast_id(ur_id);
                insertTagTx = tagDao.updateSys(tagDto);
                assertTrue(insertTagTx == 1);
            }
            Long tag_no = tagDto.getNo();
            resultSaleTag = insertSaleTagTx(sal_no, tag_no, ur_id);
        }
        return insertTagTx + resultSaleTag;
    }

    // saleTag 교차 테이블 데이터를 insert하는 트렌젝션 문
    public int insertSaleTagTx(Long sal_no, Long tag_no, String ur_id) throws Exception {
        SaleTagDto saleTagDto = new SaleTagDto(sal_no, tag_no, ur_id, ur_id);

        int insertSaleTagTx = saleTagDao.insert(saleTagDto);
        assertTrue(insertSaleTagTx == 1);
        return insertSaleTagTx;
    }

    @Test
    public void testModify() throws Exception {
        Long no = (long) (Math.random() * saleDao.count()+ 1);
    }

    @Test
    public void testGetCount() throws Exception {
        String addr_cd = "11060710";
        String sal_i_cd = null;

        Map map = new HashMap();

        map.put("addr_cd", addr_cd);
        map.put("sal_i_cd", sal_i_cd);

        int totalCnt = saleService.getCount(map);
        System.out.println(totalCnt);

        assertTrue(totalCnt != 0);
    }

    @Test
    public void testGetListPh() throws Exception {
        String addr_cd = "11060710";
//        String sal_i_cd = "016001005";
        String sal_i_cd = null;
        int page = 1;
        int pageSize = 10;

        Map map = new HashMap();

        map.put("addr_cd", addr_cd);
        map.put("sal_i_cd", sal_i_cd);

        int totalCnt = saleService.getCount(map);
        System.out.println(totalCnt);

        assertTrue(totalCnt != 0);

        PageHandler ph = new PageHandler(totalCnt, page, pageSize);

        System.out.println("ph : " + ph);

        map.put("offset", ph.getOffset());
        map.put("pageSize", pageSize);

        List<SaleDto> saleList = saleService.getList(map);

        System.out.println(saleList.size());
    }

    public SaleDto saleInsert(HttpSession session) throws Exception {
        String ur_id = "asdf";
        session = testSession.setSession(ur_id, session);
        ur_id = (String) session.getAttribute("userId");
        System.out.println("ur_id : " + ur_id);

        String seller_id = (String) session.getAttribute("userId");
        String seller_nick = (String) session.getAttribute("userNick");

        List<AddrCdDto> addrCdList = (List<AddrCdDto>) session.getAttribute("userAddrCdDtoList");
        // addrCdList 중 첫번째 값이 기본 주소값
        AddrCdDto addrCdDto = addrCdList.get(0);
        String addr_cd = addrCdDto.getAddr_cd();
        String addr_name = addrCdDto.getAddr_name();

        SaleDto saleDto = new SaleDto();
        saleDto.setSeller_id(seller_id);
        saleDto.setSeller_nick(seller_nick);
        saleDto.setAddr_cd(addr_cd);
        saleDto.setAddr_name(addr_name);
        saleDto.setSal_i_cd("016001005");
        saleDto.setSal_name("016001005");
        saleDto.setPro_s_cd("C");
        saleDto.setTx_s_cd("S");
        saleDto.setTrade_s_cd_1("F");
        saleDto.setPrice(28000);
        saleDto.setSal_s_cd("S");
        saleDto.setTitle("동화책 팔아요");
        saleDto.setContents("어린이 동화책 전집 팔이요.");
        saleDto.setBid_cd("N");
        saleDto.setPickup_addr_cd("11060710");
        saleDto.setPickup_addr_name("서울특별시 동대문구 회기동");
        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saleDto.setBrand("ㅇㅇ북스");
        saleDto.setReg_price(30000);

        return saleDto;
    }

    public List<String> tagList() throws Exception {
        List<String> contents = new ArrayList<>();
        contents.add("태그테스트");
        contents.add("태그입력");
        contents.add("입력테스트");

        return contents;
    }

    public List<String> tagListDuplication() throws Exception {
        List<String> contents = new ArrayList<>();
        contents.add("태그테스트");
        contents.add("태그중복");
        contents.add("중복테스트");

        return contents;
    }

    public List<String> tagListNone() throws Exception {
        List<String> contents = new ArrayList<>();

        return contents;
    }
}