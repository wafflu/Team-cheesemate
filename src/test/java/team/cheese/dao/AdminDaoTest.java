package team.cheese.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import team.cheese.domain.AdminDto;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class AdminDaoTest extends TestCase {

    @Autowired
    private AdminDao adminDao;

    // *** 모든 관리자의 수를 가져오는지 테스트 ***
    @Test
    public void testGetAdminCnt() {
        System.out.println("*** testGetAdministerCnt 테스트 시작 ***");

        int cnt = adminDao.getAdminCnt();

        System.out.println("모든 유저 수(cnt) : " + cnt);

        assertTrue(cnt == 2);
    }

    // *** 모든 관리자를 불러오는지 테스트 ***
    @Test
    public void testGetAllAdmins () {
        System.out.println("*** testGetAllAdmins 테스트 시작 ***");

        List<AdminDto> adminDtoList = adminDao.getAllAdmins();

        System.out.println("List로 불러온 관리자 수 : " + adminDtoList.size());
        System.out.println("모든 관리자를 카운트한 수 : " + adminDao.getAdminCnt());

        assertTrue(adminDtoList.size() == adminDao.getAdminCnt());
    }

    // *** 특정 아이디를 가진 관리자를 불러올 수 있는지 테스트 ***
    // 1. 모든 관리자 불러오기
    // 2. 랜덤으로 관리자 선택
    // 3. 랜덤 관리자의 아이디로 검색
    // 4. 동일한 관리자인지 확인
    @Test
    public void testGetAdminById() {
        System.out.println("*** testGetAdminById 테스트 시작 ***");

        // 1. 모든 관리자 불러오기
        List<AdminDto> adminDtoList = adminDao.getAllAdmins();
        assertTrue(adminDtoList.size() == adminDao.getAdminCnt());

        // 2. 랜덤한 관리자 선택
        AdminDto randomAdminDto = adminDtoList.get((int)(Math.random()*adminDtoList.size()));
        System.out.println("랜덤으로 선택된 Admin 아이디 : " + randomAdminDto.getId());

        // 3. 랜덤 관리자의 아이디로 검색
        AdminDto adminDto = adminDao.getAdminById(randomAdminDto.getId());
        System.out.println("아이디 검색으로 찾은 Admin의 아이디 : " + adminDto.getId());

        // 4. 동일한 관리자인지 확인
        assertEquals(adminDto, randomAdminDto);
    }

    // *** 모든 관리자의 아이디만을 불러오는지 테스트 ***
    // 1. 모든 관리자의 정보를 불러오기
    // 2. 모든 관리자의 아이디만 불러오기
    // 3. 두 배열의 Id 비교
    @Test
    public void testGetAllAdminsId() {
        System.out.println("*** testGetAdminById 테스트 시작 ***");

        List<AdminDto> adminDtoList = adminDao.getAllAdmins();
        assertTrue(adminDtoList.size() == adminDao.getAdminCnt());

        List<String> adminIdList = adminDao.getAllAdminsId();
        assertTrue(adminIdList.size() == adminDao.getAdminCnt());

        for(int i=0; i<adminDtoList.size(); i++) {
            System.out.printf("userDtoList[%d] userId : %s\n", i, adminDtoList.get(i).getId());
            System.out.printf("userIdList[%d] userId : %s\n", i, adminIdList.get(i));
            System.out.println("--------------------");
            assertTrue(adminDtoList.get(i).getId().equals(adminIdList.get(i)));
        }
    }
}