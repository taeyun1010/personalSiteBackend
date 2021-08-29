package com.personal.site.repository;

import com.personal.site.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByRoomIdOrderByDate(String roomId);
}
