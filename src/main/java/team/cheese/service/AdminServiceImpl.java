package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import team.cheese.dao.AdminDao;
import team.cheese.domain.AdminDto;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    // *** 모든 유저의 수 카운트하는 메서드 ***
    @Override
    public int getAdminCnt() {
        return adminDao.getAdminCnt();
    }

    // *** 모든 유저를 리스트로 불러오는 메서드 ***
    @Override
    public List<AdminDto> getAllAdmins() {
        return adminDao.getAllAdmins();
    }

    // *** 특정 아이디를 가진 유저를 가져오는 메서드 ***
    @Override
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
    @Override
    public AdminDto login(String inputId, String inputPw) {
        System.out.println("*** AdminService에서 login 기능을 수행합니다. ***");

        try {
            AdminDto dto = adminDao.getAdminById(inputId);

            if(dto != null) {
                if(dto.getPw().equals(inputPw)) {
                    System.out.println("관리자 로그인 성공.");
                    return dto;
                }
                else {
                    System.out.println("유효하지 않은 관리자 비밀번호 입니다.");
                    return null;
                }
            }
            else {
                System.out.println("유효하지 않은 관리자 아이디 입니다.");
                return null;
            }
        } catch (DataAccessException e) {
            System.out.println("DB Access Exception");
            e.printStackTrace();
            return null;
        }
    }

    // *** 모든 관리자의 아이디를 리턴 ***
    @Override
    public List<String> getAllAdminsId() {
        System.out.println("*** AdminService에서 getAllAdminsId 기능을 수행합니다. ***");

        try {
            List<String> adminsIdList = adminDao.getAllAdminsId();

            return adminsIdList;
        } catch (Exception e) {
            System.out.println("DB Access Exception");
            e.printStackTrace();
            return null;
        }
    }
}
