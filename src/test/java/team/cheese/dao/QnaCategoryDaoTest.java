package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.domain.QnaCategoryDto;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class QnaCategoryDaoTest {

    @Autowired
    QnaCategoryDao qnaCategoryDao;

    //카데고리 전체 목록을 카운트한다.
    @Test
    public void CountTest() throws Exception {
        assertTrue("qnaCategoryDao is Null",qnaCategoryDao !=null);

        int cnt = qnaCategoryDao.count();
        System.out.println(cnt);
        assertTrue(cnt ==17);
    }

    //카데고리 대분류 목록을 카운트한다.
    @Test
    public void CountTest2() throws Exception{
        assertTrue("qnaCategoryDao is Null",qnaCategoryDao !=null);

        int cnt = qnaCategoryDao.MajorCount();
        System.out.println(cnt);
        assertTrue(cnt ==5);
    }
    //카테고리 대분류를 조회한다.
    @Test
    public void selectMajorCategoryTest()throws Exception{
        //Dao 주입 확인한다.
        assertTrue("qnaCategoryDao is Null",qnaCategoryDao !=null);

        System.out.println("qnaCategoryDao = " + qnaCategoryDao);
        //대분류를 조회하여 List에 담는다.
        List<QnaCategoryDto> QnaCategoryDtoList = qnaCategoryDao.selectMajorCategory();
        System.out.println(QnaCategoryDtoList);

        assertNotNull(QnaCategoryDtoList.isEmpty());
    }

    //카테고리 중분류를 조회한다.
    @Test
    public void selectSubCategory()throws Exception{
        assertTrue("qnaCategoryDao is Null",qnaCategoryDao !=null);

        System.out.println("qnaCategoryDao = " + qnaCategoryDao);

        //대분류 카테고리 1에 대한 중분류를 QnaCategoryDtoList에 넣는다.
        List<QnaCategoryDto> QnaCategoryDtoList = qnaCategoryDao.selectSubCategory(1);
        System.out.println(QnaCategoryDtoList);

        //List에 값이 비어있지 않은지 확인한다.
        assertNotNull(QnaCategoryDtoList.isEmpty());

        //현재 들어있는 size와 같은지 확인한다.
        int cnt = QnaCategoryDtoList.size();
        System.out.println(cnt);
        assertTrue(cnt ==2);
    }

    // 유효성 테스트
    @Test
    public void testSelectSubCategoryInvalidQueCd()throws Exception {
        assertTrue("qnaCategoryDao is Null",qnaCategoryDao !=null);

        // 예상치 못한 값과 존재하지 않는 코드 포함한다.
        int[] invalidQueCdValues = {-1, 0, 9999};
        for (int que_cd : invalidQueCdValues) {
            List<QnaCategoryDto> results = qnaCategoryDao.selectSubCategory(que_cd);
            assertTrue("결과가 비어 있어야 합니다.", results.isEmpty()); // 유효하지 않은 입력에 대해 비어 있는 리스트가 반환되어야 함
        }
    }
}
