package com.spmystery.episode.user;

import com.spmystery.episode.user.entity.User;
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

    public User toUser() {
        User user = new User();
        user.setOpenId(openId);
        user.setUnionId(unionId);
        user.setHeadImgUrl(headImgUrl);
        user.setNickname(nickname);
        user.setSex(sex);
        user.setProvince(province);
        user.setCity(city);
        user.setCountry(country);
        return user;
    }
}
