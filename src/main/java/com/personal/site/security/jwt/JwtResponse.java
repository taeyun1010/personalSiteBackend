package com.personal.site.security.jwt;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private String address;
    private String phone;
    private String website;
    private String gender;
    private List<String> roles;

    public JwtResponse(String accessToken, String id, String username,
                       String address, String phone, String website,
                       String gender, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.gender = gender;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }
}