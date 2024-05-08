package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
import team.cheese.dao.CommunityBoard.CommunityBoardDao;
import team.cheese.dao.CommunityHeart.CommunityHeartDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommunityHeartDaoImplTest {

    @Autowired
    CommunityHeartDao communityHeartDao;

    @Autowired
    CommunityBoardDao communityBoardDao;

    @Test
    public void testDoLike() throws Exception {


        CommunityBoardDto communityBoardDto = community();
        int post_no = communityBoardDto.getno();

        //given
        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
        communityHeartDto.setPost_no(post_no);
        communityHeartDto.setUr_id("user123");
        communityHeartDao.doLike(communityHeartDto);
        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());
//        System.out.println(communityHeartDto.toString());
        assertEquals('y',communityHeartDto.getUr_state());

        communityHeartDao.doLike(communityHeartDto);
        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());
//        System.out.println(communityHeartDto.toString());
        assertEquals('n',communityHeartDto.getUr_state());

    }

//
//    //하트 삭제
//    @Test
//    public void deleteLike()throws Exception{
//        //init
//        communityHeartDao.deleteAll();
//        assertEquals(0,communityHeartDao.count());
//        //given1:하트 생성
//        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
//        communityHeartDto.setPost_no(313);
//        communityHeartDto.setUr_id("user123");
//        communityHeartDao.insertLike(communityHeartDto);
//
//        assertEquals(1,communityHeartDao.countAllLikes(313));
//        //given2:하트 삭제
//        communityHeartDto.setUr_id("user123");
//        communityHeartDto.setPost_no(313);
//        communityHeartDao.deleteLike(communityHeartDto);
//
//        //assert:하트 삭제되었는지 확인
//        assertEquals(0,communityHeartDao.countAllLikes(313));
//
//    }
//
//
//    //하트 삭제가 안될때
//    @Test
//    public void deleteLike2()throws Exception{
//        //init
//        communityHeartDao.deleteAll();
//        assertEquals(0,communityHeartDao.count());
//        //given1:하트 생성
//        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
//        communityHeartDto.setPost_no(313);
//        communityHeartDto.setUr_id("user123");
//        communityHeartDao.insertLike(communityHeartDto);
//        assertEquals(1,communityHeartDao.countAllLikes(313));
//
//        //given2:아이디값이 일치하지않을때 - 불일치 필요 , 1값이 유지
//        communityHeartDto.setUr_id("user456");
//        communityHeartDto.setPost_no(313);
//        communityHeartDao.deleteLike(communityHeartDto);
//
//        //assert:하트가 여전히 유지
//        assertEquals(1, communityHeartDao.countAllLikes(313));
//        //given3:번호가 일치하지않을때 - 불일치 필요
//        communityHeartDto.setUr_id("user123");
//        communityHeartDto.setPost_no(315);
//        communityHeartDao.deleteLike(communityHeartDto);
//
//        //assert:하트가 여전히 유지
//        assertEquals(1, communityHeartDao.countAllLikes(313));
//    }

//
//    //하트 채우기
//    @Test
//    public void insertLike()throws Exception{
//        //init
//        communityHeartDao.deleteAll();
//        assertEquals(0,communityHeartDao.count());
//        CommunityBoardDto communityBoardDto = community();
//        int post_no = communityBoardDto.getno();
//
//        //given
//        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
//        communityHeartDto.setPost_no(post_no);
//        communityHeartDto.setUr_id("user123");
//        communityHeartDto.setUr_state('y');
//        communityHeartDao.insertLike(communityHeartDto);
//
//        //assert
//        assertEquals(1,communityHeartDao.countAllLikes(post_no));
//
//
//        //assert2
//        System.out.println(communityHeartDto.toString());
//    }
//
//
//
//    //게시물하나당 총 하트의 개수
//    @Test
//    public void countAllLikes()throws Exception{
//        //init
//        communityHeartDao.deleteAll();
//        assertEquals(0,communityHeartDao.count());
//        CommunityBoardDto communityBoardDto = community();
//        int post_no = communityBoardDto.getno();
//
//        //given
//        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
//        communityHeartDto.setPost_no(post_no);
//        communityHeartDto.setUr_id("user123");
//        communityHeartDto.setUr_state('y');
//        communityHeartDao.insertLike(communityHeartDto);
//
//        //assert
//        assertEquals(1,communityHeartDao.countAllLikes(post_no));
//
//
//
//
//    }
//
//
//
//
//
//    //하트 상태 바꾸기
//    @Test
//    public void changeHeartStatus()throws Exception{
//        //하트 만들기
//        //init
//        communityHeartDao.deleteAll();
//        assertEquals(0,communityHeartDao.count());
//        CommunityBoardDto communityBoardDto = community();
//        int post_no = communityBoardDto.getno();
//
//        //given
//        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
//        communityHeartDto.setPost_no(post_no);
//        communityHeartDto.setUr_id("user123");
//        communityHeartDto.setUr_state('y');
//        communityHeartDao.insertLike(communityHeartDto);
//
//        //assert
//        assertEquals(1,communityHeartDao.countAllLikes(post_no));
//
//        //하트 상태 변경
//        communityHeartDto.setUr_state('n');
//        communityHeartDao.changeHeartState(communityHeartDto);
//        assertEquals('n',communityHeartDto.getUr_state());
//
//    }
//
//
//
//
//    //하트의 상태 숫자
//    @Test
//    public void countLikeStatus()throws Exception{
//        //하트 만들기
//        //init
//        communityHeartDao.deleteAll();
//        assertEquals(0,communityHeartDao.count());
//        CommunityBoardDto communityBoardDto = community();
//        int post_no = communityBoardDto.getno();
//
//        //given
//        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
//        communityHeartDto.setPost_no(post_no);
//        communityHeartDto.setUr_id("user123");
//        communityHeartDto.setUr_state('y');
//        communityHeartDao.insertLike(communityHeartDto);
//
//        //assert
//        assertEquals(1,communityHeartDao.countLikeStatus(communityHeartDto));
//
//        //하트 상태 변경
//        communityHeartDto.setUr_state('n');
//        communityHeartDao.changeHeartState(communityHeartDto);
//        assertEquals('n',communityHeartDto.getUr_state());
//        assertEquals(0,communityHeartDao.countLikeStatus(communityHeartDto));
//
//
//    }
//
//

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
