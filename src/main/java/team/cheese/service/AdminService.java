package team.cheese.service;

import team.cheese.domain.AdminDto;

import java.util.List;

public interface AdminService {
    // *** 모든 유저의 수 카운트하는 메서드 ***
    int getAdminCnt();

    // *** 모든 유저를 리스트로 불러오는 메서드 ***
    List<AdminDto> getAllAdmins();

    // *** 특정 아이디를 가진 유저를 가져오는 메서드 ***
    AdminDto getAdminById(String id);

    // *** 로그인 기능 ***
    // - 로그인 성공, 실패를 확인하는 메서드, 성공시 로그인한 유저를 리턴한다
    // 1. DB에 inputId가 있는지 확인한다
    //      1.1 inputId가 있는 경우
    //          1.1.1 inputPw가 유효한 경우 해당 유저 리턴
    //          1.1.2 inputPw가 유효하지 않은 경우 null 리턴
    //      1.2 inputId가 없는 경우 null 리턴
    AdminDto login(String inputId, String inputPw);

    // *** 모든 관리자의 아이디를 리턴 ***
    List<String> getAllAdminsId();
}
