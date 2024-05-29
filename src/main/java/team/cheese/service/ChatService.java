package team.cheese.service;

import org.springframework.http.ResponseEntity;
import team.cheese.domain.ChatMessageDto;
import team.cheese.domain.ChatRoomDto;
import team.cheese.domain.MyPage.UserInfoDTO;
import team.cheese.domain.ProfileimgDto;
import team.cheese.domain.SaleDto;

import java.util.ArrayList;

public interface ChatService {
    ResponseEntity<ArrayList<ProfileimgDto>> loadChatroom(String acid) throws Exception;

    Long checkChat(SaleDto sdto);

    ResponseEntity<String> savemessage(ChatMessageDto cmto);

    ArrayList<ChatMessageDto> loadMessage(int no) throws Exception;

    ArrayList<UserInfoDTO> loadChatlist(Long saleno) throws Exception;
}
