package team.cheese.service.CommunityBoard;

import org.apache.ibatis.jdbc.Null;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
import team.cheese.dao.CommunityBoard.CommunityBoardDao;
import team.cheese.dao.CommunityHeart.CommunityHeartDao;
import team.cheese.service.CommunityHeart.CommunityHeartService;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommunityHeartServiceImplTest {


    @Autowired
    CommunityBoardService communityBoardService;

    @Autowired
    CommunityHeartDao communityHeartDao;

    @Autowired
    CommunityHeartService communityHeartService;




    @Test
    public void doLike1() throws Exception {
        //제대로 하트가 들어가는 경우
        //init

        CommunityBoardDto communityBoardDto = community();
        int post_no = communityBoardDto.getno();

        //given1:처음 하트 클릭
        CommunityHeartDto communityHeartDto = new CommunityHeartDto("user123", post_no, 'y');
        communityHeartService.doLike(communityHeartDto);

        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());

        //assert1:처음 하트 클릭 상태 y검증
        assertEquals('y',communityHeartDto.getUr_state());


        //given2:두번째 하트 클릭
        communityHeartService.doLike(communityHeartDto);
        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());

        //assert2:두번째 하트 클릭 상태 n검증
        assertEquals('n',communityHeartDto.getUr_state());
    }


    @Test(expected = NullPointerException.class)
    public void doLike2() throws Exception {
        CommunityBoardDto communityBoardDto = community();
        int post_no = communityBoardDto.getno();

        //given1:post_no가 null일때
        CommunityHeartDto communityHeartDto = new CommunityHeartDto("user123", null);
        communityHeartService.doLike(communityHeartDto);

        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());

        //assert1:예외 검증
        assertEquals(0,communityHeartDto);
    }


    @Test(expected = IllegalArgumentException.class)
    public void doLike3() throws Exception {
        //given: post_no(communityBoardDto)가 접근할 수 없는 상태일때
        CommunityBoardDto communityBoardDto = community();
        int post_no = communityBoardDto.getno();
        communityBoardDto.setur_state('n');
        communityBoardService.userStateChange(communityBoardDto);


        CommunityHeartDto communityHeartDto = new CommunityHeartDto("user123", post_no);
        communityHeartService.doLike(communityHeartDto);

        communityHeartDto = communityHeartDao.select(communityHeartDto.getLike_no());

        assertEquals(0,communityHeartDto);
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

        communityBoardService.write(communityBoardDto);
        return communityBoardDto;
    }



}
