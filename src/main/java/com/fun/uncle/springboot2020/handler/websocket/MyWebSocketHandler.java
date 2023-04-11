package com.fun.uncle.springboot2020.handler.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/4/11 10:06
 * @Version: 1.0.0
 */
public class MyWebSocketHandler extends TextWebSocketHandler {

    private ConcurrentHashMap<String, HashSet<WebSocketSession>> sessionsMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("有新的长链接加入");
        super.afterConnectionEstablished(session);
        URI uri = session.getUri();
        String roomId = uri.getQuery().substring("roomId=".length());
        session.getAttributes().put("roomId", roomId);

        HashSet<WebSocketSession> sessionHashSet = sessionsMap.get(roomId);

        // 已经存在
        if (Objects.nonNull(sessionHashSet)) {
            sessionHashSet.add(session);
            return;
        }

        sessionHashSet = new HashSet<>();
        sessionHashSet.add(session);
        sessionsMap.put(roomId, sessionHashSet);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        String roomId = (String) session.getAttributes().get("roomId");
        HashSet<WebSocketSession> sessionHashSet = sessionsMap.get(roomId);
        sessionHashSet.remove(session);
        System.out.println("关闭链接");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String roomId = (String) session.getAttributes().get("roomId");
        HashSet<WebSocketSession> sessionHashSet = sessionsMap.get(roomId);
        for (WebSocketSession s : sessionHashSet) {
            s.sendMessage(message);
            s.sendMessage(new TextMessage("hello"));
        }
    }
}
