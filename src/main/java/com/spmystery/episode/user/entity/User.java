package com.spmystery.episode.user.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class User {

    private String id;

    private String mobile;

    private String nickname;

    private List<String> roleIds;

    private String openId;

    private String unionId;

    private String headImgUrl;

    private String sex;

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

    public boolean isValid() {
        return status != null && status.equals(1);
    }
}
