package team.cheese.Service;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.Dao.eventDao;
import team.cheese.dto.eventDto;

import java.lang.reflect.Array;
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
