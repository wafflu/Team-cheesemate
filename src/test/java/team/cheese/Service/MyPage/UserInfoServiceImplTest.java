package team.cheese.Service.MyPage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.Dao.MyPage.UserInfoDao;
import team.cheese.Domain.MyPage.UserInfoDTO;

import java.util.HashSet;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserInfoServiceImplTest {
    @Autowired
    UserInfoService userService;

    @Autowired
    UserInfoDao userInfoDao;

    @Test
    public void getCount() throws Exception{
        // given : 테이블 초기화
        userInfoDao.deleteAll();
        assertTrue(userService.getCount()==0);
        // do : 소개글 2개 쓰기
        UserInfoDTO u1 = new UserInfoDTO("1","안녕하세요");
        UserInfoDTO u2 = new UserInfoDTO("2","안녕하세요");
        assertTrue(userService.write(u1)==1);
        assertTrue(userService.write(u2)==1);
        // assert : 테이블에 소개글이 2개 있는지 확인
        assertTrue(userService.getCount()==2);

        // do : 소개글 2개 쓰기
         u1 = new UserInfoDTO("3","안녕하세요");
         u2 = new UserInfoDTO("4","안녕하세요");
        assertTrue(userService.write(u1)==1);
        assertTrue(userService.write(u2)==1);
        // assert : 테이블에 소개글이 4개 있는지 확인
        assertTrue(userService.getCount()==4);

        // do : 소개글 100개 쓰기
        for(int i=4; i<104; i++) {
            UserInfoDTO userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userService.write(userInfoDTO)==1);
        }
        // assert : 테이블에 소개글이 104개 있는지 확인
        assertTrue(userService.getCount()==104);


        // do : 소개글 40개 삭제
        for(int i=0; i<40; i++) {
            assertTrue(userService.remove(Integer.toString(i+1))==1);
        }
        // assert : 소개글 64개 있는지 확인
        assertTrue(userService.getCount()==64);
    }

    @Test
    public void read() throws Exception{
        // given : 테이블 초기화 & 소개글 2개 쓰기
        userInfoDao.deleteAll();
        assertTrue(userService.getCount()==0);
        // do : 소개글 2개 쓰기 & 읽기
        UserInfoDTO u1 = new UserInfoDTO("1","안녕하세요");
        UserInfoDTO u2 = new UserInfoDTO("2","안녕하세요");
        assertTrue(userService.write(u1)==1);
        assertTrue(userService.write(u2)==1);
        assertTrue(userService.getCount()==2);
        // assert : 소개글 2개의 방문수가 1씩 증가 했는지 확인 ( 카운트 1 )
        assertTrue(userService.read("1").getView_cnt()==1);
        assertTrue(userService.read("2").getView_cnt()==1);

        // do : 동일한 소개글 2개 읽기
        assertTrue(userService.read("1").getView_cnt()==2);
        assertTrue(userService.read("2").getView_cnt()==2);
        // assert : 소개글 2개의 방문수가 1씩 증가 했는지 확인 ( 카운트 2 )
    }

    @Test
    public void write() throws Exception{
        // given : 테이블 초기화
        userInfoDao.deleteAll();
        assertTrue(userService.getCount()==0);
        // do : 소개글 2개 쓰기
        UserInfoDTO u1 = new UserInfoDTO("1","안녕하세요");
        UserInfoDTO u2 = new UserInfoDTO("2","안녕하세요");
        assertTrue(userService.write(u1)==1);
        assertTrue(userService.write(u2)==1);
        // assert : 테이블에 소개글이 2개인지 확인
                    // 방문자수가 1 증가했는지 확인
        assertTrue(userService.getCount()==2);
        assertTrue(userService.read("1").getView_cnt()==1);
        assertTrue(userService.read("2").getView_cnt()==1);

        // do : 똑같은 사용자 id로 소개글 쓰기
         u1 = new UserInfoDTO("1","안녕하세요");
         u2 = new UserInfoDTO("2","안녕하세요");
         try {
             assertTrue(userService.write(u1)==-1);
             assertTrue(userService.write(u2)==-1);
         }catch (DuplicateKeyException e) {
             assertTrue(1==1);
         }
        // assert : 중복 아이디로 인해 에러 & 변경사항 없으므로 테이블에 소개글은 2개
        assertTrue(userService.getCount()==2);
    }

    @Test
    public void modify() throws Exception{
        // given : 테이블 초기화 & 소개글 1개 쓰기
        userInfoDao.deleteAll();
        assertTrue(userService.getCount()==0);
        UserInfoDTO userInfoDTO =null;
        userInfoDTO = new UserInfoDTO("1","Hi1111111");
        assertTrue(userService.write(userInfoDTO)==1);
        assertTrue(userService.getCount()==1);
        // do : 업데이트할 소개글 생성 & 소개글 업데이트
        userInfoDTO = new UserInfoDTO("1","Hi2222222");
        assertTrue(userService.modify(userInfoDTO)==1);
        assertTrue(userService.getCount()==1);
        // assert : 테이블에 있는 소개글을 읽어서 내용이 바뀌었는지 확인
        userInfoDTO = userService.read("1");
        assertTrue(userInfoDTO.getContents().equals("Hi2222222"));


        // do : 업데이트할 소개글 생성 & 소개글 업데이트
        userInfoDTO = new UserInfoDTO("2","Hi3333333");
        assertTrue(userService.modify(userInfoDTO)==0);
        assertTrue(userService.getCount()==1);
        // assert : 테이블에 있는 소개글을 읽어서 내용이 바뀌었는지 확인
        userInfoDTO = userService.read("1");
        assertTrue(userInfoDTO.getContents().equals("Hi2222222"));

    }

    @Test
    public void remove() throws Exception{
        // given : 테이블 초기화 & 소개글 100개 쓰기
        userInfoDao.deleteAll();
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<100; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userService.write(userInfoDTO)==1);
        }
        assertTrue(userService.getCount()==100);
        // do : 소개글 50개 삭제
        for(int i=0; i<50; i++) {
            assertTrue(userService.remove(Integer.toString(i+1))==1);
        }
        // assert : 남은 소개글 50개인지 확인
        assertTrue(userService.getCount()==50);

        // do : 소개글 20개 삭제
        for(int i=50; i<70; i++) {
            assertTrue(userService.remove(Integer.toString(i+1))==1);
        }
        // assert : 남은 소개글 30개인지 확인
        assertTrue(userService.getCount()==30);

        // do : 소개글 20개 삭제
        for(int i=70; i<90; i++) {
            assertTrue(userService.remove(Integer.toString(i+1))==1);
        }
        // assert : 남은 소개글 10개인지 확인
        assertTrue(userService.getCount()==10);

        // do : 소개글 10개 삭제
        for(int i=90; i<100; i++) {
            assertTrue(userService.remove(Integer.toString(i+1))==1);
        }
        // assert : 남은 소개글 0개인지 확인
        assertTrue(userService.getCount()==0);
    }
}