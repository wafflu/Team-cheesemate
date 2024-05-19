package team.cheese.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import team.cheese.domain.ChatDto;

@Controller
public class ChatController {
    @GetMapping("/chating")
    public String chating() {
        return "chating";
    }

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/chat/{roomId}")
    public ChatDto sendMessage(@Payload ChatDto chatMessage, @DestinationVariable String roomId) {
        System.out.println(chatMessage);
        return chatMessage;

    }

    @MessageMapping("/addUser")
    @SendTo("/topic/chat")
    public ChatDto addUser(@Payload ChatDto chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in websocket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getNick());
        return chatMessage;
    }
}

