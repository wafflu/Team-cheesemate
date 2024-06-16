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

    public int getAdminCnt() {
        return adminDao.getAdminCnt();
    }

    public List<AdminDto> getAllAdmins() {
        return adminDao.getAllAdmins();
    }

    public AdminDto getAdminById(String id) {
        return adminDao.getAdminById(id);
    }

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
