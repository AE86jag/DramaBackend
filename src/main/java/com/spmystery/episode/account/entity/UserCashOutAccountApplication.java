package com.spmystery.episode.account.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserCashOutAccountApplication {

    private Integer id;
    private String userId;
    private BigDecimal amount;
    private Integer status;
    private String approveUserId;
    private String approveUserName;
    private LocalDateTime approveTime;
    private String rejectReason;
    private LocalDateTime lastModifyTime;
    private LocalDateTime create_time;
}
