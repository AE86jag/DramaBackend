package com.spmystery.episode.systemconfig.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashOutCondition {

    /**
     * 提现的级别
     */
    private Integer level;
    /**
     * 提现金额
     */
    private BigDecimal amount;
    /**
     * 观看不同短剧次数
     */
    private Integer watchNumber;
    /**
     * 用户等级
     */
    private Integer userLevel;
    /**
     * 观看整部短剧次数，提现金额较大时需要观看完整部短剧
     */
    private Integer dramaCount;
    /**
     * 该等级提现金额每天提现次数
     */
    private Integer cashOutCountPerDay;
}
