package team.cheese.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.domain.SaleCategoryDto;
import team.cheese.domain.SaleDto;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class SaleCategoryDaoImplTest extends TestCase {
    @Autowired
    SaleCategoryDao salecategorydao;

    @Test
    public void count() throws Exception {
        // sale테이블에 게시글이 몇개 들어있는지 확인하는 테스트
        System.out.println("count : " + salecategorydao.count());
        int cnt = salecategorydao.count();
        assertTrue(cnt == 1370);
    }

    @Test
    public void testSelectAll() throws Exception {
        // 전체 카테고리를 받아오는지 테스트
        System.out.println("selectAll : " + salecategorydao.selectAll());
        List<SaleCategoryDto> list = salecategorydao.selectAll();
        System.out.println(list.size());
        assertTrue(list.size() == 763);
        System.out.println(list.get(0));
    }

    @Test
    public void testSelectCategory1() throws Exception {
        // 대분류 카테고리를 받아오는지 테스트 (첫번째 카테고리 "여성의류")
        System.out.println("selectCategory1 : " + salecategorydao.selectCategory1());
        List<SaleCategoryDto> list = salecategorydao.selectCategory1();
        assertTrue(list.get(0).getName().equals("여성의류"));
    }

    @Test
    public void testSelectCategory1_6() throws Exception {
        // 대분류 카테고리를 받아오는지 테스트 (여섯번째 카테고리 "쥬얼리")
        System.out.println("selectCategory1 : " + salecategorydao.selectCategory1());
        List<SaleCategoryDto> list = salecategorydao.selectCategory1();
        System.out.println(list.get(5).getName());
        System.out.println(list.get(5).getSal_cd());
        assertTrue(list.get(5).getName().equals("쥬얼리"));
    }

    @Test
    public void testSelectCategory1_8() throws Exception {
//         대분류 카테고리를 받아오는지 테스트 (열번째 카테고리 "스포츠/레저")
        System.out.println("selectCategory1 : " + salecategorydao.selectCategory1());
        List<SaleCategoryDto> list = salecategorydao.selectCategory1();
        System.out.println(list.get(9).getName());
        System.out.println(list.get(9).getSal_cd());
        assertTrue(list.get(9).getName().equals("스포츠/레저"));
    }

    @Test
    public void testSelectCategory1_last() throws Exception {
//         대분류 카테고리를 받아오는지 테스트 (마지막 카테고리 "기타")
        System.out.println("selectCategory1 : " + salecategorydao.selectCategory1());
        List<SaleCategoryDto> list = salecategorydao.selectCategory1();
        System.out.println(list.get(list.size()-1).getName());
        System.out.println(list.get(list.size()-1).getSal_cd());
        assertTrue(list.get(list.size()-1).getName().equals("기타"));
    }
}