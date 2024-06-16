package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.domain.CommunityBoard.CommunityBoardDto;
import team.cheese.domain.CommunityHeart.CommunityHeartDto;
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

        //init
        CommunityBoardDto communityBoardDto = community();
        int post_no = communityBoardDto.getno();

        //given
        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
        communityHeartDto.setPost_no(post_no);
        communityHeartDto.setUr_id("user123");
        communityHeartDao.doLike(communityHeartDto);
        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());

        //assert
        assertEquals('y',communityHeartDto.getUr_state());

        communityHeartDao.doLike(communityHeartDto);
        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());

        assertEquals('n',communityHeartDto.getUr_state());

    }




    @Test
    public void testCountLike() throws Exception {

        //init
        CommunityBoardDto communityBoardDto = community();
        int post_no = communityBoardDto.getno();


        //given
        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
        communityHeartDto.setPost_no(post_no);
        communityHeartDto.setUr_id("user123");
        communityHeartDao.doLike(communityHeartDto);

         communityHeartDto = new CommunityHeartDto();
        communityHeartDto.setPost_no(post_no);
        communityHeartDto.setUr_id("david234");
        communityHeartDao.doLike(communityHeartDto);

        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());
        assertEquals(2, communityHeartDao.countLike(post_no));



    }


    @Test
    public void selectUser()throws Exception{
        CommunityBoardDto communityBoardDto = community();
        int post_no = communityBoardDto.getno();
        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
        communityHeartDto.setPost_no(post_no);
        communityHeartDto.setUr_id("user123");
        communityHeartDao.doLike(communityHeartDto);

        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());
        String result = communityHeartDao.findByUserId("user123", String.valueOf(post_no));

        assertEquals(1,communityHeartDao.countLike(post_no));


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
