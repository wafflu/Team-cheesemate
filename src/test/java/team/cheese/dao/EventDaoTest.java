package team.cheese.dao;

import team.cheese.Dao.Event.eventDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.Service.Event.eventService;
import team.cheese.Domain.Event.eventDto;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class EventDaoTest {
    @Autowired
    eventDao Dao;

    @Autowired
    eventService service;

    @Test
    public void countTest() throws Exception {
        System.out.println(Dao.count());
    }

    @Test
    public void selectTest() throws Exception {
        ArrayList<eventDto> arr= service.getDtolist();
        for(eventDto i : arr) {
            System.out.println(i.getEvt_no());
            System.out.println(i.getContents());
            System.out.println(i.getPrize());

        }
    }

}
