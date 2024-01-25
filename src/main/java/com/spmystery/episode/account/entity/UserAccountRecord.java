package com.spmystery.episode.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.spmystery.episode.systemconfig.SystemConfigOperate.*;

@Data
public class UserAccountRecord {

    private Integer id;

    private String userId;

    private ChangeType changeType;

    private String changeMessage;

    private BigDecimal changeAmount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")

    private LocalDateTime lastModifyTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;


    public enum ChangeType{
        C("提现"),
        D("看完一集短剧", USER_WATCHING_ONE_DRAMA),
        A("看完广告", USER_WATCHING_ADVERTISEMENTS),
        I("邀请", INVITE_USER_REWARD),
        R("注册", USER_REGISTER_REWARD),
        S("签到");

        private String description;

        /**
         * 对应类型账户积分变动金额配置项key
         */
        private String key;

        ChangeType(String description, String key) {
            this.description = description;
            this.key = key;
        }

        ChangeType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public String getKey() {
            return key;
        }
    }

    public static UserAccountRecord buildRegisterRecord(String userId) {
        UserAccountRecord record = new UserAccountRecord();
        record.setUserId(userId);
        record.setChangeType(ChangeType.R);
        record.setChangeMessage("新用户注册");
        return record;
    }

    public boolean isRegister() {
        return changeType == ChangeType.R;
    }
}
