package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import team.cheese.domain.FaqDto;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FaqDaoTest {

    @Autowired
    FaqDao faqDao;

    //자주 묻는 질문을 count 한다.
    @Test
    public void count() {
        assertTrue("faqDao is Null",faqDao !=null);

        int cnt = faqDao.count();
        System.out.println(cnt);
        assertTrue(cnt ==130);
    }

    //자주 묻는 질문 중 Y 상태인 전체 리스트를 조회 후 수가 일치 여부를 테스트한다.
    @Test
    public void select(){
        assertTrue("faqDao is Null",faqDao !=null);

        List<FaqDto> list = faqDao.selectAllFaq();

        //list에 들어있는 값이 null이 아닌지 확인한다.
        assertNotNull("비어있으면 안됩니다.",list.isEmpty());

        int cnt = faqDao.selectAllFaq().size();
        System.out.println(cnt);
        assertTrue(cnt ==125);
    }

    //대분류로 검색하여 조회 후 수가 일치 여부를 테스트한다.
    @Test
    public void selectMajor(){
        assertTrue("faqDao is Null",faqDao !=null);

        List<FaqDto> list = faqDao.selectMajorFaq(2);
        //list에 들어있는 값이 null이 아닌지 확인한다.
        assertNotNull("결과가 비어 있어야 합니다.",list.isEmpty());

        System.out.println(list);
        int cnt = faqDao.selectMajorFaq(2).size();
        System.out.println(cnt);
        //DB에 들어있는 수와 일치여부를 테스트한다.
        assertTrue(cnt ==47);
    }

    //유효성 테스트 (음수, 0, 큰 수가 들어왔을 때)
    @Test
    public void selectInvalid(){
        assertTrue("faqDao is Null",faqDao !=null);

        // 예상치 못한 값과 존재하지 않는 코드 포함한다.
        int[] invalidQueCdValues = {-1, 0, 9999};
        for (int que_cd : invalidQueCdValues) {
            List<FaqDto> results = faqDao.selectMajorFaq(que_cd);
            assertTrue("결과가 비어 있어야 합니다.", results.isEmpty()); // 유효하지 않은 입력에 대해 비어 있는 리스트가 반환되어야 함
        }
    }

    // 테스트 완료 시간 = 1초
    @Test(timeout = 1000)
    public void selectAllShouldCompleteInTimelyManner() {
        faqDao.selectAllFaq();
    }

    @Test
    public void delete(){
        assertTrue("faqDao is Null",faqDao !=null);

        int cnt = faqDao.deleteAdmin(134);
        assertTrue(cnt ==1);
    }
    @Test
    public void insert(){
        assertTrue("faqDao is Null",faqDao !=null);

        FaqDto faqDto = new FaqDto();
        faqDto.setQue_i_cd(5);
        faqDto.setQue_i_cd(5);
        faqDto.setQue_i_cd(5);
        faqDto.setQue_i_cd(5);


        int cnt = faqDao.insertAdmin(faqDto);
    }
}