package com.spmystery.episode.account.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserCashOutAccountApplication {

    private Integer id;
    private String userId;
    private BigDecimal amount;
    private Integer level;
    private Integer status;
    private String approveUserId;
    private String approveUserName;
    private LocalDateTime approveTime;
    private String rejectReason;
    private LocalDateTime lastModifyTime;
    private LocalDateTime createTime;

    public boolean isToApprove() {
        return status != null && status.equals(0);
    }

    public UserAccountRecord toUserAccountRecord(String userId) {
        UserAccountRecord record = new UserAccountRecord();
        record.setUserId(userId);
        record.setChangeType(UserAccountRecord.ChangeType.C);
        record.setChangeMessage("提现");
        record.setChangeAmount(amount.negate());
        return record;
    }
}
