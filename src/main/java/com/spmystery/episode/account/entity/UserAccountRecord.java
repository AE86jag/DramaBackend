package com.spmystery.episode.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        D("看完一集短剧"),
        A("看完广告"),
        I("邀请"),
        R("注册");

        private String description;

        ChangeType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static UserAccountRecord buildRegisterRecord(String userId) {
        UserAccountRecord record = new UserAccountRecord();
        record.setUserId(userId);
        record.setChangeType(ChangeType.R);
        record.setChangeMessage("新用户注册");
        return record;
    }
}
