package team.cheese.Dao.MyPage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.Domain.MyPage.UserInfoDTO;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserInfoDaoImplTest {
    @Autowired
    UserInfoDao userInfoDao;
    @Test
    public void count() throws Exception{
        // given : 테이블 초기화&소개글 10개 생성
        userInfoDao.deleteAll();
        assertTrue(userInfoDao.count()==0);
        // do : 테이블에 10개 insert

        UserInfoDTO userInfoDTO = new UserInfoDTO("alswjd","안녕하세요오오오");
        assertTrue(userInfoDao.insert(userInfoDTO)==1);
        UserInfoDTO userInfoDTO2 = new UserInfoDTO("david234","안녕하세요오오오");
        assertTrue(userInfoDao.insert(userInfoDTO2)==1);
        UserInfoDTO userInfoDTO3 = new UserInfoDTO("dbrud","안녕하세요오오오");
        assertTrue(userInfoDao.insert(userInfoDTO3)==1);
        UserInfoDTO userInfoDTO4 = new UserInfoDTO("rudtlr","안녕하세요오오오");
        assertTrue(userInfoDao.insert(userInfoDTO4)==1);
        UserInfoDTO userInfoDTO5 = new UserInfoDTO("user123","안녕하세요오오오");
        assertTrue(userInfoDao.insert(userInfoDTO5)==1);
        UserInfoDTO userInfoDTO6 = new UserInfoDTO("whdgjs","안녕하세요오오오");
        assertTrue(userInfoDao.insert(userInfoDTO6)==1);
        UserInfoDTO userInfoDTO7 = new UserInfoDTO("wjdgk","안녕하세요오오오");
        assertTrue(userInfoDao.insert(userInfoDTO7)==1);
        UserInfoDTO userInfoDTO8 = new UserInfoDTO("wjdgns","안녕하세요오오오");
        assertTrue(userInfoDao.insert(userInfoDTO8)==1);
        UserInfoDTO userInfoDTO9 = new UserInfoDTO("wlsdn","안녕하세요오오오");
        assertTrue(userInfoDao.insert(userInfoDTO9)==1);
        UserInfoDTO userInfoDTO10 = new UserInfoDTO("wogjs","안녕하세요오오오");
        assertTrue(userInfoDao.insert(userInfoDTO10)==1);
        // assert : 테이블에 데이터가 10개가 맞는지 확인
        assertTrue(userInfoDao.count()==10);
    }

    @Test
    public void select() {
        // given : 테이블에 10개의 행이 있는지 확인
        // do :
        // assert :
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void incrementViewCnt() {
    }

    @Test
    public void incrementCompleteCnt() {
    }

    @Test
    public void updateRvCmtCnt() {
    }

    @Test
    public void incrementLikeCnt() {
    }

    @Test
    public void incrementHateCnt() {
    }

    @Test
    public void incrementRptCnt() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteAll() {
    }
}