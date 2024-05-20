package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.domain.Comment.CommentDto;
import team.cheese.domain.CommunityBoard.CommunityBoardDto;
import team.cheese.dao.Comment.CommentDao;
import team.cheese.dao.CommunityBoard.CommunityBoardDao;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommentDaoImplTest {

    @Autowired
    CommentDao commentDao;

    @Autowired
    CommunityBoardDao communityBoardDao;

    //카운팅
    @Test
    public void count()throws Exception{
        commentDao.deleteAll();

        CommentDto commentDto = insertComment();
        commentDao.insert(commentDto);

        commentDto = commentDao.select(commentDto.getNo());
        int result = commentDao.count(commentDto.getNo());
        assertEquals(1,result);



    }

    //인서트

    @Test
    public void insert()throws Exception{
        commentDao.deleteAll();

        CommentDto commentDto = insertComment();
        commentDao.insert(commentDto);

        commentDto = commentDao.select(commentDto.getNo());


        assertEquals('y',commentDto.getUr_state());

    }

    //업데이트
    @Test
    public void updateComment()throws Exception{
        commentDao.deleteAll();
        CommentDto commentDto = insertComment();
        commentDao.insert(commentDto);
        commentDto = commentDao.select(commentDto.getNo());

        assertEquals(1,commentDao.count(commentDto.getNo()));

        commentDto.setContents("바꿨음");
        commentDao.update(commentDto);
        commentDto = commentDao.select(commentDto.getNo());
        assertEquals(1,commentDao.count(commentDto.getNo()));
        assertEquals("바꿨음",commentDto.getContents());


    }



    @Test
    public void userChangeState()throws Exception{
        commentDao.deleteAll();
        CommentDto commentDto = insertComment();
        commentDao.insert(commentDto);
        commentDto = commentDao.select(commentDto.getNo());

        assertEquals(1,commentDao.count(commentDto.getNo()));

        commentDto.setUr_state('n');
        commentDao.userChangeState(commentDto);
        commentDto = commentDao.select(commentDto.getNo());
        int result = commentDao.count(commentDto.getNo());
        assertEquals(1,result);
        assertEquals('n',commentDto.getUr_state());

    }

    @Test
    public void delete()throws Exception{
        commentDao.deleteAll();
        CommentDto commentDto = insertComment();
        commentDao.insert(commentDto);
        assertEquals(1,commentDao.count(commentDto.getNo()));
        commentDao.delete(commentDto.getNo());
        assertEquals(0,commentDao.count(commentDto.getNo()));
    }





    @Test
    public void getComment()throws Exception{
        commentDao.deleteAll();
        CommentDto commentDto = insertComment();
        commentDao.insert(commentDto);
        commentDto = commentDao.select(commentDto.getNo());

        List<CommentDto> list = commentDao.selectAll(commentDto.getPost_no());

        assertEquals(1,list.size());
        list.add(commentDao.select(commentDto.getNo()));


    }


    public CommentDto insertComment()throws Exception{
        CommunityBoardDto communityBoardDto = community();
        int post_no = communityBoardDto.getno();
        CommentDto commentDto = new CommentDto();
        commentDto.setPost_no(post_no);
        commentDto.setNo(1);
        commentDto.setNick("skyLee");
        commentDto.setUr_id("user123");
        commentDto.setContents("댓글테스트메서드");

        return commentDto;
    }


    @Test
    public void findMaxNoByPostNo()throws Exception{
        commentDao.deleteAll();

        commentDao.deleteAll();
        CommentDto commentDto = insertComment();
        commentDao.insert(commentDto);
        commentDto = commentDao.select(commentDto.getNo());

        commentDao.findMaxByPostNo(commentDto.getPost_no());
        assertEquals(1,commentDao.count(commentDto.getNo()));
    }




    public CommunityBoardDto community() throws Exception {
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("라이트 테스트");
        communityBoardDto.setContents("라이트 테스트");
        communityBoardDto.setNick("skyLee");
        communityBoardDto.setur_state('y'); // 상태 설정

        communityBoardDao.insert(communityBoardDto);
        return communityBoardDto;
    }



}
