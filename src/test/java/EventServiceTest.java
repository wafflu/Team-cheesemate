import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.event.EventDao;
import team.cheese.domain.event.EventDto;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class EventServiceTest {
    @Autowired
    EventDao eventDao;

    @Test
    public void insert(){
        EventDto edto = new EventDto();
        edto.setEvt_cd("N");
        edto.setActive_s_cd("P");
        edto.setS_date(new Date(2024,05,20));
        edto.setE_date(new Date(2024,05,20));
        edto.setImg_full_rt("eventtest.png");
        edto.setPrize("asd");
        edto.setAd_id("admin001");
        edto.setFirst_id("admin");
        edto.setLast_id("admin");
        for(int i = 0; i<30; i++){
            edto.setGroup_no(i);
            edto.setTitle("TestEvent : "+i);
            edto.setContents("TestEvent : "+i);
            eventDao.insert(edto);
        }


    }
}
