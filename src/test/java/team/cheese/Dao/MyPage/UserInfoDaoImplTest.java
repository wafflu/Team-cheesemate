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
    public void select() throws Exception{
        // given : 테이블에 10개의 행이 있는지 확인
        assertTrue(userInfoDao.count()==10);
        // do : id가 rudtlr,alswjd을 select
        UserInfoDTO u1 = userInfoDao.select("rudtlr");
        UserInfoDTO u2 = userInfoDao.select("alswjd");
        // assert : select한 두개의 객체의 id가 rudtlr,alswjd이 맞는지 확인
        assertTrue(u1.getUr_id().equals("rudtlr"));
        assertTrue(u2.getUr_id().equals("alswjd"));

        // given : 테이블 초기화 & 테이블에 소개글 100개 넣기
        assertTrue(userInfoDao.deleteAll()==0);
        assertTrue(userInfoDao.count()==0);
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<100; i++) {
           userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
           assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==100);
        // do : id가 1하고 10인 소개글을 select
        UserInfoDTO u3 = userInfoDao.select("1");
        UserInfoDTO u4 = userInfoDao.select("10");
        // assert : id가 1하고 10이 맞는지 확인
        assertTrue(u3.getUr_id().equals("1"));
        assertTrue(u4.getUr_id().equals("10"));
    }

    @Test
    public void insert() throws Exception{
        // given : 테이블 초기화
        assertTrue(userInfoDao.deleteAll()==0);
        assertTrue(userInfoDao.count()==0);

        // do : 테이블에 소개글 200개 넣기
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<200; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==200);
        // assert : 테이블에 200개의 행이 있는지 확인 (성공)
        assertTrue(userInfoDao.count()==200);

        // given : 테이블 200개 행이 있는지 확인
        assertTrue(userInfoDao.count()==200);
        // do : 테이블에 소개글 10개 넣기 &  중복 id값을 넣어서 추가 ( 실패 )
        for(int i=0; i<10; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        // assert : 테이블에 200개의 행이 있는지 확인
        assertTrue(userInfoDao.count()==200);
    }

    @Test
    public void update() throws Exception{
        // given : 테이블 초기화 & 소개글 10개 insert
        assertTrue(userInfoDao.deleteAll()==10);
        assertTrue(userInfoDao.count()==0);
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<10; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==10);
        // do : id가 1과 10인 소개글을 update & contents(내용)을 "Hi222"로 업데이트할것
        UserInfoDTO u1 = new UserInfoDTO("1","Hi222");
        UserInfoDTO u2 = new UserInfoDTO("10","Hi222");
        assertTrue(userInfoDao.update(u1)==1);
        assertTrue(userInfoDao.update(u2)==1);
        // assert : id가 1과 10인 소개글의 내용이 "Hi222"가 맞는지 확인
        assertTrue(userInfoDao.select("1").getContents().equals("Hi222"));
        assertTrue(userInfoDao.select("10").getContents().equals("Hi222"));
        System.out.println(userInfoDao.select("1"));
        System.out.println(userInfoDao.select("10"));
        System.out.println(userInfoDao.select("2"));
    }

    @Test
    public void incrementViewCnt() throws Exception {
        // given : 테이블초기화 & 소개글 10개 생성 후 insert
        userInfoDao.deleteAll();
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<10; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==10);
        // do : 10개의 소개글 ViewCnt를 1씩 증가
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.incrementViewCnt(Integer.toString(i+1))==1);
        }
        // assert : 10개의 소개글 ViewCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getView_cnt()==1);
        }

        // do : 10개의 소개글 ViewCnt를 1씩 추가적으로 증가
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.incrementViewCnt(Integer.toString(i+1))==1);
        }
        // assert : 10개의 소개글 ViewCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getView_cnt()==2);
        }
    }

    @Test
    public void incrementCompleteCnt() throws Exception{
        // given : 테이블초기화 & 소개글 10개 생성 후 insert
        userInfoDao.deleteAll();
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<10; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==10);
        // do : 10개의 소개글 CompleteCnt를 1씩 증가
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.incrementCompleteCnt(Integer.toString(i+1))==1);
        }
        // assert : 10개의 소개글 CompleteCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getComplete_cnt()==1);
        }

        // do : 10개의 소개글 CompleteCnt를 1씩 추가적으로 증가
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.incrementCompleteCnt(Integer.toString(i+1))==1);
        }
        // assert : 10개의 소개글 CompleteCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getComplete_cnt()==2);
        }
    }

    @Test
    public void updateRvCmtCnt() throws Exception{
        // given : 테이블초기화&소개글 10개 생성 후 insert
        userInfoDao.deleteAll();
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<10; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==10);
        // do : 10개의 소개글에 cnt를 1씩 증가 (댓글증가)
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.updateRvCmtCnt(Integer.toString(i+1),1)==1);
        }
        // assert : 10개의 소개글에 rv_cmt_cnt가 1씩 증가했는지 확인 (1이여야함)
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getRv_cmt_cnt()==1);
        }

        // given : 테이블에 소개글 10개 있는지 확인
        assertTrue(userInfoDao.count()==10);
        // do : 10개의 소개글에 cnt를 1씩 감소 (댓글감소)
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.updateRvCmtCnt(Integer.toString(i+1),-1)==1);
        }
        // assert : 10개의 소개글에 rv_cmt_cnt가 1씩 감소했는지 확인 (0이여야함)
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getRv_cmt_cnt()==0);
        }
    }

    @Test
    public void incrementLikeCnt() throws Exception{
        // given : 테이블초기화 & 소개글 10개 생성 후 insert
        userInfoDao.deleteAll();
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<10; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==10);
        // do : 10개의 소개글 ViewCnt를 1씩 증가
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.incrementLikeCnt(Integer.toString(i+1))==1);
        }
        // assert : 10개의 소개글 ViewCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getLike_cnt()==1);
        }

        // do : 10개의 소개글 ViewCnt를 1씩 추가적으로 증가
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.incrementLikeCnt(Integer.toString(i+1))==1);
        }
        // assert : 10개의 소개글 ViewCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getLike_cnt()==2);
        }
    }

    @Test
    public void incrementHateCnt() throws  Exception{
        // given : 테이블초기화 & 소개글 10개 생성 후 insert
        userInfoDao.deleteAll();
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<10; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==10);
        // do : 10개의 소개글 ViewCnt를 1씩 증가
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.incrementHateCnt(Integer.toString(i+1))==1);
        }
        // assert : 10개의 소개글 ViewCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getHate_cnt()==1);
        }

        // do : 10개의 소개글 ViewCnt를 1씩 추가적으로 증가
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.incrementHateCnt(Integer.toString(i+1))==1);
        }
        // assert : 10개의 소개글 ViewCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getHate_cnt()==2);
        }
    }

    @Test
    public void incrementRptCnt() throws Exception{
        // given : 테이블초기화 & 소개글 10개 생성 후 insert
        userInfoDao.deleteAll();
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<10; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==10);
        // do : 10개의 소개글 ViewCnt를 1씩 증가
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.incrementRptCnt(Integer.toString(i+1))==1);
        }
        // assert : 10개의 소개글 ViewCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getRpt_cnt()==1);
        }

        // do : 10개의 소개글 ViewCnt를 1씩 추가적으로 증가
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.incrementRptCnt(Integer.toString(i+1))==1);
        }
        // assert : 10개의 소개글 ViewCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            assertTrue(userInfoDao.select(Integer.toString(i+1)).getRpt_cnt()==2);
        }
    }

    @Test
    public void delete() throws Exception{
        // given : 테이블 초기화 & 소개글 10개 insert
        assertTrue(userInfoDao.deleteAll()==0);
        assertTrue(userInfoDao.count()==0);
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<10; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==10);
        // do : id가 1,10인 소개글을 delete
        assertTrue(userInfoDao.delete("1")==1);
        assertTrue(userInfoDao.delete("10")==1);
        // assert : 테이블에 id가 1,10인 소개글이 없는것을 확인
        assertTrue(userInfoDao.select("1")==null);
        assertTrue(userInfoDao.select("10")==null);
    }

    @Test
    public void deleteAll() throws Exception{
        // given : 테이블 초기화 & 소개글 10개 insert
        assertTrue(userInfoDao.deleteAll()==8);
        assertTrue(userInfoDao.count()==0);
        UserInfoDTO userInfoDTO =null;
        for(int i=0; i<10; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==10);
        // do : 소개글 10개 추가 & 테이블 전체 삭제
        for(int i=10; i<20; i++) {
            userInfoDTO = new UserInfoDTO(Integer.toString(i+1),"Hi");
            assertTrue(userInfoDao.insert(userInfoDTO)==1);
        }
        assertTrue(userInfoDao.count()==20);
        userInfoDao.deleteAll();
        // assert : 테이블에 아무것도 없는지 확인
        assertTrue(userInfoDao.count()==0);
    }
}