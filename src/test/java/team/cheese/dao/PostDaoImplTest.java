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
import static org.junit.Assert.assertTrue;


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
        postDto.setur_id("user123");
        postDto.setaddr_cd("11010720");
        postDto.setNo(1);
        postDto.setcommu_cd("commu_L");
        postDto.setaddr_name("서울특별시 종로구 사직동");
        postDto.setTitle("좋아하는사람 제목");
        postDto.setContents("좋아하는 사람 내용");
        postDto.setNick("skyLee");

        int result = postDao.insert(postDto);
        assertEquals(1,result);
    }

    @Test
    public void insertAll()throws Exception{
        PostDto postDto = new PostDto();
        for (int i = 0; i < 10; i++) {
            postDto.setur_id("user123");
            postDto.setaddr_cd("11010720");
            postDto.setNo(1);
            postDto.setcommu_cd("commu_L");
            postDto.setaddr_name("서울특별시 종로구 사직동");
            postDto.setTitle("좋아하는사람 제목" + i);
            postDto.setContents("좋아하는 사람 내용" + i) ;
            postDto.setNick("skyLee");
            postDao.insert(postDto);
        }

        for (int i = 0; i < 10; i++) {
            postDto.setur_id("david234");
            postDto.setaddr_cd("11010530");
            postDto.setNo(2);
            postDto.setcommu_cd("commu_B");
            postDto.setaddr_name("서울특별시 종로구 청운효자동");
            postDto.setTitle("잡담제목" + i);
            postDto.setContents("잡담내용" + i) ;
            postDto.setNick("minyoung");
            postDao.insert(postDto);
        }




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

    @Test
    public void select()throws Exception{

        System.out.println(postDao.select(30));
    }

    @Test
    public void delete()throws Exception{
        //given:테스트 테이블 insert
        PostDto postDto = new PostDto();
        postDto.setur_id("user123");
        postDto.setaddr_cd("11010720");
        postDto.setNo(1);
        postDto.setcommu_cd("commu_L");
        postDto.setaddr_name("서울특별시 종로구 사직동");
        postDto.setTitle("테스트 제목");
        postDto.setContents("테스트 내용");
        postDto.setNick("skyLee");

        int insertResult = postDao.insert(postDto);
        assertEquals(1,insertResult);
        //do:테스트 테이블 delete


        //assert:delete되었는지 확인
        assertEquals(1, postDao.delete(44));
    }

    @Test
    public void deleteAll()throws Exception{
        postDao.deleteAll();

    };


    @Test
    public void update()throws Exception {
        PostDto postDto = new PostDto();
        postDto.setSn(59);
        postDto.setTitle("업데이트 수정테스트");
        postDto.setContents("업데이트 수정테스트");


        int result = postDao.update(postDto);
        assertEquals(1,result);
    }



    @Test
    public void increaseViewCnt()throws Exception{
        //given : 특정 sn을 select
        //do view_cnt++
        //assert
        PostDto postDto = new PostDto();
        postDto.setur_id("user123");
        postDto.setaddr_cd("11010720");
        postDto.setNo(1);
        postDto.setcommu_cd("commu_L");
        postDto.setaddr_name("서울특별시 종로구 사직동");
        postDto.setTitle("테스트 뷰");
        postDto.setContents("테스트 뷰");
        postDto.setNick("skyLee");

        postDao.insert(postDto);

        postDao.select(52);
        assertTrue(postDto!=null);

        postDao.increaseViewCnt(52);
        System.out.println(postDao.select(52));
        System.out.println(postDao.select(52).getview_cnt());
        assertTrue(postDao.select(52).getview_cnt()>0);



//        postDto = postDao.select(24);
//        assertEquals(2,postDto.getview_cnt());
    }

    @Test
    public void getTopTen()throws Exception{
        List<PostDto>getTopTen = postDao.getTopTen();
        getTopTen.forEach(System.out::println);


    }
}