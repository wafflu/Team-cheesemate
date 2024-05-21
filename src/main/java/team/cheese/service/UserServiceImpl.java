package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.dao.UserDao;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.UserDto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddrCdService addrCdService;

    @Override
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }

    @Override
    public int getCnt() {
        return userDao.getCnt();
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public UserDto getUserById(String id) {
        return userDao.getUserById(id);
    }

    // *** 로그인 기능 ***
    @Override
    public UserDto login(String inputId, String inputPw) {

        try {
            UserDto dto = userDao.getUserById(inputId);

            if(dto != null && dto.getPw().equals(hashPassword(inputPw)) && dto.getS_cd() == 'O') {
                    return dto;
            }
            else {
                return null;
            }
        } catch (DataAccessException e) {
            System.out.println("DB Access Exception");
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException");
            e.printStackTrace();
            return null;
//            throw new RuntimeException(e);
        }
    }

    // *** 모든 유저/관리자의 아이디를 리턴 ***
    @Override
    public List<String> getAllUsersAdminsId(List<String> adminIdList) {
        try {
            List<String> userIdList = userDao.getAllUsersId();
            userIdList.addAll(adminIdList);

            return userIdList;
        } catch (Exception e) {
            System.out.println("DB Access Exception");
            e.printStackTrace();
            return null;
        }
    }

    // *** 회원가입 기능 ***
    // - Tx 처리
    @Override
    @Transactional
    public int insertNewUser(UserDto dto, AddrCdDto addrCdDto) throws NoSuchAlgorithmException {

        dto.setPw(hashPassword(dto.getPw()));

        userDao.insertNewUser(dto);

        return addrCdService.insertAddrCd(addrCdDto);
    }

    // *** 비밀번호 암호화 기능 ***
    public String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] encodedHash = digest.digest(password.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
