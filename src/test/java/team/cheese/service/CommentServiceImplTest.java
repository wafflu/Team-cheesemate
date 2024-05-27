//package team.cheese.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import team.cheese.domain.Comment.CommentDto;
//import team.cheese.domain.CommunityBoard.CommunityBoardDto;
//import team.cheese.dao.Comment.CommentDao;
//import team.cheese.dao.CommunityBoard.CommunityBoardDao;
//import team.cheese.service.Comment.CommentService;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class CommentServiceImplTest {
//    @Autowired
//    CommentService commentService;
//
//    @Autowired
//    CommentDao commentDao;
//
//    @Autowired
//    CommunityBoardDao communityBoardDao;
//
//
//
//    @Test
//    public void write()throws Exception{
//        //전체 삭제
//        communityBoardDao.deleteAll();
//
//
//        int cc = community();
//
//        CommunityBoardDto communityBoardDto = communityBoardDao.select(cc);
//
//        CommentDto commentDto = new CommentDto();
//        commentDto.setUr_id("user123");
//        commentDto.setPost_no(communityBoardDto.getno());
//        commentDto.setNo(1);
//        commentDto.setNick("skyLee");
//        commentDto.setContents("댓글");
//        commentService.write(commentDto);
//        commentDto = commentDao.select(commentDto.getNo());
//
//        communityBoardDto = communityBoardDao.select(communityBoardDto.getno());
//        System.out.println(communityBoardDto.toString());
//        int count = communityBoardDto.getComment_count();
//
//
//        assertEquals(1,count);
//
//    }
//
//    @Test
//    public void read()throws Exception{
//        commentDao.deleteAll();
//        int cc = community();
//        CommunityBoardDto communityBoardDto = communityBoardDao.select(cc);
//
//        CommentDto commentDto = new CommentDto();
//        commentDto.setUr_id("user123");
//        commentDto.setPost_no(communityBoardDto.getno());
//        commentDto.setNo(1);
//        commentDto.setNick("skyLee");
//        commentDto.setContents("댓글");
//        commentService.write(commentDto);
//        commentDto = commentDao.select(commentDto.getNo());
//
//        communityBoardDto = communityBoardDao.select(communityBoardDto.getno());
//        System.out.println(communityBoardDto.toString());
//        int count = communityBoardDto.getComment_count();
//        assertEquals(1,count);
//        commentDto = commentService.read(commentDto.getNo());
//        System.out.println(commentDto.toString());
//    }
//
//
//
//    @Test
//    public void readAll()throws Exception{
//        commentDao.deleteAll();
//        int cc = community();
//        CommunityBoardDto communityBoardDto = communityBoardDao.select(cc);
//        List<CommentDto> list = new ArrayList<>();
//        CommentDto commentDto = new CommentDto();
//        commentDto.setUr_id("user123");
//        commentDto.setPost_no(communityBoardDto.getno());
//        commentDto.setNo(1);
//        commentDto.setNick("skyLee");
//        commentDto.setContents("댓글1");
//        commentService.write(commentDto);
//        commentDto = commentDao.select(commentDto.getNo());
//        list.add(commentDao.select(commentDto.getNo()));
//
//        commentDto.setUr_id("user123");
//        commentDto.setPost_no(communityBoardDto.getno());
//        commentDto.setNo(2);
//        commentDto.setNick("skyLee");
//        commentDto.setContents("댓글2");
//        commentService.write(commentDto);
//        list.add(commentDao.select(commentDto.getNo()));
//
//
//
//        Iterator<CommentDto> iterator = list.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next().toString());
//        }
//        assertEquals(2,list.size());
//    }
//
//
//
//    @Test
//    public void userStateChange()throws Exception{
//        commentDao.deleteAll();
//        int cc = community();
//        CommunityBoardDto communityBoardDto = communityBoardDao.select(cc);
//        List<CommentDto> list = new ArrayList<>();
//        CommentDto commentDto = new CommentDto();
//        commentDto.setUr_id("user123");
//        commentDto.setPost_no(communityBoardDto.getno());
//        commentDto.setNo(1);
//        commentDto.setNick("skyLee");
//        commentDto.setContents("댓글1");
//        commentService.write(commentDto);
//        commentDto = commentDao.select(commentDto.getNo());
//
//        commentDto.setUr_state('n');
//        int stateChange = commentService.userChangeState(commentDto);
//        assertEquals(1,stateChange);
//        assertEquals('n',commentDto.getUr_state());
//
//
//    }
//    public int community() throws Exception {
//        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
//        communityBoardDto.setur_id("user123");
//        communityBoardDto.setaddr_cd("11010720");
//        communityBoardDto.setaddr_no(1);
//        communityBoardDto.setcommu_cd("commu_L");
//        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
//        communityBoardDto.setTitle("라이트 테스트");
//        communityBoardDto.setContents("라이트 테스트");
//        communityBoardDto.setNick("skyLee");
//        communityBoardDto.setur_state('y'); // 상태 설정
//
//        communityBoardDao.insert(communityBoardDto);
//        return communityBoardDto.getno();
//    }
//
//
//}
