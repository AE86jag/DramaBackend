package com.spmystery.episode.user.entity;

import lombok.Data;

@Data
public class UserToken {

    private String id;

    private String username;

    private String headImageUrl;

    private Integer sex;

    public static UserToken from(Token token) {
        UserToken userToken = new UserToken();
        userToken.setId(token.getId());
        userToken.setUsername(token.getUser().getNickname());
        userToken.setHeadImageUrl(token.getUser().getHeadImgUrl());
        userToken.setSex(token.getUser().getSex());
        return userToken;
    }
}
