package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.UserDto;

import java.util.List;

@Repository
public interface UserDao {

    public void deleteAllUsers();
    public int getCnt();
    public List<UserDto> getAllUsers();
    public UserDto getUserById(String id);
    public int insertNewUser(UserDto userDto);
    public List<String> getAllUsersId();

}
