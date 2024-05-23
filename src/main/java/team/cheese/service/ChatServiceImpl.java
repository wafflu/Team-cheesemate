package team.cheese.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import team.cheese.dao.ChatDao;
import team.cheese.domain.ChatMessageDto;
import team.cheese.domain.ChatRoomDto;
import team.cheese.domain.ProfileimgDto;
import team.cheese.domain.SaleDto;
import team.cheese.domain.MyPage.UserInfoDTO;
import team.cheese.service.MyPage.UserInfoService;

import java.util.ArrayList;
import java.util.Iterator;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatDao chatDao;

    @Autowired
    UserInfoService userInfoService;

    @Override
    public ResponseEntity<ArrayList<ProfileimgDto>> loadChatroom(String acid) throws Exception {
        ArrayList<ChatRoomDto> ChatRoomlist = (ArrayList<ChatRoomDto>) chatDao.select(acid);

        ArrayList<ProfileimgDto> profile = new ArrayList<>();

        Iterator it = ChatRoomlist.iterator();

        while (it.hasNext()){
            ChatRoomDto room = (ChatRoomDto) it.next();
            UserInfoDTO user = userInfoService.read(room.getSeller_id());
            profile.add(new ProfileimgDto(room.getNo(), user.getUr_id(), user.getNick(), user.getImg_full_rt()));
        }

        if(profile == null){
            throw new Exception("채팅방 로딩 오류");
        }

        log.info(""+profile.size());

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @Override
    public Long checkChat(SaleDto sdto){
//        log.info("체크 방번호 : "+sdto.getNo()+" | "+sdto.getBuyer_id());
        ChatRoomDto cdto = chatDao.select(sdto);
//        log.info("cdto : "+(cdto == null));
        Long no = 0L;
        if(cdto == null){
            chatDao.insert(sdto);
            no = sdto.getNo();
            return no;
        }
        no = cdto.getNo();
        return no;
    }

    @Override
    public ResponseEntity<String> savemessage(ChatMessageDto cmto){
        chatDao.insert(cmto);
        return new ResponseEntity<>("성공", HttpStatus.OK);
    }

    @Override
    public ArrayList<ChatMessageDto> loadMessage(int no){
        ArrayList<ChatMessageDto> msglist = (ArrayList<ChatMessageDto>) chatDao.select(no);
        if(msglist.size() == 0){
            msglist.add(null);
        }
        return msglist;
    }

}
