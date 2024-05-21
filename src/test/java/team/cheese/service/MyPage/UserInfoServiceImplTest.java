package team.cheese.service.MyPage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.MyPage.UserInfoDao;
import team.cheese.domain.MyPage.UserInfoDTO;


import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserInfoServiceImplTest {
    @Autowired
    UserInfoService userService;

    @Autowired
    UserInfoDao userInfoDao;

    // 소개글 작성 -> write
    // 성공
        @Test
        public void writeSuccessTest1() throws Exception {
            // given&when : 테이블초기화 & UserInfoDTO 1개 생성
            cleanDB();
            assertTrue(userService.getCount()==0);
            UserInfoDTO userInfoDTO = createData(1);
            // do : 소개글 1개 작성
            userService.write(userInfoDTO);
            // assert : 테이블에 1개의 행이 있는지 & DTO와 read한 DTO가 일치하는지 확인
            assertTrue(userService.getCount()==1);
            assertTrue(userInfoDTO.equals(userService.read("1")));
        }
        @Test
        public void writeSuccessTest2() throws Exception {
            // given&when : 테이블초기화 & UserInfoDTO 10개 생성
            cleanDB();
            assertTrue(userService.getCount()==0);
            // do : 소개글 10개 작성
            insertData(10);
            // assert : 테이블에 10개의 행이 있는지 & DTO와 read한 DTO가 일치하는지 확인
            assertTrue(userService.getCount()==10);
            for(int i=1; i<=10; i++) {
                assertTrue(userService.read(i+"").getUr_id().equals(i+""));
            }
        }
        @Test
        public void writeSuccessTest3() throws Exception {
            // given&when : 테이블초기화 & UserInfoDTO 100개 생성
            cleanDB();
            assertTrue(userService.getCount()==0);
            // do : 소개글 100개 작성
            insertData(100);
            // assert : 테이블에 100개의 행이 있는지 & DTO와 read한 DTO가 일치하는지 확인
            assertTrue(userService.getCount()==100);
            for(int i=1; i<=100; i++) {
                assertTrue(userService.read(i+"").getUr_id().equals(i+""));
            }
        }
    // 실패
        @Test(expected = Exception.class)
        public void writeFaildTest1() throws Exception {
            // given&when : 테이블초기화 & ur_id가 null인 UserInfoDTO 1개 생성
            cleanDB();
            assertTrue(userService.getCount()==0);

            UserInfoDTO userInfoDTO = new UserInfoDTO(null,"1","1");
            // do : 소개글 1개 작성
            userService.write(userInfoDTO);
            // assert : 테이블에 0개의 행이 있는지 & 예상한 예외가 발생했는지 확인
            assertTrue(userService.getCount()==0);
        }
        @Test(expected = Exception.class)
        public void writeFaildTest2() throws Exception {
            // given&when : 테이블초기화 & ur_id가 user테이블에 없는 값으로 UserInfoDTO 1개 생성
            cleanDB();
            assertTrue(userService.getCount()==0);
            UserInfoDTO userInfoDTO = new UserInfoDTO(2000+"","1","1");
            // do : 소개글 1개 작성
            userService.write(userInfoDTO);
            // assert : 테이블에 0개의 행이 있는지 & 예상한 예외가 발생했는지 확인
            assertTrue(userService.getCount()==0);
        }
        @Test(expected = Exception.class)
        public void writeFaildTest3() throws Exception {
            // given&when : 테이블초기화 & ur_id를 중복된 값으로 UserInfoDTO 1개 생성
            cleanDB();
            assertTrue(userService.getCount()==0);
            insertData(1);
            UserInfoDTO userInfoDTO = createData(1);
            // do : 소개글 1개 작성
            userService.write(userInfoDTO);
            // assert : 테이블에 1개의 행이 있는지 & 예상한 예외가 발생했는지 확인
            assertTrue(userService.getCount()==1);
        }

    // 소개글 삭제 -> remove
    // 성공
        @Test
        public void removeSuccessTest1() throws Exception {
            // given&when : 테이블초기화 & UserInfoDTO 1개 생성 & 소개글 1개 작성
            cleanDB();
            assertTrue(userService.getCount()==0);
            insertData(1);
            assertTrue(userService.getCount()==1);
            // do : 소개글 1개 삭제
            userService.remove("1");
            // assert : 테이블이 비었는지 확인
            assertTrue(userService.getCount()==0);
        }
        @Test
        public void removeSuccessTest2() throws Exception {
            // given&when : 테이블초기화 & UserInfoDTO 10개 생성 & 소개글 10개 작성
            cleanDB();
            assertTrue(userService.getCount()==0);
            insertData(10);
            assertTrue(userService.getCount()==10);
            // do : 소개글 10개 삭제
            for(int i=1; i<=10; i++) {
                userService.remove(i+"");
            }
            // assert : 테이블이 비었는지 확인
            assertTrue(userService.getCount()==0);
        }
        @Test
        public void removeSuccessTest3() throws Exception {
            // given&when : 테이블초기화 & UserInfoDTO 100개 생성 & 소개글 100개 작성
            cleanDB();
            assertTrue(userService.getCount()==0);
            insertData(100);
            assertTrue(userService.getCount()==100);
            // do : 소개글 100개 삭제
            for(int i=1; i<=100; i++) {
                userService.remove(i+"");
            }
            // assert : 테이블이 비었는지 확인
            assertTrue(userService.getCount()==0);
        }

    // 실패
        @Test(expected = Exception.class)
        public void removeFaildTest1() throws Exception  {
            // given&when : 테이블초기화
            cleanDB();
            assertTrue(userService.getCount()==0);
            // do : 소개글 1개 삭제 (아무것도 없는 테이블에서 삭제하려고 할때)
            userService.remove("1");
            // assert : 예상한 예외가 발생하는지 확인
            assertTrue(userService.getCount()==0);
        }
        @Test(expected = Exception.class)
        public void removeFaildTest2() throws Exception  {
            // given&when : 테이블초기화 & UserInfoDTO 1개 생성 & 소개글 1개 작성
            cleanDB();
            assertTrue(userService.getCount()==0);
            insertData(1);
            assertTrue(userService.getCount()==1);
            // do : 소개글 1개 삭제 ( ur_id값을 null로 지정했을 때)
             userService.remove(null);
            // assert : 테이블에 행이 1개인지 확인 & 예상한 예외가 발생했는지 확인
            assertTrue(userService.getCount()==1);
        }



    // 소개글 읽기 (자신의 소개글인지 다른사람의 소개글인지)
    // 성공
        // 자신의 소개글 읽기 -> 방문자수 증가 X
//        @Test
//        public void readSuccessTest1() throws Exception {
//            // given&when : 테이블초기화 & UserInfoDTO 1개 생성 & 소개글 1개 작성
//            cleanDB();
//            assertTrue(userService.getCount()==0);
//            UserInfoDTO userInfoDTO = new UserInfoDTO("1","1","1");
//           userService.write(userInfoDTO);
//            assertTrue(userService.getCount()==1);
//            // do : 소개글 1개 읽기
//            UserInfoDTO userInfoDTO2 = userService.read(null,"1");
//            // assert : DTO와 read한 DTO가 일치하는지 확인 & 방문자 수가 증가 안했는지 확인
//            assertTrue(userInfoDTO2.equals(userInfoDTO));
//            assertTrue(userInfoDTO2.getView_cnt()==0);
//        }
//        // 다른 사람의 소개글 읽기 -> 방문자수 증가
//        @Test
//        public void readSuccessTest2() throws Exception {
//            // given&when : 테이블초기화 & UserInfoDTO 1개 생성 & 소개글 1개 작성
//            cleanDB();
//            assertTrue(userService.getCount()==0);
//            UserInfoDTO userInfoDTO = new UserInfoDTO("1","1","1");
//            userService.write(userInfoDTO);
//            assertTrue(userService.getCount()==1);
//            // do : 소개글 1개 읽기
//            UserInfoDTO userInfoDTO2 = userService.read("1","2");
//            // assert :  방문자 수가 증가 했는지 확인
//            assertTrue(userInfoDTO2.getView_cnt()==1);
//        }
//    // 실패
//        @Test(expected = Exception.class)
//        public void readFaildTest1() throws Exception {
//            // sesson_id값이 null일떄
//            // given&when : 테이블초기화 & UserInfoDTO 1개 생성 & 소개글 1개 작성
//            cleanDB();
//            assertTrue(userService.getCount()==0);
//            UserInfoDTO userInfoDTO = new UserInfoDTO("1","1","1");
//            userService.write(userInfoDTO);
//            assertTrue(userService.getCount()==1);
//            // do : 소개글 1개 읽기
//            UserInfoDTO userInfoDTO1 = userService.read(null,null);
//            // assert : 예상한 예외가 발생하는지 확인
//        }
//        @Test(expected = Exception.class)
//        public void readFaildTest2() throws Exception {
//            // session_id값이 테이블에 없는 아이디일떄
//            // given&when : 테이블초기화 & UserInfoDTO 1개 생성 & 소개글 1개 작성
//            cleanDB();
//            assertTrue(userService.getCount()==0);
//            UserInfoDTO userInfoDTO = new UserInfoDTO("1","1","1");
//            userService.write(userInfoDTO);
//            assertTrue(userService.getCount()==1);
//            // do : 소개글 1개 읽기
//            UserInfoDTO userInfoDTO1 = userService.read(null,"2");
//            // assert : 예상한 예외가 발생하는지 확인
//        }
//
//    // 소개글 수정
//    // 성공
//        @Test
//        public void modifySuccessTest1() throws Exception {
//            // given&when : 테이블초기화 & UserInfoDTO 1개 생성 & 소개글 1개 작성 & update할 UserInfoDTO 1개 생성
//            cleanDB();
//            assertTrue(userService.getCount()==0);
//            insertData(1);
//            assertTrue(userService.getCount()==1);
//            UserInfoDTO userInfoDTO = createUpdateData(1);
//            // do : update할 DTO로 update
//            userService.modify(userInfoDTO);
//            // assert : update할 DTO와 read한 DTO의 내용이 일치하는지 확인
//            assertTrue(userInfoDTO.getContents().equals(userService.read("1").getContents()));
//        }
//    // 실패
//        @Test(expected = Exception.class)
//        public void modifyFaildTest1() throws Exception {
//            // update할 DTO의 ur_id가 null일떄
//            // given&when : 테이블초기화 & UserInfoDTO 1개 생성 & 소개글 1개 작성 & update할 UserInfoDTO 1개 생성
//            cleanDB();
//            assertTrue(userService.getCount()==0);
//            insertData(1);
//            assertTrue(userService.getCount()==1);
//            UserInfoDTO userInfoDTO = new UserInfoDTO(null,null,null);
//            // do : update할 DTO로 update
//             userService.modify(userInfoDTO);
//            // assert : 예상한 예외가 발생하는지 확인
//        }

    // 보조 메서드
    private void cleanDB() throws Exception {
        userInfoDao.deleteAll();
    }

    private void insertData(int amount) throws Exception{
        for (int i=1; i<=amount; i++) {
            UserInfoDTO createdData = createData(i);
            userService.write(createdData);
        }
    }

    private UserInfoDTO createData(int i) {
        UserInfoDTO createdUserInfo = new UserInfoDTO(i+"",i+"",i+"" );
        return createdUserInfo;
    }


    private UserInfoDTO createUpdateData(int i) {
        UserInfoDTO createdUserInfo = new UserInfoDTO(i+"",""+i,"new"+i );
        return createdUserInfo;
    }
}