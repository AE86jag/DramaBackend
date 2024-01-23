package com.spmystery.episode.user;

import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.util.StringUtil;
import lombok.Data;

@Data
public class UserRegisterParam {

    private String openId;

    private String unionId;

    private String headImgUrl;

    private String nickname;

    private Integer sex;

    private String province;

    private String city;

    private String country;

    public User toUser() {
        User user = new User();
        user.setOpenId(StringUtil.isNotEmpty(openId) ? openId : null);
        user.setUnionId(StringUtil.isNotEmpty(unionId) ? unionId : null);
        user.setHeadImgUrl(StringUtil.isNotEmpty(headImgUrl) ? headImgUrl : null);
        user.setNickname(StringUtil.isNotEmpty(nickname) ? nickname : null);
        user.setSex(sex);
        user.setProvince(StringUtil.isNotEmpty(province) ? province : null);
        user.setCity(StringUtil.isNotEmpty(city) ? city : null);
        user.setCountry(StringUtil.isNotEmpty(country) ? country : null);
        user.setWatchAdCounts(0);
        user.setLevel(0);
        user.setWatchAdCounts(0);
        return user;
    }
}
