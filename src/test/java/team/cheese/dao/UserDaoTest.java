package team.cheese.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import team.cheese.domain.UserDto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoTest extends TestCase {

    @Autowired
    UserDao userDao;

    // *** 모든 유저 수를 가져올 수 있는지 테스트 ***
    @Test
    public void testGetCnt() {
        userDao.deleteAllUsers();
        assertTrue(userDao.getCnt() == 0);

        for(int i = 0; i < 10; i++) {
            UserDto userDto = new UserDto("User_" + i, "1234", "insertDaoTest", "insertDaoTest", "1999-01-02", 'M', "01012345678", "05012345678", 'Y', "inserDaoTest@google.com", 'O', "풍무동-2", new Timestamp(System.currentTimeMillis()), "", new Timestamp(System.currentTimeMillis()), "");
            userDao.insertNewUser(userDto);
        }

        int cnt = userDao.getCnt();
        System.out.println("cnt : " + cnt);
        assertTrue(cnt == 10);
    }

    // *** 모든 유저의 정보를 가져올 수 있는지 테스트 ***
    @Test
    public void testGetAllUser() {
        userDao.deleteAllUsers();
        assertTrue(userDao.getCnt() == 0);

        for(int i = 0; i < 10; i++) {
            UserDto userDto = new UserDto("User_" + i, "1234", "insertDaoTest", "insertDaoTest", "1999-01-02", 'M', "01012345678", "05012345678", 'Y', "inserDaoTest@google.com", 'O', "풍무동-2", new Timestamp(System.currentTimeMillis()), "", new Timestamp(System.currentTimeMillis()), "");
            userDao.insertNewUser(userDto);
        }
        assertEquals(userDao.getAllUsers().size(), userDao.getCnt());

        ArrayList<UserDto> arrayList = (ArrayList<UserDto>) userDao.getAllUsers();
        System.out.println("arrayListSize : " + arrayList.size());

        assertTrue(arrayList.size() == userDao.getCnt());
    }

    // *** 특정 아이디를 가진 유저를 가져올 수 있는가?(1) ***
    // 1. Select를 통해 모든 유저의 정보를 불러온다
    // 2. 유저 중 랜덤 유저의 아이디를 가져온다
    // 3. 해당 아이디로 검색을 해 찾아지는지 확인한다.
    @Test
    public void testGetUserById() {
        System.out.println("*** testGetUserById 테스트 시작 ***");
        userDao.deleteAllUsers();
        assertTrue(userDao.getCnt() == 0);

        for(int i = 0; i < 10; i++) {
            UserDto userDto = new UserDto("User_" + i, "1234", "insertDaoTest", "insertDaoTest", "1999-01-02", 'M', "01012345678", "05012345678", 'Y', "inserDaoTest@google.com", 'O', "풍무동-2", new Timestamp(System.currentTimeMillis()), "", new Timestamp(System.currentTimeMillis()), "");
            userDao.insertNewUser(userDto);
        }
        assertEquals(userDao.getAllUsers().size(), userDao.getCnt());

        // 1. Select를 통해 모든 유저의 정보를 불러온다
        ArrayList<UserDto> arrayList = (ArrayList<UserDto>) userDao.getAllUsers();
        assertTrue(arrayList.size() == userDao.getCnt());

        for(int i=0; i<arrayList.size(); i++) {
            UserDto dto = arrayList.get(i);
            System.out.printf("%d | UserId : %s\n", i, dto.getId());
        }

        // 2. 유저 중 랜덤 유저의 아이디를 가져온다
        int randomNum = (int)(Math.random()*arrayList.size());
        System.out.println("randomNum : " + randomNum);

        UserDto randomUserDto = arrayList.get(randomNum);
        System.out.println("randomUserDto Id : " + randomUserDto.getId());

        // 3. 해당 아이디로 검색을 해 찾아지는지 확인 후 일치하는지 확인한다
        UserDto searchUserDto = userDao.getUserById(randomUserDto.getId());
        System.out.println("searchUserDto Id : " + searchUserDto.getId());
        assertTrue(searchUserDto.getId().equals(randomUserDto.getId()));
    }

    // *** 특정 아이디를 가진 유저를 가져올 수 있는가?(2) ***
    // 1. 새로운 유저를 추가한다
    // 2. 새로운 유저의 아이디로
    // 3. 해당 아이디로 검색을 해 찾아지는지 확인한다.

    // *** 새로운 유저가 잘 insert 되는지 테스트 ***
    @Test
    public void testInsertNewUser() {
        System.out.println("*** testInsertNewUser 테스트 시작 ***");
        userDao.deleteAllUsers();
        assertTrue(userDao.getCnt() == 0);

        for(int i = 0; i < 10; i++) {
            UserDto userDto = new UserDto("User_" + i, "1234", "insertDaoTest", "insertDaoTest", "1999-01-02", 'M', "01012345678", "05012345678", 'Y', "inserDaoTest@google.com", 'O', "풍무동-2", new Timestamp(System.currentTimeMillis()), "", new Timestamp(System.currentTimeMillis()), "");
            userDao.insertNewUser(userDto);
        }
        assertEquals(userDao.getAllUsers().size(), userDao.getCnt());

        String insertUserId = "insertDaoTest";
        UserDto userDto = new UserDto(insertUserId, "1234", "insertDaoTest", "insertDaoTest", "1999-01-02", 'M', "01012345678", "05012345678", 'Y', "inserDaoTest@google.com", 'O', "풍무동-2", new Timestamp(System.currentTimeMillis()), "", new Timestamp(System.currentTimeMillis()), "");
        System.out.println("insertUser Id : " + insertUserId);
        assertTrue(userDao.insertNewUser(userDto) == 1);

        UserDto searchUser = userDao.getUserById(insertUserId);
        System.out.println("searchUser Id : " + searchUser.getId());
        assertTrue(userDto.getId().equals(searchUser.getId()));
    }

    // *** 모든 유저의 아이디를 가져오는지 테스트(1) ***
    // 1. 모든 유저의 정보를 불러오기
    // 2. 모든 유저의 아이디만 불러오기
    // 3. 두 배열의 Id 비교
    @Test
    public void testGetAllUsersId() {
        System.out.println("*** testGetAllUserId 테스트 시작 ***");
        userDao.deleteAllUsers();
        assertTrue(userDao.getCnt() == 0);

        for(int i = 0; i < 10; i++) {
            UserDto userDto = new UserDto("User_" + i, "1234", "insertDaoTest", "insertDaoTest", "1999-01-02", 'M', "01012345678", "05012345678", 'Y', "inserDaoTest@google.com", 'O', "풍무동-2", new Timestamp(System.currentTimeMillis()), "", new Timestamp(System.currentTimeMillis()), "");
            userDao.insertNewUser(userDto);
        }
        assertEquals(userDao.getAllUsers().size(), userDao.getCnt());

        List<UserDto> userDtoList = userDao.getAllUsers();
        assertTrue(userDtoList.size() == userDao.getCnt());

        List<String> userIdList = userDao.getAllUsersId();
        assertTrue(userIdList.size() == userDao.getCnt());

        for(int i=0; i<userDtoList.size(); i++) {
            System.out.printf("userDtoList[%d] userId : %s\n", i, userDtoList.get(i).getId());
            System.out.printf("userIdList[%d] userId : %s\n", i, userIdList.get(i));
            System.out.println("--------------------");
            assertTrue(userDtoList.get(i).getId().equals(userIdList.get(i)));
        }
    }

    // *** 모든 유저의 아이디를 가져오는지 테스트(2) ***
    // 1. 새 유저를 하나 추가
    // 2. 새 유저의 아이디를 불러오는지 테스트
}