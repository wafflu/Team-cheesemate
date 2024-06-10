package team.cheese.dao;

import team.cheese.domain.ChatMessageDto;
import team.cheese.domain.ChatRoomDto;
import team.cheese.domain.SaleDto;

import java.util.List;

public interface ChatDao {
    int insert(SaleDto sdto);

    int insert(ChatMessageDto cmsg);

    List<ChatRoomDto> select(String acid);

    List<ChatMessageDto> select(int no);

    ChatRoomDto select(SaleDto sdto);

    List<ChatRoomDto> select(long saleno);
}
