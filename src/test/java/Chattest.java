import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.ChatDaoImpl;
import team.cheese.domain.ChatMessageDto;
import team.cheese.domain.ChatRoomDto;
import team.cheese.domain.SaleDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
@Slf4j
public class Chattest {
    @Autowired
    ChatDaoImpl chatDao;

    //채팅방 생성
    @Test
    public void CreateChatroom(){
        ChatRoomDto cr = new ChatRoomDto();
        cr.setSale_no(521L);
        cr.setSeller_id("user1");
        cr.setSeller_nk("둘리");
        cr.setBuyer_id("user2");
        cr.setBuyer_nk("또치");
//        assertTrue(chatDao.insert(cr) == 1);
    }

    //여러개

    //채팅메세지
    @Test
    public void CreateChatrooms(){
        ChatMessageDto cmsg = new ChatMessageDto();
        cmsg.setCr_no(1L);
        cmsg.setSale_no(520L);
        cmsg.setAcid("user1");
        cmsg.setNick("둘리");
        cmsg.setMessage("삽니다 사요");
        assertTrue(chatDao.insert(cmsg) == 1);
    }

    //채팅방 목록조회
    @Test
    public void SelectChatRoomList(){
        ArrayList<ChatRoomDto> list = (ArrayList<ChatRoomDto>) chatDao.select("user1");
        assertTrue(list != null);

        Iterator it = list.iterator();
        while (it.hasNext()){
            ChatRoomDto cr = (ChatRoomDto) it.next();
            System.out.println(cr.getSale_no()+cr.getNo()+"번방 Seller : "+cr.getSeller_id()+" SellerNick : "+cr.getSeller_nk()+" BuyerId : "+cr.getBuyer_id()+" BuyerNick : "+cr.getBuyer_nk());
        }
    }

    //채팅방 로그
    @Test
    public void SelectChatLog(){
        ArrayList<ChatMessageDto> list = (ArrayList<ChatMessageDto>) chatDao.select(1);

        Iterator it = list.iterator();
        while (it.hasNext()){
            ChatMessageDto cms = (ChatMessageDto) it.next();
            System.out.println(cms.getSale_no()+cms.getCr_no()+"번방 acid : "+cms.getAcid()+" nick : "+cms.getNick()+" message : "+cms.getMessage()+" date : "+cms.getR_date());
        }
    }

    //이미 채팅에 참여하고 있는지 체크
    @Test
    public void SelectBuyer(){

        SaleDto sdto = new SaleDto();
        sdto.setNo(523L);
        sdto.setBuyer_id("user123");
        ChatRoomDto crto = chatDao.select(sdto);
        assertTrue(crto != null);
    }


}
