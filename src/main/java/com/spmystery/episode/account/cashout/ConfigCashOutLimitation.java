package com.spmystery.episode.account.cashout;

import com.spmystery.episode.account.AccountOperate;
import com.spmystery.episode.account.mapper.UserCashOutAccountApplicationMapper;
import com.spmystery.episode.config.CacheLoadRunner;
import com.spmystery.episode.drama.mapper.UserWatchDramaRecordMapper;
import com.spmystery.episode.exception.DramaException;
import com.spmystery.episode.systemconfig.SystemConfigOperate;
import com.spmystery.episode.systemconfig.entity.CashOutCondition;
import com.spmystery.episode.systemconfig.entity.SystemConfig;
import com.spmystery.episode.user.UserOperate;
import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.util.CurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.spmystery.episode.exception.DramaErrorCode.*;
import static com.spmystery.episode.systemconfig.SystemConfigOperate.NEED_AD_COUNTS_PER_LEVEL;
import static com.spmystery.episode.systemconfig.SystemConfigOperate.USER_CASH_OUT_COUNT_PER_DAY;
import static java.math.BigDecimal.ZERO;

@Component
public class ConfigCashOutLimitation {

    @Autowired
    private UserOperate userOperate;

    @Autowired
    private SystemConfigOperate systemConfigOperate;

    @Autowired
    private UserCashOutAccountApplicationMapper userCashOutAccountApplicationMapper;

    @Autowired
    private AccountOperate accountOperate;

    @Autowired
    private UserWatchDramaRecordMapper userWatchDramaRecordMapper;

    /** [{
     *   "level": 1,
     *   "amount": 0.3  提现金额 + 在途金额 不能大于余额
     *   "watchNumber": 3, 查询观看历史表，当天看完不同的3个视频
     *   "userClass": 0, 查询user表里面查看广告次数，根据配置来判断等级
     *   "dramaCount": 0, 查看整部短剧，是否有SDK接口复用，还是需要自己记(
     *      自己实现，记录用户观看记录，用户ID，短剧ID，短剧集数，当前观看的集数，如何防止用户拖动滚动条刷短剧就必须看广告，允许他拖滚动条
     *   )
     *   "cashOutCountPerDay": 3 查询当前等级当天提现记录，是否大于该配置
     * }]
     */
    public boolean canCashOut(CashOutCondition condition) {
        String userId = CurrentUserUtil.currentUserId();
        //TODO 是否实名认证
        //TODO 是否绑定提现账户
        //TODO 可能修改过，缓存中的提现级别在数据库中没有（刷新缓存时，有用户查询，没有做并发控制情况下）

        //判断用户等级是否要求
        if (condition.getUserLevel() > 0) {
            User user = userOperate.getById(userId);
            Integer watchAdCounts = user.getWatchAdCounts();
            Integer addCountsPerLevel = systemConfigOperate.findIntegerByKey(NEED_AD_COUNTS_PER_LEVEL);
            Integer userCurrentLevel = watchAdCounts / addCountsPerLevel;
            if (userCurrentLevel < condition.getUserLevel()) {
                throw new DramaException(DO004);
            }
        }


        //判断用户当天该等级提现次数是否超过当前等级的提现次数上限
        int hasCashOutCount =
                userCashOutAccountApplicationMapper.findCountByUserIdAndLevel(userId, condition.getLevel());
        if (hasCashOutCount > condition.getCashOutCountPerDay()) {
            throw new DramaException(DO005);
        }

        //判断用户提现次数是否超过提现总次数限制
        Integer config = systemConfigOperate.findIntegerByKey(USER_CASH_OUT_COUNT_PER_DAY);
        List<Integer> status = new ArrayList<>();
        status.add(0);
        status.add(1);
        int count = userCashOutAccountApplicationMapper.findCountByUserIdAndStatus(userId, status);
        if (count > config) {
            throw new DramaException(DO006);
        }

        //提现金额不能大于用户余额
        BigDecimal userBalance = accountOperate.getUserBalance();
        if (userBalance == null || userBalance.compareTo(ZERO) <= 0) {
            throw new DramaException(DA001);
        }
        //在途的金额（不止当天） + 当前申请提现的金额 不能大于余额
        BigDecimal cashOutInDoingAmount = userCashOutAccountApplicationMapper.findSumInDoingByUserId(userId);
        if (cashOutInDoingAmount.add(condition.getAmount()).compareTo(userBalance) > 0) {
            throw new DramaException(DO007);
        }


        //观看不同视频的次数，用户观看短剧
        int differentDramaCount = userWatchDramaRecordMapper.findCountByUserId(userId);
        if (differentDramaCount < condition.getWatchNumber()) {
            throw new DramaException(DO008, condition.getWatchNumber(), differentDramaCount);
        }

        //判断观看整部短剧数量是否符合要求
        if (condition.getDramaCount() > 0) {

        }
        return true;
    }

}
