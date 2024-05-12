package team.cheese.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import team.cheese.domain.AddrCdDto;

//import team.cheese.domain.UserDto;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class AddrCdDaoTest extends TestCase {

    @Autowired
    AddrCdDao addrCdDao;

    // *** 모든 addr_cd를 셀 수 있는지 테스트 ***
    @Test
    public void testGetAddrCdCnt() {
        System.out.println("*** testGetAdministerCnt 테스트 시작 ***");

        int cnt = addrCdDao.getAddrCdCnt();
        System.out.println("모든 주소 수(cnt) : " + cnt);

        assertTrue(cnt == 2);
    }

    // *** 모든 addr_cd를 가져올 수 있는지 테스트
    @Test
    public void testGetAllAddrCd() {
        System.out.println("*** testGetAllAddrCd 테스트 시작 ***");

        List<AddrCdDto> list = addrCdDao.getAllAddrCd();

        assertTrue(list.size() == addrCdDao.getAddrCdCnt());
    }

    // *** 아이디로 특정 데이터만 가져올 수 있는지 테스트 ***
    // 1. 모든 add_cd 불러오기
    // 2. 모든 유저 불러오기
    // 3. 랜덤 유저의 아이디 선택
    // 4. 유저의 아이디로 특정 addr_cd 검색

    @Test
    public void testGetAddrCdByUserId() {
        System.out.println("*** testGetAllAddrCd 테스트 시작 ***");


        String userId = "asdf";

        List<AddrCdDto> addrCdDtoList = addrCdDao.getAddrCdByUserId(userId);
        System.out.printf("%s addrCdDtoList.size() = %d\n", userId, addrCdDtoList.size());
        assertTrue(addrCdDtoList.size() == 2);
    }

}