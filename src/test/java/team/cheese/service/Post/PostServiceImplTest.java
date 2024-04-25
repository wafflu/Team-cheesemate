package team.cheese.service.Post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.Domain.Post.PostDto;
import team.cheese.dao.Post.PostDao;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class PostServiceImplTest {
    @Autowired
    PostDao postDao;

    @Autowired
    PostService postService;

    @Test
    public void selectAll() throws Exception {
        List<PostDto> list = postService.selectAll();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    @Test
    public void write() throws Exception {
        int beforeCount = postService.getCount();
        PostDto postDto = new PostDto();
        postDto.setur_id("user123");
        postDto.setaddr_cd("11010720");
        postDto.setNo(1);
        postDto.setcommu_cd("commu_L");
        postDto.setaddr_name("서울특별시 종로구 사직동");
        postDto.setTitle("라이트 테스트");
        postDto.setContents("라이트 테스트");
        postDto.setNick("skyLee");
        postService.write(postDto);
        int afterCount = postService.getCount();
        assertTrue(afterCount== beforeCount + 1);
        System.out.println(postService.getCount());
    }


    @Test
    public void remove() throws Exception {
        if(postDao.select(33)!= null){
            postService.remove(33);
        }

    }

    @Test
    public void modify() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setur_id("user123");
        postDto.setaddr_cd("11010720");
        postDto.setNo(1);
        postDto.setcommu_cd("commu_L");
        postDto.setaddr_name("서울특별시 종로구 사직동");
        postDto.setTitle("라이트 테스트");
        postDto.setContents("라이트 테스트");
        postDto.setNick("skyLee");
         postService.write(postDto);



        int beforeCnt =  postService.getCount();


        postDto.setSn(61);
        postDto.setTitle("라이트수정테스트");
        postDto.setContents("라이트 수정 테스트");
        postService.modify(postDto);

//        postDao.select(60);

        int afterCnt =  postService.getCount();
        assertEquals(beforeCnt, afterCnt);


    }


}