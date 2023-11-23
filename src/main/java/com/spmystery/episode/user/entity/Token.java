package com.spmystery.episode.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Token {

    public static final int TOKEN_EXPIRE_DAYS = 15;

    private String id;

    private User user;

    private LocalDateTime expireTime;

    private LocalDateTime lastModifyTime;

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
}
