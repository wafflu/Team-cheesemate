package team.cheese.service;

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
public class AdminServiceTest extends TestCase {

    @Autowired
    private AdminService adminService;

    // *** 모든 관리자의 수를 가져오는지 테스트 ***
    @Test
    public void testGetAdminCnt() {
        System.out.println("*** testGetAdminCnt 테스트 시작 ***");

        int cnt = adminService.getAdminCnt();
        System.out.println("cnt: " + cnt);

        assertTrue(cnt == 2);
    }

    // *** 모든 관리자를 불러오는지 테스트 ***
    @Test
    public void testGetAllAdmins() {
        System.out.println("*** testGetAllAdmins 테스트 시작 ***");

        List<AdminDto> adminDtoList = adminService.getAllAdmins();

        System.out.println("List로 불러온 관리자 수 : " + adminDtoList.size());
        System.out.println("모든 관리자를 카운트한 수 : " + adminService.getAdminCnt());

        assertTrue(adminDtoList.size() == adminService.getAdminCnt());
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
        List<AdminDto> adminDtoList = adminService.getAllAdmins();
        assertTrue(adminDtoList.size() == adminService.getAdminCnt());

        // 2. 랜덤한 관리자 선택
        AdminDto randomAdminDto = adminDtoList.get((int)(Math.random()*adminDtoList.size()));
        System.out.println("랜덤으로 선택된 Admin 아이디 : " + randomAdminDto.getId());

        // 3. 랜덤 관리자의 아이디로 검색
        AdminDto adminDto = adminService.getAdminById(randomAdminDto.getId());
        System.out.println("아이디 검색으로 찾은 Admin의 아이디 : " + adminDto.getId());

        // 4. 동일한 관리자인지 확인
        assertEquals(adminDto, randomAdminDto);
    }
}