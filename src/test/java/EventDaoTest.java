import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.EventDao;
import team.cheese.domain.EventDto;
import team.cheese.domain.EventPageHanddler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class EventDaoTest {
    @Autowired()
    EventDao eventDao;



    EventDto dto1 = new EventDto("E","F","이벤트 1", "상품권 이벤트", new Date(2024/02/01), new Date(2024/02/30),1,"src", "도서 상품권", "ghkdwjdgk", "ghkdwjdgk", "ghkdwjdgk");
    EventDto dto2 = new EventDto("A","C","이벤트 2", "치즈마켓 상품권 추첨 이벤트", new Date(2024/04/30), new Date(2024/05/20),1, "src","신세계 상품권", "ghkdwjdgk", "ghkdwjdgk", "ghkdwjdgk");
    EventDto dto3 = new EventDto("N","P","이벤트 3", "치즈마켓 신규유저 환영 이벤트", new Date(2024/05/1), new Date(2024/06/05),1,"src", "문화상품권", "ghkdwjdgk", "ghkdwjdgk", "ghkdwjdgk");
    EventDto dto4 = new EventDto("이벤트 4", "스타벅스 기프티콘 이벤트", "이벤트 관리자", new Date(2024/03/01), new Date(2024/03/30),"A", "스타벅스 기프티콘");
    EventDto dto5 = new EventDto("이벤트 5", "치즈 마켓 장바구니 증정 이벤트", "이벤트 관리자", new Date(2024/04/30), new Date(2024/05/07), "N", "치즈마켓 장바구니");
    EventDto dto6 = new EventDto("이벤트 6", "치즈 마켓 머그컵 증정 이벤트", "이벤트 관리자", new Date(2024/04/30), new Date(2024/05/30), "R", "치즈마켓머그컵");
    ArrayList<EventDto> dtoList = new ArrayList<>();

    public EventDaoTest(){
        dtoList.add(dto1);
        dtoList.add(dto2);
        dtoList.add(dto3);
        dtoList.add(dto4);
        dtoList.add(dto5);
        dtoList.add(dto6);
    }
    @Before
    public void setup() throws  Exception {
        eventDao.deleteAll();
        assertTrue(eventDao.count("")==0);
        eventDao.autoIncreaseReset();
        for(int i = 0 ; i < 100 ; i++) {
            for(EventDto dto : dtoList) {
                assertTrue(1 == eventDao.insert(dto));
            }
        }
    }

    @Test
    public void countTest() throws Exception {
        String nowcd = "C";
        int C = eventDao.count(nowcd);
        assertTrue(C >= 0);
        nowcd = "P";
        int P = eventDao.count(nowcd);
        assertTrue(P >= 0);
        nowcd = "F";
        int F = eventDao.count(nowcd);
        assertTrue(F >= 0);
        nowcd = "";
        int all = eventDao.count(nowcd);
        assertTrue(all >= 0);
        assertTrue(all==C+P+F);
        System.out.printf("all:%d, C:%d, P: %d, F: %d",all,C,P,F);
    }
    @Test
    public void selectPPageTest() throws  Exception{
        int pageSize = 8;
        int maxnum = eventDao.count("P");
        int nowpage = (int)(Math.random()*(maxnum/8)+1);
        EventPageHanddler ph = new EventPageHanddler(maxnum,nowpage);
        ph.setMAXCONTENT(pageSize);
        ArrayList<EventDto> arr = eventDao.selectPage(ph.getStartBno(),"P",pageSize);
        assertTrue(pageSize== arr.size());
        for(int i=ph.getStartBno(); i<ph.getStartBno()-pageSize; i--){
            assertTrue(dtoList.get(i % 4 +((( i / 4 ) * 6))).equals(arr.get(i)));
        }
    }
    @Test
    public void selectALLPageTest() throws  Exception{
        int pageSize = 8;
        int maxnum = eventDao.count("");
        int nowpage = (int)(Math.random()*(maxnum/8)+1);
        EventPageHanddler ph = new EventPageHanddler(maxnum,nowpage);
        ph.setMAXCONTENT(pageSize);
        ArrayList<EventDto> arr = eventDao.selectPage(ph.getStartBno(),"P",pageSize);
        assertTrue(pageSize== arr.size());
        for(int i=ph.getStartBno(); i<ph.getStartBno()-pageSize; i--){
            assertTrue(dtoList.get(i % 6).equals(arr.get(i)));
        }
    }
//    @Test
//    public void insertTest() throws Exception {
//        eventDao.deleteAll();
//        assertTrue(eventDao.count("")==0);
//        eventDao.autoIncreaseReset();
//        for(int i = 0 ; i < 100 ; i++) {
//            for(EventDto dto : dtoList) {
//                assertTrue(1 == eventDao.insert(dto));
//            }
//        }
//        assertTrue(eventDao.count("") == 600);
//        assertTrue(eventDao.count("P")==400);
//        assertTrue(eventDao.count("F")==100);
//        assertTrue(eventDao.count("c")==100);
//
//        for(int i = 0 ; i < 10 ; i++) {
//            int r = ((int) (Math.random() * eventDao.count("")))+ 1;
//            assertTrue(dtoList.get((r-1)% dtoList.size()).equals(eventDao.selectContent(Long.valueOf(r))));
//        }
//    }
//
    @Test
    public void selectContentsTest() throws Exception {
        System.out.println(eventDao.selectContent(Long.valueOf(1)).getEvt_cd());

        assertTrue(eventDao.selectContent(Long.valueOf(1)).getEvt_cd().equals("E"));
//        assertTrue(eventDao.selectcontent(1).getActive_s_cd()=="F");
//        assertTrue(eventDao.selectcontent(1).getTitle() == "이벤트 1");
    }
//    @Test
//    public void updateTest() throws Exception {
//        EventDto dto = new EventDto();
//        dto.setActive_s_cd("F");
//        dto.setAd_id("ghkdwjdgk");
//        dto.setEvt_no(Long.valueOf(720));
//        service.changeState(dto);
//        service.getcontent(dto.getEvt_no()).getEvt_cd();
//        System.out.println(service.getcontent(dto.getEvt_no()));
//        assertTrue("F"==service.getcontent(dto.getEvt_no()).getActive_s_cd());
//    }

    @Test
    public void searchDaoTest() throws Exception {
        Map map = new HashMap();
        map.put("searchCd", "title");
        map.put("searchContent", "2월");
    }
    @Test
    public void searchDaoTest2() throws Exception {
        Map map = new HashMap();
        map.put("searchCd", "title");
        map.put("searchContent", "신규");
        map.put("startnum", 1);
        System.out.println(eventDao.selectSearch(map));
    }

    @Test
    public void updateStateTest(){
        EventDto dto=eventDao.selectContent(Long.valueOf(1));
        dto.setEvt_cd("E");
        eventDao.updatestate(dto);
        EventDto dto1=eventDao.selectContent(Long.valueOf(1));
        assertTrue(dto.equals(dto1));
        assertTrue(dto1.getEvt_cd().equals("E"));
    }
}
