package team.cheese.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.dto.eventDto;

import java.util.ArrayList;

@Repository
public class eventDao {
    @Autowired
    private SqlSession session;

    private static String namespace  = "team.cheese.dao.event.";
    public int count(){
        return session.selectOne(namespace + "count");
    }
    public ArrayList<eventDto> selectAll(){
        ArrayList<eventDto> arr = new ArrayList<>(session.selectList(namespace+"selectAll"));
        return arr;
    }
}
