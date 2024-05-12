package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import team.cheese.dao.AdminDao;
import team.cheese.dao.UserDao;
import team.cheese.domain.UserDto;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    // *** 모든 유저를 삭제하는 메서드 ***
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }

    // *** 모든 유저의 수 카운트하는 메서드 ***
    public int getCnt() {
        return userDao.getCnt();
    }

    // *** 모든 유저를 리스트로 불러오는 메서드 ***
    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers();
    }

    // *** 특정 아이디를 가진 유저를 가져오는 메서드 ***
    public UserDto getUserById(String id) {
        return userDao.getUserById(id);
    }

    // *** 로그인 기능 ***
    // - 로그인 성공, 실패를 확인하는 메서드, 성공시 로그인한 유저를 리턴한다
    // 1. DB에 inputId가 있는지 확인한다
    //      1.1 inputId가 있는 경우
    //          1.1.1 inputPw가 유효한 경우 해당 유저 리턴
    //          1.1.2 inputPw가 유효하지 않은 경우 null 리턴
    //      1.2 inputId가 없는 경우 null 리턴
    public UserDto login(String inputId, String inputPw) {
        System.out.println("*** UserService에서 login 기능을 수행합니다. ***");

        try {
            UserDto dto = userDao.getUserById(inputId);

            if(dto != null) {
                if(dto.getPw().equals(inputPw)) {
                    System.out.println("유저 로그인 성공.");
                    return dto;
                }
                else {
                    System.out.println("유효하지 않은 유저 비밀번호 입니다.");
                    return null;
                }
            }
            else {
                System.out.println("유효하지 않은 유저 아이디 입니다.");
                return null;
            }
        } catch (DataAccessException e) {
            System.out.println("DB Access Exception");
            e.printStackTrace();
            return null;
        }
    }

    // *** 모든 유저/관리자의 아이디를 리턴 ***
    public List<String> getAllUsersAdminsId(List<String> adminIdList) {
        System.out.println("*** UserService에서 getAllUsersAdminsId 기능을 수행합니다. ***");

        try {
            List<String> userIdList = userDao.getAllUsersId();
            userIdList.addAll(adminIdList);

            return userIdList;
        } catch (Exception e) {
            System.out.println("DB Access Exception");
            e.printStackTrace();
            return null;
        }
    }

    // *** 회원가입 기능 ***
    public void insertNewUser(UserDto dto) {
        System.out.println("*** UserService에서 insertNewUser 기능을 수행합니다. ***");
        userDao.insertNewUser(dto);
    }
}
