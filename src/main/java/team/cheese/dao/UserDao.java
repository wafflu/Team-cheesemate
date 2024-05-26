package team.cheese.dao;

import org.springframework.stereotype.Repository;
import team.cheese.domain.UserDto;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    public void deleteAllUsers();
    public int getCnt();
    public List<UserDto> getAllUsers();
    public UserDto getUserById(String id);
    public int insertNewUser(UserDto userDto);
    public List<String> getAllUsersId();
    public int updateUser(Map map);
    public int updateUserPW(Map map);
    public int updateUser_s_cd(Map map);
}
