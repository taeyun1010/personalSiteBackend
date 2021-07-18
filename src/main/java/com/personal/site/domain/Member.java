package com.personal.site.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document
public class Member {
    @Id
    private final String id;
    @Indexed(unique=true)
    private final String email;
    private final String password;
    private final String nickname;
    private final String address;
    private final String phone;
    private final String website;
    private final String gender;
}
