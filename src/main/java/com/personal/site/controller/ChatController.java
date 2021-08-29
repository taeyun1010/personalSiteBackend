package com.personal.site.controller;


import com.personal.site.domain.ChatMessage;
import com.personal.site.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @MessageMapping("/chat/{roomId}/sendMessage")
//    @SendTo("/topic/public")
    public void sendMessage(@DestinationVariable String roomId,
                            @Payload ChatMessage chatMessage) {
//        return chatMessage;
        chatMessage.setDate(new Date());
        chatMessage.setRoomId(roomId);
        chatMessageRepository.insert(chatMessage);
        messagingTemplate.convertAndSend("/topic/" + roomId, chatMessage);
    }

    @MessageMapping("/chat/{roomId}/addUser")
//    @SendTo("/topic/public")
    public void addUser(@DestinationVariable String roomId,
                        @Payload ChatMessage chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        System.out.println("sessionId = " + sessionId);
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("roomId", roomId);
//        return chatMessage;
        List<ChatMessage> prevMessages = chatMessageRepository.findByRoomIdOrderByDate(roomId);

//        for (ChatMessage prevMessage : prevMessages) {
//            messagingTemplate.convertAndSendToUser(sessionId, "/topic/" + roomId, prevMessage);
//        }
        messagingTemplate.convertAndSend("/topic/" + roomId, chatMessage);
        chatMessage.setDate(new Date());
        chatMessage.setRoomId(roomId);
        chatMessageRepository.insert(chatMessage);
    }

}
