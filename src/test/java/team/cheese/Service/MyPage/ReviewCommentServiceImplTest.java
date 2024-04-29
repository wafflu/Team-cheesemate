package team.cheese.Service.MyPage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.Dao.MyPage.ReviewCommentDao;
import team.cheese.Dao.MyPage.UserInfoDao;
import team.cheese.Domain.MyPage.ReviewCommentDTO;
import team.cheese.Domain.MyPage.UserInfoDTO;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReviewCommentServiceImplTest {
    @Autowired
    ReviewCommentService rvService;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    ReviewCommentDao rvDao;


    @Test
    public void getCount() throws Exception{
        // given : 테이블 초기화 & 행이 없는 것을 확인
        rvDao.deleteAll("1");
        assertTrue(rvService.getCount("1")==0);
        // do : 후기글 10개 insert
        ReviewCommentDTO dto = null;
        for(int i=0; i <10; i++) {
            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
            assertTrue(rvService.write(dto)==1);
        }
        // assert : 행이 10개인 것을 확인
        assertTrue(rvService.getCount("1")==10);

        // do : 후기글 2개 delete
        List<ReviewCommentDTO> list = rvService.getList("1");
        String b1 = list.get(0).getBuy_id();
        String b2 = list.get(1).getBuy_id();
        int r1 = list.get(0).getNo();
        int r2 = list.get(1).getNo();
        assertTrue(rvService.remove("1",b1,r1)==1);
        assertTrue(rvService.remove("1",b2,r2)==1);
        // assert : 행이 8개인 것을 확인
        assertTrue(rvService.getCount("1")==8);


    }

    @Test
    public void read() throws Exception{
        // given : 테이블 초기화 & 후기글 10개 insert
        rvDao.deleteAll("1");
        assertTrue(rvService.getCount("1")==0);
        ReviewCommentDTO dto = null;
        for(int i=0; i <10; i++) {
            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
            assertTrue(rvService.write(dto)==1);
        }
        assertTrue(rvService.getCount("1")==10);
        // do : & 두개의 후기글에 대한 후기글번호를 얻기 & 후기글 첫번째와 마지막을 select
        List<ReviewCommentDTO> list = rvService.getList("1");
        int b1 = list.get(0).getNo();
        int b2 = list.get(9).getNo();
        ReviewCommentDTO r1 = rvService.read(b1);
        ReviewCommentDTO r2 = rvService.read(b2);
        // assert : 얻은 후기글번호와 select한 후기글의 번호와 일치하는지 확인
        assertTrue(b1==r1.getNo());
        assertTrue(b2==r2.getNo());

    }

    @Test
    public void getList() throws Exception{
        // given : 테이블 초기화 & 후기글 100개 insert
        rvDao.deleteAll("1");
        assertTrue(rvService.getCount("1")==0);
        ReviewCommentDTO dto = null;
        for(int i=0; i <100; i++) {
            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
            assertTrue(rvService.write(dto)==1);
        }
        assertTrue(rvService.getCount("1")==100);
        // do : 전체 행을 list에 담기
        List<ReviewCommentDTO> list = rvService.getList("1");
        // assert : list size가 100인지 확인
        assertTrue(list.size()==100);

        // do : 후기글을 24개 delete & 다시 테이블 전체 행을 list에 담기
        String b1 = null;
        int n1 = 0;
        for(int i=0; i<24; i++) {
            b1 = list.get(i).getBuy_id();
            n1 = list.get(i).getNo();
            System.out.println(b1);
            assertTrue(rvService.remove("1",b1,n1)==1);
        }
        assertTrue(rvService.getCount("1")==76);
        list = rvService.getList("1");
        // assert : list size가 76인지 확인
        assertTrue(list.size()==76);

    }

    @Test
    public void writeData() throws Exception {
        for(int i=0; i<20; i++) {
            ReviewCommentDTO r1 = new ReviewCommentDTO("user123",""+i,"맛있어요");
            rvService.write(r1);
        }
    }
    @Test
    public void write() throws Exception{
        // given : 테이블 초기화 & 소개글 1개 작성
        userInfoDao.deleteAll();
        assertTrue(userInfoDao.count()==0);
        UserInfoDTO u1 = new UserInfoDTO("1","안녕하세요");
        assertTrue(userInfoDao.insert(u1)==1);
        assertTrue(userInfoDao.count()==1);
        // do : 소개글에 대한 후기글 1개 작성
        rvDao.deleteAll("1");
        assertTrue(rvService.getCount("1")==0);
        ReviewCommentDTO r1 = new ReviewCommentDTO("1","2","맛있어요");
        assertTrue(rvService.write(r1)==1);
        assertTrue(rvService.getCount("1")==1);
        // assert : 소개글에 후기글수가 1 증가했는지 확인 & 후기글의 소개글 사용자아이디가 일치하는지 확인
        assertTrue(userInfoDao.select("1").getRv_cmt_cnt()==1);
        List<ReviewCommentDTO> list = rvService.getList("1");
        r1 = list.get(0);
        assertTrue(r1.getSal_id().equals("1"));

        // do : 소개글에 대한 후기글 2개 작성
        assertTrue(rvService.getCount("1")==1);
        r1 = new ReviewCommentDTO("1","3","맛있어요");
        assertTrue(rvService.write(r1)==1);
        assertTrue(rvService.getCount("1")==2);
        r1 = new ReviewCommentDTO("1","4","맛있어요");
        assertTrue(rvService.write(r1)==1);
        assertTrue(rvService.getCount("1")==3);
        // assert : 소개글에 후기글수가 3인지 확인 & 후기글의 소개글 사용자아이디가 일치하는지 확인
        assertTrue(userInfoDao.select("1").getRv_cmt_cnt()==3);
        list = rvService.getList("1");
        r1 = list.get(0);
        assertTrue(r1.getSal_id().equals("1"));
        r1 = list.get(1);
        assertTrue(r1.getSal_id().equals("1"));
        r1 = list.get(2);
        assertTrue(r1.getSal_id().equals("1"));

        // do : 소개글에 대한 후기글 1개 작성
        assertTrue(rvService.getCount("1")==3);
        r1 = new ReviewCommentDTO("1","5","맛있어요");
        assertTrue(rvService.write(r1)==1); // 예외 발생
        assertTrue(rvService.getCount("1")==4); // 변경 X
        // assert : 예외가 발생했으므로
        // 소개글에 후기글수가 3인지 확인 & 후기글의 소개글 사용자아이디가 일치하는지 확인
        assertTrue(userInfoDao.select("1").getRv_cmt_cnt()==4);
        list = rvService.getList("1");
        r1 = list.get(0);
        assertTrue(r1.getSal_id().equals("1"));
        r1 = list.get(1);
        assertTrue(r1.getSal_id().equals("1"));
        r1 = list.get(2);
        assertTrue(r1.getSal_id().equals("1"));

    }

    @Test
    public void remove() throws Exception{
        // given : 소개글 테이블 초기화 & 소개글 작성 & 소개글에 대한 후기글 작성
        userInfoDao.deleteAll();
        assertTrue(userInfoDao.count()==0);
        UserInfoDTO u1 = new UserInfoDTO("1","안녕하세요");
        assertTrue(userInfoDao.insert(u1)==1);
        assertTrue(userInfoDao.count()==1);
        // do : 소개글에 대한 후기글 1개 작성
        rvDao.deleteAll("1");
        assertTrue(rvService.getCount("1")==0);
        ReviewCommentDTO r1 = new ReviewCommentDTO("1","2","맛있어요");
        assertTrue(rvService.write(r1)==1);
        assertTrue(rvService.getCount("1")==1);
        // assert : 소개글에 후기글수가 1 증가했는지 확인 & 후기글의 소개글 사용자아이디가 일치하는지 확인
        assertTrue(userInfoDao.select("1").getRv_cmt_cnt()==1);
        List<ReviewCommentDTO> list = rvService.getList("1");
        r1 = list.get(0);
        assertTrue(r1.getSal_id().equals("1"));

        // do : 후기글 삭제
        Integer no = r1.getNo();
        assertTrue(rvService.remove(r1.getSal_id(),r1.getBuy_id(),no)==1);
        // assert : 후기글 테이블이 비었는지 확인 & 소개글에 후기글갯수가 줄었는지 확인
        assertTrue(rvService.getCount("1")==0);
        assertTrue(userInfoDao.select("1").getRv_cmt_cnt()==0);



        // do : 소개글에 대한 후기글 2개 작성
        assertTrue(rvService.getCount("1")==0);
        r1 = new ReviewCommentDTO("1","3","맛있어요");
        assertTrue(rvService.write(r1)==1);
        assertTrue(rvService.getCount("1")==1);
        r1 = new ReviewCommentDTO("1","4","맛있어요");
        assertTrue(rvService.write(r1)==1);
        assertTrue(rvService.getCount("1")==2);
        // assert : 소개글에 후기글수가 2인지 확인 & 후기글의 소개글 사용자아이디가 일치하는지 확인
        assertTrue(userInfoDao.select("1").getRv_cmt_cnt()==2);
        list = rvService.getList("1");
        r1 = list.get(0);
        no = r1.getNo();
        System.out.println(no);
        assertTrue(r1.getSal_id().equals("1"));
        r1 = list.get(1);
        assertTrue(r1.getSal_id().equals("1"));

        //예외발생
        // do : 후기글 삭제
        no = r1.getNo();
        System.out.println(no);
        assertTrue(rvService.remove(r1.getSal_id(),r1.getBuy_id(),no)==1);
        // assert : 후기글 테이블이 비었는지 확인 & 소개글에 후기글갯수가 줄었는지 확인
        assertTrue(rvService.getCount("1")==1);
        assertTrue(userInfoDao.select("1").getRv_cmt_cnt()==1);
    }

    @Test
    public void modify() throws Exception{
        // given : 테이블 초기화 & 후기글 2개 insert & 업데이트할 후기글 객체 2개 생성
        rvDao.deleteAll("1");
        assertTrue(rvService.getCount("1")==0);
        ReviewCommentDTO dto = null;
        dto = new ReviewCommentDTO("1",Integer.toString(1),"맛있어요");
        assertTrue(rvService.write(dto)==1);
        assertTrue(rvService.getCount("1")==1);
        dto = new ReviewCommentDTO("1",Integer.toString(2),"맛있어요");
        assertTrue(rvService.write(dto)==1);
        assertTrue(rvService.getCount("1")==2);
        ReviewCommentDTO dto1 = new ReviewCommentDTO("1","1","맛있어요22");
        ReviewCommentDTO dto2 = new ReviewCommentDTO("1","2","맛있어요22");
        // do : 후기글 2개에 대한 후기글 번호를 얻어오기 & 업데이트할 후기글에 초기화시켜주기
        List<ReviewCommentDTO> list = rvService.getList("1");
        int n1 = list.get(0).getNo();
        int n2 = list.get(1).getNo();
        dto1.setNo(n2);
        dto2.setNo(n1);
        System.out.println(dto1);
        System.out.println(dto2);
        //      & 후기글 2개 업데이트
        assertTrue(rvService.modify(dto1)==1);
        assertTrue(rvService.modify(dto2)==1);
        assertTrue(rvService.getCount("1")==2);
        // assert : 업데이트된 후기글 select & select한 후기글 번호가 업데이트할 후기글의 후기글 번호와 내용이
        //          일치하는지 확인
        dto = rvService.read(n1);
        System.out.println(dto);
        System.out.println(dto2);
        assertTrue(dto.getNo().equals(dto2.getNo()) &&dto.getContents().equals(dto2.getContents()));
        dto = rvService.read(n2);
        System.out.println(dto);
        assertTrue(dto.getNo().equals(dto1.getNo())&&dto.getContents().equals(dto1.getContents()));

    }

    @Test
    public void clickedLike() throws Exception{
        // given : 테이블 초기화 & 후기글 10개 insert
        rvDao.deleteAll("1");
        assertTrue(rvService.getCount("1")==0);
        ReviewCommentDTO dto = null;
        for(int i=0; i <10; i++) {
            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
            assertTrue(rvService.write(dto)==1);
        }
        assertTrue(rvService.getCount("1")==10);
        // do : 10개의 후기글 ViewCnt를 1씩 증가
        List<ReviewCommentDTO> list = rvService.getList("1");
        int no = 0;
        for(int i=0; i<10; i++) {
            no = list.get(i).getNo();
            assertTrue(rvService.ClickedLike(no)==1);
        }
        // assert : 10개의 후기글 ViewCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            no = list.get(i).getNo();
            assertTrue(rvService.read(no).getLike_cnt()==1);
        }

        // do : 10개의 후기글 ViewCnt를 1씩 추가적으로 증가
        for(int i=0; i<10; i++) {
            no = list.get(i).getNo();
            assertTrue(rvService.ClickedLike(no)==1);
        }
        // assert : 10개의 후기글 ViewCnt가 1씩 증가했는지 확인
        for(int i=0; i<10; i++) {
            no = list.get(i).getNo();
            assertTrue(rvService.read(no).getLike_cnt()==2);
        }

    }
}