package team.cheese.Service.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.Dao.Event.eventDao;
import team.cheese.Domain.Event.eventDto;

import java.util.ArrayList;

@Service
public class eventService {
    @Autowired
    eventDao dao;

    public ArrayList<eventDto> getDtolist(){
        ArrayList<eventDto> dtolist = new ArrayList<>(dao.selectAll());
        return dtolist;
    }
    public int count(){
        return dao.count();
    }

}
