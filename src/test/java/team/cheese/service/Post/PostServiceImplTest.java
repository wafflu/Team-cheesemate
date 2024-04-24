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
}