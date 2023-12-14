package com.spmystery.episode.account;

import com.spmystery.episode.account.entity.UserCashOutAccountApplication;
import com.spmystery.episode.account.mapper.UserCashOutAccountApplicationMapper;
import com.spmystery.episode.config.CacheLoadRunner;
import com.spmystery.episode.exception.DramaException;
import com.spmystery.episode.systemconfig.SystemConfigOperate;
import com.spmystery.episode.systemconfig.entity.CashOutCondition;
import com.spmystery.episode.systemconfig.entity.SystemConfig;
import com.spmystery.episode.util.CurrentUserUtil;
import com.spmystery.episode.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.spmystery.episode.exception.DramaErrorCode.*;
import static com.spmystery.episode.systemconfig.SystemConfigOperate.SYSTEM_CASH_OUT_CONDITION_CONFIG;
import static com.spmystery.episode.systemconfig.SystemConfigOperate.USER_CASH_OUT_COUNT_PER_DAY;
import static java.math.BigDecimal.ZERO;

@Component
public class UserCashOutAccountOperate {

    @Autowired
    private UserCashOutAccountApplicationMapper userCashOutAccountApplicationMapper;

    @Autowired
    private AccountOperate accountOperate;

    @Autowired
    private SystemConfigOperate systemConfigOperate;

    public void cashOutApplication(Integer level, UserCashOutAccountApplication application) {
        //查询
        CashOutCondition condition = CacheLoadRunner.getByLevel(level);
        if (condition == null) {
            throw new DramaException(DO001);
        }

        //提现金额不能是0或者负数
        if (application.getAmount().compareTo(ZERO) <= 0) {
            throw new RuntimeException("提现金额必须大于0元");
        }
        //余额必须要是正的
        BigDecimal userBalance = accountOperate.getUserBalance();
        if (userBalance == null || userBalance.compareTo(ZERO) <= 0) {
            throw new DramaException(DA001);
        }

        //提现金额不能大于余额, 流程中的金额  + 当前的金额
        if (userBalance.compareTo(application.getAmount()) < 0) {
            throw new RuntimeException("提现金额不能大于余额");
        }

        //每天提现次数不能大于配置的
        SystemConfig config = systemConfigOperate.findByKey(USER_CASH_OUT_COUNT_PER_DAY);
        if (config == null || StringUtils.isEmpty(config.getValue())) {
            throw new DramaException(DC001);
        }

        List<Integer> status = new ArrayList<>();
        status.add(0);
        status.add(1);
        int count = userCashOutAccountApplicationMapper
                .findCountByUserIdAndStatus(CurrentUserUtil.currentUserId(), status);
        if (count > Integer.valueOf(config.getValue())) {
            throw new RuntimeException("今日提现次数已用完");
        }


        //检验用户是否满足配置的规则


        /**
         * [{
         *   "level": 1,
         *   "amount": 0.3
         *   "watchNumber": 3,
         *   "userClass": 0,
         *   "dramaCount": 0,
         *   "cashOutCountPerDay": 3
         * },
         * {
         *   "level": 2,
         *   "amount": 0.5
         *   "watchNumber": 10,
         *   "userClass": 4,
         *   "dramaCount": 0,
         *   "cashOutCountPerDay": 3
         * },
         * {
         *   "level": 3,
         *   "amount": 100
         *   "watchNumber": 20,
         *   "userClass": 10,
         *   "dramaCount": 1,
         *   "cashOutCountPerDay": 3
         * },
         * {
         *   "level": 4,
         *   "amount": 200
         *   "watchNumber": 30,
         *   "userClass": 10,
         *   "dramaCount": 3,
         *   "cashOutCountPerDay": 3
         * },{
         *   "level": 5,
         *   "amount": 300
         *   "watchNumber": 50,
         *   "userClass": 10,
         *   "dramaCount": 5,
         *   "cashOutCountPerDay": 3
         * },{
         *   "level": 6,
         *   "amount": 500
         *   "watchNumber": 100,
         *   "userClass": 10,
         *   "dramaCount": 10,
         *   "cashOutCountPerDay": 3
         * }]
         * 每日可提现【3】次
         * 可提现金额符合条件，【】中的数字皆为可配置
         *  1. 第一档【0.3】 余额够 && 当天观看不同视频达【3】次   && 用户等级达到【0】级   && 看完【0】部短剧
         *  2. 第二档【0.5】 余额够 && 当天观看不同视频达【10】次  && 用户等级达到【4】级   && 看完【0】部短剧
         *  3. 第三档【100】 余额够 && 当天观看不同视频达【20】次  && 用户等级达到【10】级  && 看完【1】部短剧
         *  4. 第四档【200】 余额够 && 当天观看不同视频达【30】次  && 用户等级达到【10】级  && 看完【3】部短剧
         *  5. 第五档【300】 余额够 && 当天观看不同视频达【50】次  && 用户等级达到【10】级  && 看完【5】部短剧
         *  6. 第六档【500】 余额够 && 当天观看不同视频达【100】次 && 用户等级达到【10】级  && 看完【10】部短剧
         */



    }

    public void cashOutApprove(String applicationId) {

    }
}
