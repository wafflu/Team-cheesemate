package team.cheese.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.domain.SaleDto;
import team.cheese.domain.SaleTagDto;
import team.cheese.domain.TagDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class SaleTagDaoImplTest extends TestCase {
    @Autowired
    DeleteDao deleteDao;

    @Autowired
    SaleTagDao saleTagDao;
    @Autowired
    TagDao tagDao;
    @Autowired
    SaleDao saleDao;

    @Test
    public void testTagSaleTagInsertSelect() throws Exception {
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
        List<SaleTagDto> saleTagList = saleTagDao.selectSalNo(sal_no);
        System.out.println(saleTagList);
        assertTrue(tagList.size() == saleTagList.size());
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
    public void testDeleteSalNoTag() throws Exception {
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

        int delete = saleTagDao.delete(sal_no);
        assertTrue(delete == tagList.size());

        List<SaleTagDto> saleTagList = saleTagDao.selectSalNo(sal_no);
        assertTrue(saleTagList.size() == 0);
    }

    @Test
    public void testTagNoneInsert() throws Exception {
        deleteDao.deleteAll();

        // 글을 작성
        SaleDto saleDto = saleInsert();
        assertTrue(saleDao.count() == 1);
        Long sal_no = saleDto.getNo();
        String seller_id = saleDto.getSeller_id();

        List<String> tagList =  tagListNone();
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

    public List<String> tagListNone() throws Exception {
        List<String> contents = new ArrayList<>();

        return contents;
    }
}