import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team.cheese.dao.EventDaoImp;
import team.cheese.domain.EventDto;
import team.cheese.service.EventServiceImp;

import java.io.IOException;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class eventdaotest {
    @Autowired
    EventDaoImp eventDao;

    @Autowired
    EventServiceImp eventService;

    @Test
    public void eventinsert() throws IOException {
        EventDto edto = new EventDto();
        edto.setEvt_cd("E");
        edto.setActive_s_cd("F");
        edto.setTitle("이벤트1");
        edto.setContents("이벤트11");
        edto.setS_date(new Date(2024/02/01));
        edto.setE_date(new Date(2024/02/01));
        edto.setGroup_no(1);
        edto.setImg_full_rt("/Users/MY/Desktop/image/basket.jpg");
        edto.setPrize("도서 상품권");
        edto.setAd_id("ghkdwjdgk");
        edto.setFirst_id("ghkdwjdgk");
        edto.setLast_id("ghkdwjdgk");

//        assertTrue(eventService.eventRegister(edto, "asd.jpg") == 1);

//        EventDto edto1 = new EventDto("E","F","이벤트 1", "상품권 이벤트", new Date(2024/02/01), new Date(2024/02/30),1,"/Users/MY/Desktop/image/basket.jpg", "도서 상품권", "ghkdwjdgk", "ghkdwjdgk", "ghkdwjdgk");
        assertTrue(eventDao.eventineset(edto) == 1);
    }
}
