package team.cheese.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import team.cheese.domain.UserDto;

import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserServiceTest extends TestCase {

    @Autowired
    private UserService userService;

    // *** 모든 유저의 수 카운트 테스트 ***
    @Test
    public void testGetCnt() throws NoSuchAlgorithmException {
        System.out.println("*** testGetCnt 테스트 시작 ***");
        userService.deleteAllUsers();
        assertTrue(userService.getCnt() == 0);

        for(int i = 0; i < 10; i++) {
            UserDto userDto = new UserDto("User_" + i, "1234", "insertDaoTest", "insertDaoTest", "1999-01-02", 'M', "01012345678", "05012345678", 'Y', "inserDaoTest@google.com", 'O', "풍무동-2", new Timestamp(System.currentTimeMillis()), "", new Timestamp(System.currentTimeMillis()), "");
            userService.insertNewUser(userDto);
        }
        assertTrue(userService.getCnt() == 10);
    }

    // *** 모든 유저를 리스트로 불러오는지 테스트 ***
    @Test
    public void testGetAllUsers() {
        System.out.println("*** testGetAllUsers 테스트 시작 ***");
        ArrayList<UserDto> arrayList = (ArrayList<UserDto>) userService.getAllUsers();
        assertTrue(arrayList.size() == 2);
    }

    // *** 특정 아이디를 가진 유저를 가져올 수 있는지 테스트 ***
    // 1. Select를 통해 모든 유저의 정보를 불러온다
    // 2. 유저 중 랜덤 유저의 아이디를 가져온다
    // 3. 해당 아이디로 검색을 해 찾아지는지 확인한다.
    @Test
    public void testGetUserById() {
        System.out.println("*** testGetUserById 테스트 시작 ***");

        // 1. Select를 통해 모든 유저의 정보를 불러온다
        ArrayList<UserDto> arrayList = (ArrayList<UserDto>) userService.getAllUsers();
        assertTrue(arrayList.size() == 2);

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
        UserDto searchUserDto = userService.getUserById(randomUserDto.getId());
        System.out.println("searchUserDto Id : " + searchUserDto.getId());
        assertTrue(searchUserDto.getId().equals(randomUserDto.getId()));
    }

    // *** 로그인 기능을 테스트 ***
    // 1. Select를 통해 모든 유저의 정보를 불러온다
    // 2. 유저 중 랜덤 유저의 아이디를 가져온다
    // 3. 로그인 테스트를 진행한다
    //      3.1 유효하지 않은 아이디를 입력하는 경우
    //      3.2 유효하지 않은 비밀번호를 입력하는 경우
    //      3.3 유효한 아이디와 비밀번호를 입력하는 경우
    @Test
    public void testLogin() {
        System.out.println("*** testLogin 테스트 시작 ***");

        // 1. Select를 통해 모든 유저의 정보를 불러온다
        ArrayList<UserDto> arrayList = (ArrayList<UserDto>) userService.getAllUsers();
        assertTrue(arrayList.size() == 2);

        // 2. 유저 중 랜덤 유저의 아이디를 가져온다
        int randomNum = (int)(Math.random()*arrayList.size());
        System.out.println("randomNum : " + randomNum);

        UserDto randomUserDto = arrayList.get(randomNum);
        System.out.println("randomUserDto Id : " + randomUserDto.getId());

        // 3. 로그인 테스트를 진행한다
        //      3.1 유효하지 않은 아이디를 입력하는 경우
        assertNull(userService.login("errorId", "errorPw"));

        //      3.2 유효하지 않은 비밀번호를 입력하는 경우
        assertNull(userService.login(randomUserDto.getId(),"errorPw"));

        //      3.3 유효한 아이디와 비밀번호를 입력하는 경우
        assertEquals(userService.login(randomUserDto.getId(), randomUserDto.getPw()), randomUserDto);
    }

    // *** 회원가입 기능 테스트(수정 필요) ***
    // 1. 아이디가 중복되는 경우
    // 2.         중복되지 않는 경우
    @Test
    public void testInsertNewUser() throws NoSuchAlgorithmException {
        System.out.println("*** testInsertNewUser 테스트 시작 ***");

        String insertUserId = "insertServiceTest";
        UserDto insertUserDto = new UserDto(insertUserId, "1234", "insertServiceTest", "insertServiceTest", "1999-01-02", 'M', "01012345678", "05012345678", 'Y', "insertServiceTest@google.com", 'O', "풍무동-2", new Timestamp(System.currentTimeMillis()), "", new Timestamp(System.currentTimeMillis()), "");
        System.out.println("insertUserDto Id : " + insertUserDto.getId());
        userService.insertNewUser(insertUserDto);

        UserDto searchUserDto = userService.getUserById(insertUserDto.getId());
        System.out.println("searchUserDto Id : " + searchUserDto.getId());
        assertEquals(insertUserDto, searchUserDto);
    }
}