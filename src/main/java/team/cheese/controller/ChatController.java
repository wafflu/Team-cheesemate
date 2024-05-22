package team.cheese.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.dao.SaleDao;
import team.cheese.domain.ChatMessageDto;
import team.cheese.domain.ChatRoomDto;
import team.cheese.domain.SaleDto;
import team.cheese.service.ChatService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@Slf4j
public class ChatController {
    @Autowired
    ChatService chatService;

    @Autowired
    SaleDao saleDao;
    
    @GetMapping("/chat2")
    public String chat(Model model, HttpSession session,@RequestParam(defaultValue = "0") int no) {
        //임시 테스트용
        if(session.getAttribute("userId") == null){
            return "redirect:/loginForm";
        }
//        String userid = (String) session.getAttribute("userId");
        String usernick = (String) session.getAttribute("userNick");

        model.addAttribute("roomid", no);
        model.addAttribute("usernick", usernick);
        log.info("채팅방 입장 : "+no);
        return "/chat2";
    }

    @PostMapping("/callchat")
    public String callchat(Model model, HttpSession session, Long sno, String id, String nick) {
        //임시 테스트용
        String userid = (String) session.getAttribute("userId");
        String usernick = (String) session.getAttribute("userNick");

        SaleDto sdto = new SaleDto();
        sdto.setNo(sno);
        sdto.setSeller_id(id);
        sdto.setSeller_nick(nick);
        sdto.setBuyer_id(userid);
        sdto.setBuyer_nick(usernick);
        sdto.setFirst_id(userid);
        sdto.setLast_id(userid);

        Long cr_no = chatService.checkChat(sdto);
        log.info("방 넘버 : "+cr_no);

        log.info("채팅생성");
        return "redirect:/chat2?no="+cr_no;
    }

    @RequestMapping(value = "/loadchatroom", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<ArrayList<ChatRoomDto>> Chatroomlist(HttpSession session) {
        String userid = (String) session.getAttribute("userId");
        return chatService.loadChatroom(userid);
    }

    @RequestMapping(value = "/savemessage", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> savemessage(@RequestBody ChatMessageDto cmto, HttpSession session) {
        String userid = (String) session.getAttribute("userId");
        cmto.setAcid(userid);
        cmto.setFirst_id(userid);
        cmto.setLast_id(userid);
        return chatService.savemessage(cmto);
    }

    @RequestMapping(value = "/loadchatmsg", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<ArrayList<ChatMessageDto>> loadchatmsg(@RequestParam("roomnum") int roomnum, HttpSession session) {
//        log.info("번호 체크 : "+roomnum);
        ArrayList<ChatMessageDto> msglist = chatService.loadMessage(roomnum);
        log.info("로그 사이즈 : "+msglist.size());
        return new ResponseEntity<>(msglist, HttpStatus.OK);
    }

    @MessageMapping("/chat/{roomid}")
    @SendTo("/topic/messages/{roomid}")
    public ChatMessageDto send(ChatMessageDto cmdt, @DestinationVariable int roomid) throws Exception {
        log.info("채팅방 번호 : "+roomid);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ChatMessageDto(cmdt.getNick(), cmdt.getMessage(), time);
    }
}
