package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.UserDto;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSession sqlSession;

    private String namespace = "team.cheese.dao.UserDao.";

    @Override
    public void deleteAllUsers() {
        sqlSession.delete(namespace + "deleteAllUsers");
    }

    @Override
    public int getCnt() {
        return sqlSession.selectOne(namespace + "getCnt");
    }

    @Override
    public List<UserDto> getAllUsers() {
        return sqlSession.selectList(namespace + "getAllUsers");
    }

    @Override
    public UserDto getUserById(String id) {
        return sqlSession.selectOne(namespace + "getUserById", id);
    }

    @Override
    public int insertNewUser(UserDto userDto) {
        return sqlSession.insert(namespace + "insertNewUser", userDto);
    }

    @Override
    public List<String> getAllUsersId() {
        return sqlSession.selectList(namespace + "getAllUsersId");
    }
}
