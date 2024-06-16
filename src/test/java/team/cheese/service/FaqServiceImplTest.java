package team.cheese.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import team.cheese.dao.FaqDao;
import team.cheese.domain.FaqDto;

import java.util.*;
import java.util.stream.Collectors;

/*
1.전체 카테고리 검색
 1-1.메서드를 호출해서 전체 카데고리가 예상대로 반환되는지 확인한다.
 1-2.반환된 리스트의 크기가 예상한 값과 같은지 확인한다.
 1-3.반환된 리스트에 예상한 항목이 모두 포함되어 있는지 확인
*/

public class FaqServiceImplTest {
    // FaqDao 모의 객체 생성
    @Mock
    private FaqDao faqDao;

    // 서비스 계층의 인스턴스 생성 및 모킹된 DAO 주입
    @InjectMocks
    private FaqServiceImpl faqService;

    // Mockito 초기화
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // 메서드를 호출해서 전체 카데고리가 예상대로 반환되는지 확인한다. (2개)
    @Test
    public void testGetList1() throws Exception {

        //객체 faq1,과 faq2를 생성한다.
        FaqDto faq1 = new FaqDto();
        faq1.setQue_i_cd(1);
//        System.out.println("faq1: "+faq1);
        FaqDto faq2 = new FaqDto();
        faq2.setQue_i_cd(1);
//        System.out.println("faq2: "+faq2);


        List<FaqDto> faqs = Arrays.asList(faq1, faq2);
        //selectAllFaq()가 호출되면 expectedFaqs를 반환한다.
        when(faqDao.selectAllFaq()).thenReturn(faqs);

        // 서비스 getList메소드 실행하여 actualFaqs에 대입한다.
        List<FaqDto> actualFaqs = faqService.getList();
        System.out.println("actualFaqs:"+actualFaqs);

        assertNotNull("null이면 안 됨", actualFaqs);
        assertEquals("목록의 크기가 일치되어야 함", faqs.size(), actualFaqs.size());
        assertTrue("모든 항목이 포함되어 있어야 함", actualFaqs.containsAll(faqs));

        // DAO 호출 검증
        verify(faqDao).selectAllFaq();
    }

    // 메서드를 호출해서 전체 카데고리가 예상대로 반환되는지 확인한다. (200개)
    @Test
    public void testGetList2() throws Exception {

        List<FaqDto> faqs = new ArrayList<>();
        for (int i=0; i<100; i++){
            FaqDto faq = new FaqDto();
            faq.setQue_i_cd(1);
            faqs.add(faq);
        }
        for (int i=0; i<100; i++){
            FaqDto faq = new FaqDto();
            faq.setQue_i_cd(2);
            faqs.add(faq);
        }

        //selectAllFaq()가 호출되면 expectedFaqs를 반환한다.
        when(faqDao.selectAllFaq()).thenReturn(faqs);

        // 서비스 getList메소드 실행하여 actualFaqs에 대입한다.
        List<FaqDto> actualFaqs = faqService.getList();

        assertNotNull("null이면 안 됨", actualFaqs);
        assertEquals("목록의 크기가 일치되어야 함", faqs.size(), actualFaqs.size());
        assertTrue("모든 항목이 포함되어 있어야 함", actualFaqs.containsAll(faqs));

        // DAO 호출 검증
        verify(faqDao).selectAllFaq();
    }

/*
2.부분 카테고리 검색
 2-1.특정 카테고리 id를 사용해서 하위 카테고리가 예상대로 반환되는지 확인
 2-2.반환된 하위 카테고리 리스트의 크기를 예상값과 같은지 확인
 2-3.반환된 하위 카테고리 리스트의 항목이 예상값과 같은지 확인
*/
    @Test
    public void testGetMajorFaqs() throws Exception {

        List<FaqDto> faqs =new ArrayList<>();
        List<FaqDto> filterFaqs = new ArrayList<>();

        for (int i=0; i<100; i++){
            FaqDto faq = new FaqDto();
            faq.setQue_i_cd(1);
            faqs.add(faq);
            filterFaqs.add(faq);
        }
        for (int i=0; i<10; i++){
            FaqDto faq = new FaqDto();
            faq.setQue_i_cd(2);
            faqs.add(faq);
        }
        for (int i=0; i<10; i++){
            FaqDto faq = new FaqDto();
            faq.setQue_i_cd(3);
            faqs.add(faq);
        }

        //selectMajorFaq()가 호출되면 expectedFaqs를 반환한다.
        when(faqDao.selectMajorFaq(1)).thenReturn(filterFaqs);

        // 서비스 getList메소드 실행하여 actualFaqs에 대입한다.
        List<FaqDto> actualFaqs = faqService.getMajorFaqs(1);

        assertNotNull("null이면 안 됨", actualFaqs);
        assertEquals("부분 카테고리 목록의 크기가 일치되어야 함", filterFaqs.size(), actualFaqs.size());
        System.out.println("faqs.size() = "+filterFaqs.size());
        System.out.println("actualFaqs.size() = "+actualFaqs.size());
        assertTrue("부분 카테고리의 모든 항목이 포함되어 있어야 함", actualFaqs.containsAll(filterFaqs));
    }

/*
3.검색
 3-1.검색어를 포함한 요청을 서비스에 전달하여 관련 faq가 반환되는지 확인
 3-2.반환된 faq 리스트 크기 확인
 3-3.반환된 faq 리스트 항목 확인
 3-4.검색어가 빈문자열 또는 null 일 때 동작 확인
 */
//
//    @Test
//    public void testSearchFaqs() {
//        // 검색할 FAQ 목록 생성
//        List<FaqDto> faqs = new ArrayList<>();
//
//        FaqDto faq1 = new FaqDto();
//        faq1.setTitle("치즈");
//        faq1.setContents("메이트");
//        faqs.add(faq1);
//
//        FaqDto faq2 = new FaqDto();
//        faq2.setTitle("메이트");
//        faq2.setContents("치즈");
//        faqs.add(faq2);
//
//        FaqDto faq3 = new FaqDto();
//        faq3.setTitle("정석코딩");
//        faq3.setContents("종각역");
//        faqs.add(faq3);
//
//        FaqDto faq4 = new FaqDto();
//        faq4.setTitle("패스트캠퍼스");
//        faq4.setContents("감남역");
//        faqs.add(faq4);
//
//        // 검색어 설정
//        String keyword = "치즈";
//        Map<String, Object> search = new HashMap<>();
//        search.put("keyword", keyword);
//        System.out.println(search);
//
//        // FaqDao의 searchFaqs 메서드가 호출되면 faqs를 반환하도록 설정
//        when(faqDao.searchFaqs(search)).thenReturn(faqs);
//
//        // 검색 메서드 호출
//        List<FaqDto> actualFaqs = faqService.searchFaqs(search);
//
//        // 검색 결과 확인
//        assertNotNull("결과 리스트는 null이면 안 됨", actualFaqs);
//        assertEquals("검색된 FAQ의 수는 예상과 일치해야 함", 2, actualFaqs.size());
//        assertTrue("FAQ 목록에 '치즈'를 제목 또는 내용으로 포함하는 FAQ가 포함되어야 함",
//                actualFaqs.stream().anyMatch(faq -> faq.getTitle().contains(keyword) || faq.getContents().contains(keyword)));
//    }
}
