package team.cheese.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.domain.SaleDto;
import team.cheese.domain.SaleTagDto;
import team.cheese.domain.TagDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class TagDaoImplTest extends TestCase {
    @Autowired
    TagDao tagDao;

    @Autowired
    DeleteDao deleteDao;
    @Autowired
    SaleDao saleDao;
    @Autowired
    SaleTagDao saleTagDao;

    @Test
    public void testDeleteAllCount() throws Exception {
        saleTagDao.deleteAll();
        tagDao.deleteAll();

        System.out.println("count : " + tagDao.count());
        int cnt = tagDao.count();
        assertTrue(cnt == 0);
    }

    @Test
    public void testTagInsert() throws Exception {
        deleteDao.deleteAll();

        SaleDto saleDto = saleInsert(); // insert
        Long sal_no = saleDto.getNo();
        String seller_id = saleDto.getSeller_id();

        List<String> tagList =  new ArrayList<>();
        tagList.add("태그입력1");
        tagList.add("태그입력2");
        tagList.add("태그입력3");

        for(String contents : tagList) {
            System.out.println(contents);

            TagDto tagDto = tagDao.selectTagContents(contents);
            if (tagDto == null) { // contents가 중복값이 없는 경우
                assertNull(tagDto);
                tagDto = new TagDto(contents, seller_id); // 새로운 객체 생성

                int resultTag = tagDao.insert(tagDto);
                assertTrue(resultTag == 1);
            }
        }
    }

    @Test
    public void testTagDuplicateInsert() throws Exception {
        SaleDto saleDto = saleInsert(); // insert
        Long sal_no = saleDto.getNo();
        String seller_id = saleDto.getSeller_id();

        List<String> tagList =  new ArrayList<>();
        tagList.add("태그입력1");
        tagList.add("태그입력2");
        tagList.add("태그입력3");

        for(String contents : tagList) {
            System.out.println(contents);

            TagDto tagDto = tagDao.selectTagContents(contents);
            System.out.println(tagDto);
            if (tagDto != null) { // contents가 중복값이 있는 경우
                assertNotNull(tagDto);

                tagDto.setLast_id(seller_id);

                int resutUpdate = tagDao.updateSys(tagDto);
                assertTrue(resutUpdate == 1);
                assertTrue(tagDto.getFirst_date() != tagDto.getLast_date());
            }
        }
    }

    @Test
    public void testTagVarietyInsert() throws Exception {
        SaleDto saleDto = saleInsert(); // insert
        Long sal_no = saleDto.getNo();
        String seller_id = saleDto.getSeller_id();

        List<String> tagList =  new ArrayList<>();
        tagList.add("태그입력1");
        tagList.add("태그입력3");
        tagList.add("태그입력4");

        for(String contents : tagList) {
            System.out.println(contents);

            TagDto tagDto = tagDao.selectTagContents(contents);
            System.out.println(tagDto);
            if (tagDto == null) { // contents가 중복값이 없는 경우
                assertNull(tagDto);
                tagDto = new TagDto(); // 새로운 객체 생성

                tagDto.setContents(contents);
                tagDto.setFirst_id(seller_id);
                tagDto.setLast_id(seller_id);

                int resultTag = tagDao.insert(tagDto);
                assertTrue(resultTag == 1);
            } else {
                assertNotNull(tagDto);

                tagDto.setLast_id(seller_id);

                int resutUpdate = tagDao.updateSys(tagDto);
                assertTrue(resutUpdate == 1);
                assertTrue(tagDto.getFirst_date() != tagDto.getLast_date());
            }
        }
    }

    @Test
    // update Test
    public void testUpdateTag() throws Exception {
        // 1. 판매글 선택해서 update진행
        // 2. 임의의 판매글을 선택했다고 가정
        // 3. tag테이블에 해시태그 존재하는지 확인
        Long sal_no = (long) (Math.random() * saleDao.count()) + 1;
        SaleDto saleDto = saleDao.select(sal_no);
        String seller_id = saleDto.getSeller_id();
        System.out.println(seller_id);

        List<String> tagList = new ArrayList<>();
        tagList.add("태그테스트");
        tagList.add("태그업데이트");
        tagList.add("업데이트테스트");

        for(String contents : tagList) {
            System.out.println(contents);

            // 태그 존재하는지 확인
            TagDto tagDto = tagDao.selectTagContents(contents);
            System.out.println("tagDto 확인 : " + tagDto);
            if (tagDto == null) { // contents가 중복값이 없는 경우
                assertNull(tagDto);
                tagDto = new TagDto(); // 새로운 객체 생성

                tagDto.setContents(contents);
                tagDto.setFirst_id(seller_id);
                tagDto.setLast_id(seller_id);

                int resultTag = tagDao.insert(tagDto);
                assertTrue(resultTag == 1);

                Long tag_no = tagDto.getNo();
                System.out.println("insert된 tag의 no : " + tag_no);

            } else { // contents가 중복값이 있는 경우
                assertNotNull(tagDto);

                tagDto.setLast_id(seller_id);

                int resutUpdate = tagDao.updateSys(tagDto);
                assertTrue(resutUpdate == 1);
            }
            assertTrue(tagDto.getLast_id() == seller_id);
        }
    }

    public SaleDto saleDeleteInsert() throws Exception {
        // 1. 게시글 전체 삭제
        deleteDao.deleteAll();

        // 2. 반복문으로 동일한 게시글 1개 insert
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

        return saleDto;
    }

    @Test
    public void testGetTagContents() throws Exception {
        deleteDao.deleteAll();

        // 글을 작성
        SaleDto saleDto = saleInsert();
        assertTrue(saleDao.count() == 1);
        Long sal_no = saleDto.getNo();
        String seller_id = saleDto.getSeller_id();

        List<String> tagList =  tagList();
        for(String contents : tagList) {
            System.out.println(contents);

            TagDto tagDto = tagDao.selectTagContents(contents);
            System.out.println("tagDto 확인 : " + tagDto);
            if (tagDto == null) { // contents가 중복값이 없는 경우
                assertNull(tagDto);
                tagDto = new TagDto(); // 새로운 객체 생성

                tagDto.setContents(contents);
                tagDto.setFirst_id(seller_id);
                tagDto.setLast_id(seller_id);

                int resultTag = tagDao.insert(tagDto);
                assertTrue(resultTag == 1);

                Long tag_no = tagDto.getNo();
                System.out.println("insert된 tag의 no : " + tag_no);

                SaleTagDto saleTagDto = new SaleTagDto();
                saleTagDto.setSal_no(sal_no);
                saleTagDto.setTag_no(tag_no);
                saleTagDto.setFirst_id(seller_id);
                saleTagDto.setLast_id(seller_id);

                int resultSaleTag = saleTagDao.insert(saleTagDto);
                assertTrue(resultSaleTag == 1);
            } else { // contents가 중복값이 있는 경우
                assertNotNull(tagDto);

                tagDto.setLast_id(seller_id);

                int resutUpdate = tagDao.updateSys(tagDto);
                assertTrue(resutUpdate == 1);

                Long tag_no = tagDto.getNo();

                SaleTagDto saleTagDto = new SaleTagDto();
                saleTagDto.setSal_no(sal_no);
                saleTagDto.setTag_no(tag_no);
                saleTagDto.setFirst_id(seller_id);
                saleTagDto.setLast_id(seller_id);

                int resultSaleTag = saleTagDao.insert(saleTagDto);
                assertTrue(resultSaleTag == 1);
            }
        }

        SaleDto selectSaleDto = saleDao.select(1L);
        Long no = selectSaleDto.getNo();

        // 작성한 태그의 개수를 확인
        int tagSize = tagList.size();

        // 글을 하나 선택하여 작성한 태그의 개수가 일치하는지 확인
        List<TagDto> tag = tagDao.getTagContents(no);

        int loadTagSize = tag.size();

        assertTrue(tagSize == loadTagSize);
    }

    public SaleDto saleInsert() throws Exception {
        // 게시글 1개 insert
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