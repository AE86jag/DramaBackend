package com.spmystery.episode.user;

import lombok.Data;

@Data
public class UserRegisterParam {

    private String openId;

    private String unionId;

    private String headImgUrl;

    private String nickname;

    private String sex;

    private String province;

    private String city;

    private String country;
}
