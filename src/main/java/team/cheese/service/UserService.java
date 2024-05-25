package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.dao.MyPage.UserInfoDao;
import team.cheese.dao.UserDao;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.MyPage.UserInfoDTO;
import team.cheese.domain.UserDto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private AddrCdService addrCdService;

    // *** 모든 유저를 삭제하는 메서드 ***
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }

    // *** 모든 유저의 수 카운트하는 메서드 ***
    public int getCnt() {
        return userDao.getCnt();
    }

    // *** 모든 유저를 리스트로 불러오는 메서드 ***
    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers();
    }

    // *** 특정 아이디를 가진 유저를 가져오는 메서드 ***
    public UserDto getUserById(String id) {
        return userDao.getUserById(id);
    }

    public UserDto login(String inputId, String inputPw) {

        try {
            UserDto dto = userDao.getUserById(inputId);

            if(dto != null) {
                if(dto.getPw().equals(hashPassword(inputPw))) {
                    return dto;
                }
                else {
                    System.out.println("유효하지 않은 유저 비밀번호 입니다.");
                    return null;
                }
            }
            else {
                System.out.println("유효하지 않은 유저 아이디 입니다.");
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
    @Transactional
    public int insertNewUser(UserDto dto, AddrCdDto addrCdDto) throws NoSuchAlgorithmException {
        //유저
        dto.setPw(hashPassword(dto.getPw()));

        userDao.insertNewUser(dto);

        //주소
        addrCdService.insertAddrCd(addrCdDto);


        // 소개글 작성 (insert)
        UserInfoDTO userInfoDTO = new UserInfoDTO(dto.getId(),dto.getNick(),"");
        userInfoDTO.setContents("test");
        try {
            int rowCnt = userInfoDao.insert(userInfoDTO);
            System.out.println("rowCnt"+rowCnt);
            if(rowCnt!=1)
                throw new RuntimeException("소개글 작성 중 예외가 발생했습니다");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return 1;
    }

    public int updateUser(Map map) {
        return userDao.updateUser(map);
    }

    public int updateUserPw(Map<String, String> map) throws NoSuchAlgorithmException {
        map.put("pw", hashPassword((String) map.get("pw")));
        return userDao.updateUserPW(map);
    }

    public int updateUser_s_cd(Map map) {
        return userDao.updateUser_s_cd(map);
    }

    // *** 비밀번호 암호화 기능 ***
    private String hashPassword(String password) throws NoSuchAlgorithmException {

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