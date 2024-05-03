package team.cheese.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.domain.TagDto;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class TagDaoImplTest extends TestCase {
    @Autowired
    TagDao tagDao;

    @Test
    public void testCount() throws Exception {
        System.out.println("count : " + tagDao.count());
//        int cnt = saledao.count();
//        assertTrue(cnt == 8);
    }

    @Test
    public void testDeleteAll() throws Exception {
        System.out.println(tagDao.deleteAll());
        System.out.println("count : " + tagDao.count());
    }

    @Test
    public void testInsert() throws Exception {
        TagDto tagDto = new TagDto();
        tagDto.setContents("테스트");
        String seller_id = "test";
        tagDto.setFirst_id(seller_id);
        tagDto.setLast_id(seller_id);
        System.out.println(tagDao.insert(tagDto));
        System.out.println(tagDto.getNo());
        tagDao.deleteAll();
        tagDao.count();
        System.out.println(tagDao.count());
        tagDao.resetAutoIncrement();
        TagDto tagDto2 = new TagDto();
        tagDto2.setContents("테스트");
        String seller_id2 = "test";
        tagDto2.setFirst_id(seller_id2);
        tagDto2.setLast_id(seller_id2);
        System.out.println(tagDao.insert(tagDto2));
        System.out.println(tagDto2.getNo());
//        assertTrue(tagDto.getNo() == 1);
    }


}