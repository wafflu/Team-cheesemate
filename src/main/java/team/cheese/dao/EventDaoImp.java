package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.EventDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventDaoImp {
    @Autowired
    private SqlSession session;

    private static String namespace  = "team.cheese.dao.EventDaoImp.";

    public int eventineset(EventDto edto){
        return session.insert(namespace+"insertEvent", edto);
    }

    public int insert(EventDto edto){
        return session.insert(namespace+"insert", edto);
    }
}
