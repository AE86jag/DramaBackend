package com.spmystery.episode.user.entity;

import lombok.Data;

@Data
public class UserRole {

    public static final String APP_NORMAL_USER = "APP_NORMAL";

    public static final String APP_ADMIN_USER = "APP_ADMIN";

    private String userId;

    private String roleId;

    public static UserRole build(String userId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(APP_NORMAL_USER);
        return userRole;
    }
}
