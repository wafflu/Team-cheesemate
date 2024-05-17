package team.cheese.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import team.cheese.dao.ChatDaoImpl;
import team.cheese.domain.ChatMessageDto;
import team.cheese.domain.ChatRoomDto;
import team.cheese.domain.SaleDto;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

@Service
@Slf4j
public class ChatService {
    @Autowired
    ChatDaoImpl ChatDao;

    public ResponseEntity<ArrayList<ChatRoomDto>> loadChatroom(String acid){
        ArrayList<ChatRoomDto> ChatRoomlist = (ArrayList<ChatRoomDto>) ChatDao.select(acid);
        return new ResponseEntity<>(ChatRoomlist, HttpStatus.OK);
    }

    public Long checkChat(SaleDto sdto){
//        log.info("체크 방번호 : "+sdto.getNo()+" | "+sdto.getBuyer_id());
        ChatRoomDto cdto = ChatDao.select(sdto);
//        log.info("cdto : "+(cdto == null));
        Long no = 0L;
        if(cdto == null){
            ChatDao.insert(sdto);
            no = sdto.getNo();
            return no;
        }
        no = cdto.getNo();
        return no;
    }

    public ResponseEntity<String> savemessage(ChatMessageDto cmto){
        ChatDao.insert(cmto);
        return new ResponseEntity<>("성공", HttpStatus.OK);
    }

    public ArrayList<ChatMessageDto> loadMessage(int no){
        ArrayList<ChatMessageDto> msglist = (ArrayList<ChatMessageDto>) ChatDao.select(no);
        if(msglist.size() == 0){
            msglist.add(null);
        }
        return msglist;
    }

}
