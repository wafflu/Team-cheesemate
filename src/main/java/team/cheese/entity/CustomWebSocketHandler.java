package team.cheese.entity;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CustomWebSocketHandler extends TextWebSocketHandler {
    private final AtomicInteger connectionCount = new AtomicInteger(0);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        connectionCount.incrementAndGet(); // 클라이언트 연결 수 증가
        System.out.println("클라이언트 연결 수: " + connectionCount.get());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        connectionCount.decrementAndGet(); // 클라이언트 연결 수 감소
        System.out.println("클라이언트 연결 수: " + connectionCount.get());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터의 메시지 처리
        super.handleTextMessage(session, message);
    }
}

