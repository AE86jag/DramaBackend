package com.spmystery.episode.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class Token implements Authentication {

    public static final int TOKEN_EXPIRE_DAYS = 15;

    private String id;

    private User user;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime expireTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime lastModifyTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    public static Token build(String id, User user) {
        Token token = new Token();
        token.setId(id);
        token.setUser(user);
        token.setExpireTime(LocalDateTime.now().plusDays(TOKEN_EXPIRE_DAYS));
        return token;
    }

    @JsonIgnore
    public boolean isExpired() {
        return expireTime == null || expireTime.isBefore(LocalDateTime.now());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (user != null && user.getRoleIds() != null) {
            return user.getRoleIds().stream().map( r -> (GrantedAuthority) () -> r).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return "TokenAuthentication";
    }
}
