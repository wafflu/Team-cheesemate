//package team.cheese.service.CommunityBoard;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
//import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
//import team.cheese.dao.CommunityBoard.CommunityBoardDao;
//import team.cheese.dao.CommunityHeart.CommunityHeartDao;
//import team.cheese.service.CommunityHeart.CommunityHeartService;
//
//import static org.junit.Assert.*;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class CommunityHeartServiceImplTest {
//
//
//    @Autowired
//    CommunityBoardService communityBoardService;
//
//    @Autowired
//    CommunityHeartDao communityHeartDao;
//
//    @Autowired
//    CommunityHeartService communityHeartService;
//
//
//    @Test(expected = IllegalArgumentException.class)
//    public void doLike() throws Exception {
//        //init
//        communityHeartDao.deleteAll();
//        assertEquals(0, communityHeartDao.count());
//
//        CommunityBoardDto communityBoardDto = community();
//        int post_no = communityBoardDto.getno();
//
//        //given1: ur_id와 post_no 둘 다 존재할때
//        CommunityHeartDto communityHeartDto = new CommunityHeartDto("user123", post_no,'y');
//        communityHeartService.doLike(communityHeartDto);
//
////        System.out.println(communityHeartDto.toString());
////        assert1:ur_id와 post_no가 존재할때
////        assertEquals(1, communityHeartDao.count());
////        assertEquals(1, communityHeartDao.countLikeStatus(communityHeartDto));
////
////        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());
////        assertEquals('y',communityHeartDto.getUr_state());
//
//
//
////        communityHeartDao.deleteAll();
////        assertEquals(0, communityHeartDao.count());
////
////        //given2:ur_id가 존재하지않거나 다를때
////        communityHeartDto = new CommunityHeartDto("",post_no);
////
////        //assert2
////        communityHeartService.doLike(communityHeartDto);
////        assertEquals(0, communityHeartDao.count());
////        assertEquals(0, communityHeartDao.countLikeStatus(communityHeartDto));
////
////        communityHeartDao.deleteAll();
////        assertEquals(0, communityHeartDao.count());
////
////        //given3:post_no가 존재하지 않을때
////        communityHeartDto = new CommunityHeartDto("user123",null);
////        communityHeartService.doLike(communityHeartDto);
////        //assert3
////        assertEquals(0, communityHeartDao.count());
////        assertEquals(0, communityHeartDao.countLikeStatus(communityHeartDto));
//
//
//
//    }
//
//    public CommunityBoardDto community() throws Exception {
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
//        communityBoardService.write(communityBoardDto);
//        return communityBoardDto;
//    }
//
//
//
//}
