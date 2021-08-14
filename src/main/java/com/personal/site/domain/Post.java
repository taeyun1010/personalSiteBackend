package com.personal.site.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document
public class Post {
    @Id
    private final String id;
    private final String title;
    private final String content;
    private final String username;
    private final String date;
    private final Long readCount;
}
