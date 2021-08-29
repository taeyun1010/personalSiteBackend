package com.personal.site.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document
public class ChatMessage {
    @Id
    private String id;

    private MessageType type;
    private String content;
    private String sender;
    private String roomId;
    private Date date;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
