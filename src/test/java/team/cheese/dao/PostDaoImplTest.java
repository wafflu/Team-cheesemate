package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.Domain.Post.PostDto;
import team.cheese.dao.Post.PostDao;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class PostDaoImplTest {
    @Autowired
    PostDao postDao;

    @Test
    public void countAll() throws Exception {
        System.out.println(postDao.count());
    }

    @Test
    public void insert() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setUrId("user123");
        postDto.setAddrCd("11010720");
        postDto.setNo(1);
        postDto.setCommuCd("commu_L");
        postDto.setAddrName("서울특별시 종로구 사직동");
        postDto.setTitle("좋아하는사람 제목");
        postDto.setContents("좋아하는 사람 내용");
        postDto.setNick("skyLee");

        int result = postDao.insert(postDto);
        assertEquals(1,result);
    }

    @Test
    public void selectAll() throws Exception {
        List<PostDto> list = postDao.selectAll();

        Iterator it = list.iterator();
//        while(it.hasNext()){
//            it.next();
//        }
        System.out.println(postDao.selectAll());
    }
}