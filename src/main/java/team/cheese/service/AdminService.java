package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import team.cheese.dao.AdminDao;
import team.cheese.domain.AdminDto;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    // *** 모든 유저의 수 카운트하는 메서드 ***
    public int getAdminCnt() {
        return adminDao.getAdminCnt();
    }

    // *** 모든 유저를 리스트로 불러오는 메서드 ***
    public List<AdminDto> getAllAdmins() {
        return adminDao.getAllAdmins();
    }

    // *** 특정 아이디를 가진 유저를 가져오는 메서드 ***
    public AdminDto getAdminById(String id) {
        return adminDao.getAdminById(id);
    }

    // *** 로그인 기능 ***
    // - 로그인 성공, 실패를 확인하는 메서드, 성공시 로그인한 유저를 리턴한다
    // 1. DB에 inputId가 있는지 확인한다
    //      1.1 inputId가 있는 경우
    //          1.1.1 inputPw가 유효한 경우 해당 유저 리턴
    //          1.1.2 inputPw가 유효하지 않은 경우 null 리턴
    //      1.2 inputId가 없는 경우 null 리턴
    public AdminDto login(String inputId, String inputPw) {

        try {
            AdminDto dto = adminDao.getAdminById(inputId);

            if(dto != null) {
                if(dto.getPw().equals(inputPw)) {
                    return dto;
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    // *** 모든 관리자의 아이디를 리턴 ***
    public List<String> getAllAdminsId() {

        try {
            List<String> adminsIdList = adminDao.getAllAdminsId();

            return adminsIdList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
