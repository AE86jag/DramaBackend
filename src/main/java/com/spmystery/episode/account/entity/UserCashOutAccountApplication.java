package com.spmystery.episode.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spmystery.episode.response.CommonResponse;
import com.spmystery.episode.user.entity.Token;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime approveTime;
    private String rejectReason;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime lastModifyTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
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
