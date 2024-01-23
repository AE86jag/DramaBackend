package com.spmystery.episode.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spmystery.episode.user.CashOutBindAccount;
import com.spmystery.episode.util.StringUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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

    private Integer watchAdCounts;

    private String alipayAccount;

    private String alipayAccountName;

    private List<String> roleIds;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JsonIgnore
    private LocalDateTime lastModifyTime;
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JsonIgnore
    private LocalDateTime createTime;

    @JsonIgnore
    public boolean isValid() {
        return status != null && status.equals(1);
    }

    public boolean isBindCashOutAccount() {
        return StringUtil.isNotEmpty(alipayAccount) && StringUtil.isNotEmpty(alipayAccountName);
    }

    public CashOutBindAccount toCashOutBindAccount() {
        CashOutBindAccount account = new CashOutBindAccount();
        account.setAlipayAccount(alipayAccount);
        account.setAlipayAccountName(alipayAccountName);
        return account;
    }
}
