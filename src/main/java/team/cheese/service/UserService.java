package team.cheese.service;

import team.cheese.domain.AddrCdDto;
import team.cheese.domain.UserDto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    // *** 모든 유저를 삭제하는 메서드 ***
    void deleteAllUsers();

    // *** 모든 유저의 수 카운트하는 메서드 ***
    int getCnt();

    // *** 모든 유저를 리스트로 불러오는 메서드 ***
    List<UserDto> getAllUsers();

    // *** 특정 아이디를 가진 유저를 가져오는 메서드 ***
    UserDto getUserById(String id);

    // *** 로그인 기능 ***
    UserDto login(String inputId, String inputPw);

    // *** 모든 유저/관리자의 아이디를 리턴 ***
    List<String> getAllUsersAdminsId(List<String> adminIdList);

    // *** 회원가입 기능 ***
    int insertNewUser(UserDto dto, AddrCdDto addrCdDto) throws NoSuchAlgorithmException;

    // *** 비밀번호 암호화 기능 ***
    public String hashPassword(String password) throws NoSuchAlgorithmException;
}
