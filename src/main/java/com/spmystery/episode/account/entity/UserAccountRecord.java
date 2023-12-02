package com.spmystery.episode.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserAccountRecord {

    private Integer id;

    private String userId;

    private ChangType changType;

    private String changeMessage;

    private BigDecimal changAmount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")

    private LocalDateTime lastModifyTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;


    public enum ChangType{
        C("提现"),
        D("看完一集短剧"),
        A("看完广告"),
        I("邀请"),
        R("注册");

        private String description;

        ChangType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
