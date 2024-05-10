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
    TestSession testSession;

    public void testGetCount() {
    }

    public void testRemove() {
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

    public void testInsertTagTx() {
    }

    public void testInsertSaleTagTx() {
    }

    @Test
    public void testGetList() throws Exception {
        String addr_cd = null;
        List<SaleDto> list = saleService.getUserAddrCdList(addr_cd);
        System.out.println(list.size());
        assertTrue(list.size() == 27);
    }

    public void testGetUserAddrCdList() {
    }

    @Test
    public void testRead() throws Exception {
        Long no = Long.valueOf(1);
        SaleDto saleDto = saleService.read(no);

        System.out.println(saleDto.getNo());
        assertTrue(saleDto.getNo().equals(no));
    }

    public void testModify() {
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
        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saleDto.setBrand("ㅇㅇ북스");
        saleDto.setReg_price(30000);
        saleDto.setCheck_addr_cd(0);

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
}