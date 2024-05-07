package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
import team.cheese.dao.CommunityHeart.CommunityHeartDao;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommunityHeartDaoImplTest {

    @Autowired
    CommunityHeartDao communityHeartDao;

    public void deleteLike()throws Exception{

    }

    @Test
    public void insertLike()throws Exception{
//        //init
//        communityHeartDao.deleteAll();
//        assertEquals(0,communityHeartDao.count());
//        //given
//        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
//        communityHeartDto.setPost_no(313);
//        communityHeartDto.setUr_id("user123");
//        communityHeartDao.insertLike(communityHeartDto);

    }
    public void countLike()throws Exception{

    }

    public void countLikeStatus()throws Exception{

    }
}
