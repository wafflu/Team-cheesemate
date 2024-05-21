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

    // *** 모든 행정구역을 리스트로 불러오는지 테스트 ***
    @Test
    public void testGetAllAdministrative() throws Exception {
        List<AdministrativeDto> list = administrativedao.selectAll();

        for(AdministrativeDto dto : list){
            System.out.println(dto.getAddr_name());
        }

        System.out.println(list.size());
        assertTrue(list.size() == 3498);
    }

    // *** 대분류만 가져오는지 테스트 ***
    @Test
    public void testSelectLargeCategory() throws Exception {
        List<AdministrativeDto> list = administrativedao.selectLargeCategory();

        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).getAddr_name());
        }

        assertTrue(list != null);
    }

    // *** 중분류만 가져오는지 테스트 ***
    @Test
    public void testSelectMiddleCategory() throws Exception {
        List<AdministrativeDto> majorList = administrativedao.selectLargeCategory();

        // 랜덤 대분류 선택
        AdministrativeDto randomAddrCd = majorList.get((int)(Math.random()* majorList.size()));
        System.out.println("대분류 : " + randomAddrCd.getAddr_name());

        // 대분류의 중분류 가져오기
        List<AdministrativeDto> middleList = administrativedao.selectMediumCategory(randomAddrCd.getAddr_cd());

        for(int i=0; i<middleList.size(); i++){
            System.out.println(middleList.get(i).getAddr_name());
        }

        assertTrue(middleList != null);
    }

    // *** 소분류만 가져오는지 테스트 ***
    @Test
    public void testSelectSmallCategory() throws Exception {
        // 1. 대분류 가져오고 랜덤한 한개 선택
        List<AdministrativeDto> largeCategoryList = administrativedao.selectLargeCategory();
        AdministrativeDto randomLargeCategory = largeCategoryList.get((int)(Math.random()* largeCategoryList.size()));
        System.out.println("대분류 addr_cd : " + randomLargeCategory.getAddr_cd());
        System.out.println("대분류 addr_name : " + randomLargeCategory.getAddr_name());
        System.out.println("--------------------");

        List<AdministrativeDto> mediumCategoryList = administrativedao.selectMediumCategory(randomLargeCategory.getAddr_cd());
        AdministrativeDto randomMediumCategory = mediumCategoryList.get((int)(Math.random()* mediumCategoryList.size()));
        System.out.println("중분류 addr_cd : " + randomMediumCategory.getAddr_cd());
        System.out.println("중분류 addr_name : " + randomMediumCategory.getAddr_name());
        System.out.println("--------------------");

        List<AdministrativeDto> smallCategoryList = administrativedao.selectSmallCategory(randomMediumCategory.getAddr_cd());
        for(int i=0; i<smallCategoryList.size(); i++){
            System.out.println("소분류 addr_cd : " + smallCategoryList.get(i).getAddr_cd());
            System.out.println("소분류 addr_name : " + smallCategoryList.get(i).getAddr_name());
        }

        assertTrue(smallCategoryList != null);
    }

    // *** addr_cd로 해당 AddrCdDto를 가져오는지 테스트 ***
    @Test
    public void testSelectAddrCdByAddrCd() throws Exception {
        List<AdministrativeDto> largeCategoryList = administrativedao.selectLargeCategory();
        AdministrativeDto randomLargeCategory = largeCategoryList.get((int)(Math.random()* largeCategoryList.size()));
        System.out.println("대분류 addr_cd : " + randomLargeCategory.getAddr_cd());
        System.out.println("대분류 addr_name : " + randomLargeCategory.getAddr_name());
        System.out.println("--------------------");

        List<AdministrativeDto> mediumCategoryList = administrativedao.selectMediumCategory(randomLargeCategory.getAddr_cd());

        for(int i=0; i<mediumCategoryList.size(); i++){
            System.out.println("중분류 addr_cd : " + mediumCategoryList.get(i).getAddr_cd());
            System.out.println("중분류 addr_name : " + mediumCategoryList.get(i).getAddr_name());
        }
        System.out.println("--------------------");


        AdministrativeDto randomMediumCategory = mediumCategoryList.get((int)(Math.random()* mediumCategoryList.size()));
        System.out.println("중분류 addr_cd : " + randomMediumCategory.getAddr_cd());
        System.out.println("중분류 addr_name : " + randomMediumCategory.getAddr_name());
        System.out.println("--------------------");

        List<AdministrativeDto> smallCategoryList = administrativedao.selectSmallCategory(randomMediumCategory.getAddr_cd());

        for(int i=0; i<mediumCategoryList.size(); i++){
            System.out.println("중분류 addr_cd : " + smallCategoryList.get(i).getAddr_cd());
            System.out.println("중분류 addr_name : " + smallCategoryList.get(i).getAddr_name());
        }
        System.out.println("--------------------");

        AdministrativeDto randomSmallCategory = smallCategoryList.get((int)(Math.random()* smallCategoryList.size()));
        System.out.println("소분류 addr_cd : " + randomSmallCategory.getAddr_cd());
        System.out.println("소분류 addr_name : " + randomSmallCategory.getAddr_name());
        System.out.println("--------------------");

        AdministrativeDto search = administrativedao.selectAddrCdByAddrCd(randomSmallCategory.getAddr_cd());
        System.out.println("검색한 addr_cd : " + search.getAddr_cd());
        System.out.println("검색한 addr_name : " + search.getAddr_name());
    }
}