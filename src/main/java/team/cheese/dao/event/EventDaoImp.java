package team.cheese.dao.event;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.event.EventDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventDaoImp implements EventDao {
    @Autowired
    private SqlSession session;

    private static String namespace  = "team.cheese.dao.event.EventDao.";
    @Override
    public int count(String nowcd){
        return session.selectOne(namespace + "count", nowcd);
    }
    @Override
    public ArrayList<EventDto> selectAll(){
        ArrayList<EventDto> arr = new ArrayList<>(session.selectList(namespace+"selectAll"));
        return arr;
    }
    @Override
    public ArrayList<EventDto> selectAllcd(String nowcd){
        ArrayList<EventDto> arr = new ArrayList<>(session.selectList(namespace+"selectAllcd", nowcd));
        return arr;
    }
    @Override
    public int searchCount(Map<String,String> map){
        return session.selectOne(namespace+"searchCount", map);
    }
    @Override
    public ArrayList<EventDto> selectPage(int startnum, String cd, int maxcontents){
        Map map = new HashMap();
        map.put("startNum", startnum);
        map.put("cd", cd);
        map.put("maxContents", maxcontents);
        ArrayList<EventDto> arr = new ArrayList<>(session.selectList(namespace + "selectPage", map));
        return arr;
    }
    @Override
    public int insert(EventDto dto) {
        return session.insert(namespace + "insert", dto);
    }
    @Override
    public EventDto selectContent(Long evt_no) {
        return session.selectOne(namespace+"selectContent", evt_no);
    }

    @Override
    public int updateContent(EventDto dto) {
        return session.update(namespace + "updateContent", dto);
    }

    @Override
    public int updatestate(EventDto dto){
        return session.update(namespace + "updateState", dto);
    }
    @Override
    public List<EventDto> selectSearch(Map<String,String> map){
        List<EventDto> arr =session.selectList(namespace+"selectSearch", map);
        return arr;
    }
    @Override
    public  int deleteAll(){
        return session.delete(namespace+"deleteAll");
    }
    @Override
    public void autoIncreaseReset(){
        session.update(namespace+"autoIncreasereset");
    }
}
