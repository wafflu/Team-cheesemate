package team.cheese.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.UserDto;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class AddrCdServiceTest extends TestCase {

    @Autowired
    private AddrCdService addrCdService;

    @Autowired
    private UserService userService;

    // *** 모든 관리자의 수를 가져오는지 테스트 ***
    @Test
    public void testGetAddrCdCnt() {
        System.out.println("*** testGetAddrCdCnt 테스트 시작 ***");

        int cnt = addrCdService.getAddrCdCnt();
        System.out.println("cnt = " + cnt);

        assertTrue(cnt == 2);
    }

    // *** 모든 관리자를 불러오는지 테스트 ***
    @Test
    public void testGetAllAddrCd() {
        System.out.println("*** testGetAllAddrCd 테스트 시작 ***");

        List<AddrCdDto> addrCdDtoList = addrCdService.getAllAddrCd();

        System.out.println("List로 불러온 관리자 수 : " + addrCdDtoList.size());
        System.out.println("모든 관리자를 카운트한 수 : " + addrCdService.getAddrCdCnt());

        assertTrue(addrCdDtoList.size() == addrCdService.getAddrCdCnt());
    }

    // *** 아이디로 특정 데이터만 가져올 수 있는지 테스트 ***
    // 1. 모든 addr_cd 불러오기
    // 2. 모든 유저 불러오기
    // 3. 랜덤 유저의 아이디 선택
    // 4. 유저의 아이디로 특정 addr_cd 검색
    @Test
    public void testGetAddrCdByUserId() {
        System.out.println("*** testGetAddrCdByUserId 테스트 시작 ***");

        List<AddrCdDto> addrCdDtoList = addrCdService.getAllAddrCd();
        assertTrue(addrCdDtoList.size() == addrCdService.getAddrCdCnt());
        System.out.println("모든 Addr_Cd의 개수 : " + addrCdDtoList.size());

        List<UserDto> userDtoList = userService.getAllUsers();
        assertTrue(userDtoList.size() == userService.getCnt());
        System.out.println("모든 유저 수 : " + userDtoList.size());

        UserDto userDto = userDtoList.get(0);
        System.out.println("userDto Id : " + userDto.getId());

        List<AddrCdDto> searchAddrCdDtoList = addrCdService.getAddrCdByUserId(userDto.getId());
        System.out.println("searchAddrCdDtoList 개수 : " + searchAddrCdDtoList.size());

        for(int i=0; i<searchAddrCdDtoList.size(); i++){
            System.out.println(searchAddrCdDtoList.get(i).toString());
        }
    }
}