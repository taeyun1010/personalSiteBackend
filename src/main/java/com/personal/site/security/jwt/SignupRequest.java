package com.personal.site.security.jwt;

import com.personal.site.domain.Role;
import lombok.Data;

import java.util.Set;

import javax.validation.constraints.*;

@Data
public class SignupRequest {
    @Email
    private String email;
    private String password;
    private String username;
    private String address;
    private String phone;
    private String website;
    private String gender;
    private Set<String> roles;

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRole(Set<String> roles) {
        this.roles = roles;
    }
}