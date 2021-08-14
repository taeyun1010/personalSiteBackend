package com.personal.site.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document
public class Member {
    @Id
    private String id;
    @Indexed(unique=true)
    private final String email;
    private final String password;
    @Indexed(unique=true)
    private final String username;
    private final String address;
    private final String phone;
    private final String website;
    private final String gender;
    @DBRef
    private Set<Role> roles = new HashSet<>();

    public Member(String email, String password,
                  String username, String address, String phone,
                  String website, String gender) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.gender = gender;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
