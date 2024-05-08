//package team.cheese.Dao.MyPage;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import team.cheese.Domain.MyPage.ReviewCommentDTO;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//public class ReviewCommentDaoImplTest {
//    @Autowired
//    ReviewCommentDao rvdao;
//
//    @Test
//    public void count() throws Exception{
//        // given : 테이블 초기화 & 행이 없는 것을 확인
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        // do : 후기글 10개 insert
//        ReviewCommentDTO dto = null;
//        for(int i=0; i <10; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        // assert : 행이 10개인 것을 확인
//        assertTrue(rvdao.count("1")==10);
//
//        // do : 후기글 2개 delete
//        List<ReviewCommentDTO> list = rvdao.selectAll("1");
//        String b1 = list.get(0).getBuy_id();
//        String b2 = list.get(1).getBuy_id();
//        int r1 = list.get(0).getNo();
//        int r2 = list.get(1).getNo();
//        assertTrue(rvdao.delete(b1,r1)==1);
//        assertTrue(rvdao.delete(b2,r2)==1);
//        // assert : 행이 8개인 것을 확인
//        assertTrue(rvdao.count("1")==8);
//    }
//
//    @Test
//    public void select() throws Exception{
//        // given : 테이블 초기화 & 후기글 10개 insert
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        ReviewCommentDTO dto = null;
//        for(int i=0; i <10; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        assertTrue(rvdao.count("1")==10);
//        // do : & 두개의 후기글에 대한 후기글번호를 얻기 & 후기글 첫번째와 마지막을 select
//        List<ReviewCommentDTO> list = rvdao.selectAll("1");
//        int b1 = list.get(0).getNo();
//        int b2 = list.get(9).getNo();
//        ReviewCommentDTO r1 = rvdao.select(b1);
//        ReviewCommentDTO r2 = rvdao.select(b2);
//        // assert : 얻은 후기글번호와 select한 후기글의 번호와 일치하는지 확인
//        assertTrue(b1==r1.getNo());
//        assertTrue(b2==r2.getNo());
//    }
//
//    @Test
//    public void selectAll() throws Exception{
//        // given : 테이블 초기화 & 후기글 100개 insert
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        ReviewCommentDTO dto = null;
//        for(int i=0; i <100; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        assertTrue(rvdao.count("1")==100);
//        // do : 전체 행을 list에 담기
//        List<ReviewCommentDTO> list = rvdao.selectAll("1");
//        // assert : list size가 100인지 확인
//        assertTrue(list.size()==100);
//
//        // do : 후기글을 24개 delete & 다시 테이블 전체 행을 list에 담기
//        String b1 = null;
//        int n1 = 0;
//        for(int i=0; i<24; i++) {
//            b1 = list.get(i).getBuy_id();
//            n1 = list.get(i).getNo();
//            System.out.println(b1);
//            assertTrue(rvdao.delete(b1,n1)==1);
//        }
//        assertTrue(rvdao.count("1")==76);
//        list = rvdao.selectAll("1");
//        // assert : list size가 76인지 확인
//        assertTrue(list.size()==76);
//    }
//
//    @Test
//    public void insert() throws Exception {
//        // given : 테이블 초기화 & 0개인지 확인
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        // do : & 10개 후기글 insert
//        ReviewCommentDTO dto = null;
//        for(int i=0; i <10; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        // assert : & 10개인지 확인
//        assertTrue(rvdao.count("1")==10);
//
//        // do : 15개 후기글 insert
//        for(int i=10; i <25; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        // assert : 25개인지 확인
//        assertTrue(rvdao.count("1")==25);
//
//        // do : 25개 후기글 insert
//        for(int i=25; i <50; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        // assert : 50개인지 확인
//        assertTrue(rvdao.count("1")==50);
//    }
//
//    @Test
//    public void update() throws Exception{
//        // given : 테이블 초기화 & 후기글 2개 insert & 업데이트할 후기글 객체 2개 생성
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        ReviewCommentDTO dto = null;
//        dto = new ReviewCommentDTO("1",Integer.toString(1),"맛있어요");
//        assertTrue(rvdao.insert(dto)==1);
//        assertTrue(rvdao.count("1")==1);
//        dto = new ReviewCommentDTO("1",Integer.toString(2),"맛있어요");
//        assertTrue(rvdao.insert(dto)==1);
//        assertTrue(rvdao.count("1")==2);
//        ReviewCommentDTO dto1 = new ReviewCommentDTO("1","1","맛있어요22");
//        ReviewCommentDTO dto2 = new ReviewCommentDTO("1","2","맛있어요22");
//        // do : 후기글 2개에 대한 후기글 번호를 얻어오기 & 업데이트할 후기글에 초기화시켜주기
//        List<ReviewCommentDTO> list = rvdao.selectAll("1");
//        int n1 = list.get(0).getNo();
//        int n2 = list.get(1).getNo();
//        dto1.setNo(n2);
//        dto2.setNo(n1);
//        System.out.println(dto1);
//        System.out.println(dto2);
//        //      & 후기글 2개 업데이트
//        assertTrue(rvdao.update(dto1)==1);
//        assertTrue(rvdao.update(dto2)==1);
//        assertTrue(rvdao.count("1")==2);
//        // assert : 업데이트된 후기글 select & select한 후기글 번호가 업데이트할 후기글의 후기글 번호와 내용이
//        //          일치하는지 확인
//        dto = rvdao.select(n1);
//        System.out.println(dto);
//        System.out.println(dto2);
//        assertTrue(dto.getNo().equals(dto2.getNo()) &&dto.getContents().equals(dto2.getContents()));
//        dto = rvdao.select(n2);
//        System.out.println(dto);
//        assertTrue(dto.getNo().equals(dto1.getNo())&&dto.getContents().equals(dto1.getContents()));
//
//    }
//
//    @Test
//    public void incrementLikeCnt() throws Exception {
//        // given : 테이블 초기화 & 후기글 10개 insert
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        ReviewCommentDTO dto = null;
//        for(int i=0; i <10; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        assertTrue(rvdao.count("1")==10);
//        // do : 10개의 후기글 ViewCnt를 1씩 증가
//        List<ReviewCommentDTO> list = rvdao.selectAll("1");
//        int no = 0;
//        for(int i=0; i<10; i++) {
//            no = list.get(i).getNo();
//            assertTrue(rvdao.incrementLikeCnt(no)==1);
//        }
//        // assert : 10개의 후기글 ViewCnt가 1씩 증가했는지 확인
//        for(int i=0; i<10; i++) {
//            no = list.get(i).getNo();
//            assertTrue(rvdao.select(no).getLike_cnt()==1);
//        }
//
//        // do : 10개의 후기글 ViewCnt를 1씩 추가적으로 증가
//        for(int i=0; i<10; i++) {
//            no = list.get(i).getNo();
//            assertTrue(rvdao.incrementLikeCnt(no)==1);
//        }
//        // assert : 10개의 후기글 ViewCnt가 1씩 증가했는지 확인
//        for(int i=0; i<10; i++) {
//            no = list.get(i).getNo();
//            assertTrue(rvdao.select(no).getLike_cnt()==2);
//        }
//    }
//
//    @Test
//    public void userStateChange() throws Exception{
//        // given : 테이블 초기화 & 후기글 10개 insert
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        ReviewCommentDTO dto = null;
//        for(int i=0; i <10; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        assertTrue(rvdao.count("1")==10);
//        // do : 10개의 후기글 상태를 N으로 바꾸기
//        List<ReviewCommentDTO> list = rvdao.selectAll("1");
//        ReviewCommentDTO rc = null;
//        int no = 0;
//        for(int i=0; i<10; i++) {
//            rc = list.get(i);
//            assertTrue(rvdao.UserStateChange(rc)==1);
//        }
//        // assert : 상태가 N인지 확인
//        for(int i=0; i<10; i++) {
//            no = list.get(i).getNo();
//            assertTrue(rvdao.select(no).getUr_state()=='N');
//        }
//        // do : 10개의 후기글 상태를 Y로 바꾸기
//        for(int i=0; i<10; i++) {
//            rc = rvdao.selectAll("1").get(i);
//            System.out.println(rc);
//            assertTrue(rvdao.UserStateChange(rc)==1);
//        }
//        // assert : 상태가 Y인지 확인
//        for(int i=0; i<10; i++) {
//            no = list.get(i).getNo();
//            assertTrue(rvdao.select(no).getUr_state()=='Y');
//        }
//    }
//
//    @Test
//    public void adminStateChange() throws Exception {
//        // given : 테이블 초기화 & 후기글 10개 insert
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        ReviewCommentDTO dto = null;
//        for(int i=0; i <10; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        assertTrue(rvdao.count("1")==10);
//        // do : 10개의 후기글 상태를 N으로 바꾸기
//        List<ReviewCommentDTO> list = rvdao.selectAll("1");
//        ReviewCommentDTO rc = null;
//        int no = 0;
//        for(int i=0; i<10; i++) {
//            rc = list.get(i);
//            assertTrue(rvdao.AdminStateChange(rc)==1);
//        }
//        // assert : 상태가 N인지 확인
//        for(int i=0; i<10; i++) {
//            no = list.get(i).getNo();
//            assertTrue(rvdao.select(no).getAd_state()=='Y');
//        }
//        // do : 10개의 후기글 상태를 Y로 바꾸기
//        for(int i=0; i<10; i++) {
//            rc = rvdao.selectAll("1").get(i);
//            System.out.println(rc);
//            assertTrue(rvdao.AdminStateChange(rc)==1);
//        }
//        // assert : 상태가 Y인지 확인
//        for(int i=0; i<10; i++) {
//            no = list.get(i).getNo();
//            assertTrue(rvdao.select(no).getAd_state()=='N');
//        }
//    }
//
//    @Test
//    public void delete() throws Exception{
//        // given : 테이블 초기화 & 후기글 5개 insert
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        ReviewCommentDTO dto = null;
//        for(int i=0; i <5; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        assertTrue(rvdao.count("1")==5);
//
//        // do : 후기글 2개 delete
//        List<ReviewCommentDTO> list = rvdao.selectAll("1");
//        String b1 = list.get(0).getBuy_id();
//        String b2 = list.get(1).getBuy_id();
//        int n1 = list.get(0).getNo();
//        int n2 = list.get(1).getNo();
//        assertTrue(rvdao.delete(b1,n1)==1);
//        assertTrue(rvdao.delete(b2,n2)==1);
//        // assert : 테이블에 3개의 행만 있는지 확인
//        assertTrue(rvdao.count("1")==3);
//
//        // do : 후기글 2개 delete
//        b1 = list.get(2).getBuy_id();
//        b2 = list.get(3).getBuy_id();
//        n1 = list.get(2).getNo();
//        n2 = list.get(3).getNo();
//        assertTrue(rvdao.delete(b1,n1)==1);
//        assertTrue(rvdao.delete(b2,n2)==1);
//        // assert : 테이블에 1개의 행만 있는지 확인
//        assertTrue(rvdao.count("1")==1);
//    }
//
//    @Test
//    public void deleteAll() throws Exception{
//        // given : 테이블 초기화 & 후기글 100개 insert
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        ReviewCommentDTO dto = null;
//        for(int i=0; i <100; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        assertTrue(rvdao.count("1")==100);
//        // do : 후기글 20개 삭제
//        List<ReviewCommentDTO> list = rvdao.selectAll("1");
//        assertTrue(list.size()==100);
//        for(int i=0; i<20; i++) {
//            String b1 = list.get(i).getBuy_id();
//            int n1 = list.get(i).getNo();
//            assertTrue(rvdao.delete(b1,n1)==1);
//        }
//        // assert : 후기글 모두 삭제시 80개가 삭제되는지 확인
//        assertTrue(rvdao.deleteAll("1")==80);
//
//        // given : 테이블 초기화 & 후기글 100개 insert
//        rvdao.deleteAll("1");
//        assertTrue(rvdao.count("1")==0);
//        dto = null;
//        for(int i=0; i <100; i++) {
//            dto = new ReviewCommentDTO("1",Integer.toString(i+1),"맛있어요");
//            assertTrue(rvdao.insert(dto)==1);
//        }
//        assertTrue(rvdao.count("1")==100);
//        // do : 후기글 40개 삭제
//        list = rvdao.selectAll("1");
//        assertTrue(list.size()==100);
//        for(int i=0; i<40; i++) {
//            String b1 = list.get(i).getBuy_id();
//            int n1 = list.get(i).getNo();
//            assertTrue(rvdao.delete(b1,n1)==1);
//        }
//        // assert : 후기글 모두 삭제시 60개가 삭제되는지 확인
//        assertTrue(rvdao.deleteAll("1")==60);
//    }
//}