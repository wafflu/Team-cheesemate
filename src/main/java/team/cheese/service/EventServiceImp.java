package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.dao.EventDaoImp;
import team.cheese.domain.EventDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventServiceImp implements EventService {
    @Autowired
    EventDaoImp dao;
    @Override
    public ArrayList<EventDto> getAlllist(){
        ArrayList<EventDto> dtolist = new ArrayList<>(dao.selectAll());
        return dtolist;
    }
    @Override
    public int count(String nowcd){
        return dao.count(nowcd);
    }
    @Override
    public int searchCount(String cd, String contents){
        Map map = new HashMap();
        map.put("searchContent", contents);
        map.put("searchCd", cd);
        return dao.searchCount(map);
    }
    @Override
    public  ArrayList<EventDto> getPageList(int startnum, String cd, int maxcontents){
        ArrayList<EventDto> dtolist = new ArrayList<>(dao.selectPage(startnum, cd, maxcontents));
        return dtolist;
    }
    @Override
    public List<EventDto> getSearchList(String cd, String contents, int startnum){
        Map map = new HashMap();
        map.put("searchCd", cd);
        map.put("searchContent", contents);
        map.put("startnum", startnum);
        List<EventDto> arr = dao.selectSearch(map);
        return arr;
    }
    @Override
    public int eventRegister(EventDto dto){
        int result=dao.insert(dto);
        return result;
    }
    @Override
    public EventDto getContent(Long evt_no){
        return dao.selectContent(evt_no);
    }
    @Override
    public int updateContent(EventDto dto){
        int result=dao.updatecontent(dto);
        return result;
    }
    @Override
    public int changeState(EventDto dto){
        return dao.updatestate(dto);
    }
}