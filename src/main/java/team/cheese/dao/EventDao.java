package team.cheese.dao;

import team.cheese.domain.EventDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface EventDao {
    public int count(String nowcd);
    public ArrayList<EventDto> selectAll();
    public ArrayList<EventDto> selectAllcd(String nowcd);
    public ArrayList<EventDto> selectPage(int startnum, String cd,  int maxcontents);
    public int searchCount(Map<String,String> map);
    public int insert(EventDto dto);
    public List<EventDto> selectSearch(Map<String,String> map);
    public EventDto selectContent(Long evt_no);
    public int updatecontent(EventDto dto);
    public int updatestate(EventDto dto);
    public  int deleteAll();
    public void autoIncreaseReset();
}
