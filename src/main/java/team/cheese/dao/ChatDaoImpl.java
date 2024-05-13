package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class ChatDaoImpl {

    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.Chat.";

}
