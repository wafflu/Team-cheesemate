package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.ChatMessageDto;
import team.cheese.domain.ChatRoomDto;
import team.cheese.domain.SaleDto;

import java.util.List;

@Repository
public class ChatDaoImpl implements ChatDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.Chat.";

    @Override
    public int insert(SaleDto sdto){
        return session.insert(namespace+"insert_chatroom", sdto);
    }

    @Override
    public int insert(ChatMessageDto cmsg){
        return session.insert(namespace+"insert_chatmsg", cmsg);
    }

    @Override
    public List<ChatRoomDto> select(String acid){
        return session.selectList(namespace+"select_chatlist", acid);
    }
    @Override
    public List<ChatMessageDto> select(int no){
        return session.selectList(namespace+"select_chatmsglog", no);
    }
    @Override
    public ChatRoomDto select(SaleDto sdto){
        return session.selectOne(namespace+"select_buyer", sdto);
    }
    @Override
    public List<ChatRoomDto> select(long saleno){
        return session.selectList(namespace+"select_salelist", saleno);
    }
}
