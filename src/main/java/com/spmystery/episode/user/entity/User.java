package com.spmystery.episode.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private String id;

    private String mobile;

    private String nickname;

    private String openId;

    private String unionId;

    private String headImgUrl;

    private Integer sex;

    private Integer level;

    /**
     * 用户所在省
     */
    private String province;

    /**
     * 用户所在市
     */
    private String city;

    /**
     * 用户所在县
     */
    private String country;

    /**
     * 邀请人ID
     */
    private String inviteUserId;

    private Integer status;

    private LocalDateTime lastModifyTime;
    private LocalDateTime createTime;

    @JsonIgnore
    public boolean isValid() {
        return status != null && status.equals(1);
    }

    public void update(User user) {
        nickname = user.getNickname();
        headImgUrl = user.getHeadImgUrl();
        unionId = user.getUnionId();
        province = user.getProvince();
        city = user.getCity();
        country = user.getCountry();
        sex = user.getSex();
        level = 0;
    }
}
