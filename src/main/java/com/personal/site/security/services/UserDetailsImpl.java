package com.personal.site.security.services;

import com.personal.site.domain.Member;
import com.personal.site.domain.Role;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String address;
    private String phone;
    private String website;
    private String gender;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String id, String username, String email,
                           String password, String address, String phone,
                           String website, String gender,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.gender = gender;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Member member) {
        List<GrantedAuthority> authorities = member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(member.getId(),
                member.getUsername(), member.getEmail(), member.getPassword(),
                member.getAddress(), member.getPhone(), member.getWebsite(),
                member.getGender(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
