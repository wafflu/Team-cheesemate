package team.cheese.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.ChatMessageDto;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Slf4j
public class ChatController {
    @GetMapping("/chat2")
    public void chat(Model model) {
        log.info("채팅방 입장");
    }

    @MessageMapping("/chat/{roomid}")
    @SendTo("/topic/messages/{roomid}")
    public ChatMessageDto send(ChatMessageDto cmdt, @DestinationVariable int roomid) throws Exception {
        log.info(""+roomid);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ChatMessageDto(cmdt.getNick(), cmdt.getMessage(), time);
    }

}
