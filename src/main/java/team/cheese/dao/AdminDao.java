package team.cheese.dao;

import org.springframework.stereotype.Repository;
import team.cheese.domain.AdminDto;

import java.util.List;

@Repository
public interface AdminDao {

    public int getAdminCnt();
    public List<AdminDto> getAllAdmins();
    public AdminDto getAdminById(String id);
    public List<String> getAllAdminsId();

}
