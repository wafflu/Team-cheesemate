package team.cheese.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.domain.AdministrativeDto;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class AdministrativeDaoImplTest extends TestCase {
    @Autowired
    AdministrativeDao administrativedao;

    // 행정동코드 전체 개수 확인
    @Test
    public void testCount() throws Exception {
        // sale테이블에 게시글이 몇개 들어있는지 확인하는 테스트
        System.out.println("count : " + administrativedao.count());
        int cnt = administrativedao.count();
        assertTrue(cnt == 3776);
    }

    // 검색하는 경우 테스트
    @Test
    public void testSearch() throws Exception {
        String letter = "강남";
        List<AdministrativeDto> list =  administrativedao.searchLetter(letter);
        System.out.println(list);
        assertTrue(list.size() == 26);
    }

    @Test
    public void testNotUse() throws Exception {
        // 선택하고
        // 선택한 값을 변경
    }


}