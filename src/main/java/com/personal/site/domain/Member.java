package com.personal.site.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document
public class Member {
    @Id
    private final String id;
    private final String email;
    private final String password;
    private final String nickname;
    private final String address;
    private final String phone;
    private final String website;
    private final String gender;
}
