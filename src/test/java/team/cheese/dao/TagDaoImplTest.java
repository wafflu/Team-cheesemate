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
        saleTagDao.deleteAll();
        tagDao.deleteAll();

        // 글을 새로 하나 작성했다고 가정 후 진행
        SaleDto saleDto = saleDeleteInsert(); // deleteAll -> insert
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
    }

    @Test
    public void testTagDuplicatoinInsert() throws Exception {
        // 글을 새로 하나 추가로 더 작성했다고 가정 후 진행
        SaleDto saleDto = saleInsert(); // insert
        Long sal_no = saleDto.getNo();
        String seller_id = saleDto.getSeller_id();

        List<String> tagList =  tagListDuplication();
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
        saleDto.setSeller_id("asdf");
        saleDto.setSal_i_cd("016001005");
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
        saleDto.setBrand("어린이 동화책");
        saleDto.setReg_price(30000);
        saleDto.setCheck_addr_cd(0);

        saleDao.insert(saleDto);

        return saleDto;
    }

    public SaleDto saleInsert() throws Exception {
        // 2. 게시글 1개 insert
        SaleDto saleDto = new SaleDto();
        saleDto.setSeller_id("david234");
        saleDto.setSal_i_cd("016001005");
        saleDto.setPro_s_cd("C");
        saleDto.setTx_s_cd("S");
        saleDto.setTrade_s_cd_1("F");
        saleDto.setPrice(80000);
        saleDto.setSal_s_cd("S");
        saleDto.setTitle("WHY 책 팔아요");
        saleDto.setContents("WHY 책 전집 팔이요.");
        saleDto.setBid_cd("N");
        saleDto.setPickup_addr_cd("11060710");
        saleDto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saleDto.setReg_price(210000);
        saleDto.setCheck_addr_cd(0);

        saleDao.insert(saleDto);

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