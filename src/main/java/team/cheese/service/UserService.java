package team.cheese.service;

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
    // - 로그인 성공, 실패를 확인하는 메서드, 성공시 로그인한 유저를 리턴한다
    // 1. DB에 inputId가 있는지 확인한다
    //      1.1 inputId가 있는 경우
    //          1.1.1 inputPw가 유효한 경우 해당 유저 리턴
    //          1.1.2 inputPw가 유효하지 않은 경우 null 리턴
    //      1.2 inputId가 없는 경우 null 리턴
    UserDto login(String inputId, String inputPw);

    // *** 모든 유저/관리자의 아이디를 리턴 ***
    List<String> getAllUsersAdminsId(List<String> adminIdList);

    // *** 회원가입 기능 ***
    int insertNewUser(UserDto dto) throws NoSuchAlgorithmException;

    // *** 비밀번호 암호화 기능 ***
    default String hashPassword(String password) throws NoSuchAlgorithmException {

        // SHA-256 해시 알고리즘 인스턴스 생성
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // 입력된 비밀번호를 바이트 배열로 변환하여 해싱
        byte[] encodedHash = digest.digest(password.getBytes());

        // 해시값을 16진수 문자열로 변환
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        // 암호화된 비밀번호 리턴
        return hexString.toString();
    }
}
