package com.spmystery.episode.account;

import com.spmystery.episode.account.cashout.ConfigCashOutLimitation;
import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.account.entity.UserCashOutAccountApplication;
import com.spmystery.episode.account.mapper.UserAccountRecordMapper;
import com.spmystery.episode.account.mapper.UserCashOutAccountApplicationMapper;
import com.spmystery.episode.config.CacheLoadRunner;
import com.spmystery.episode.exception.DramaException;
import com.spmystery.episode.systemconfig.SystemConfigOperate;
import com.spmystery.episode.systemconfig.entity.CashOutCondition;
import com.spmystery.episode.user.UserOperate;
import com.spmystery.episode.util.CurrentUserUtil;
import com.spmystery.episode.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.spmystery.episode.exception.DramaErrorCode.*;

@Component
public class UserCashOutAccountOperate {

    @Autowired
    private UserCashOutAccountApplicationMapper userCashOutAccountApplicationMapper;

    @Autowired
    private UserOperate userOperate;

    @Autowired
    private ConfigCashOutLimitation configCashOutLimitation;

    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;

    public void cashOutApplication(Integer level, UserCashOutAccountApplication application,
                                   List<DramaTotalCount> dramaTotalCounts) {
        //是否绑定提现账户
        if (!userOperate.isBindCashOutAccount()) {
            throw new DramaException(DO012);
        }
        //查询
        CashOutCondition condition = CacheLoadRunner.getByLevel(level);
        if (condition == null) {
            throw new DramaException(DO001);
        }
        //提现条件校验
        configCashOutLimitation.cashOutCheck(condition, dramaTotalCounts);

        userCashOutAccountApplicationMapper.insert(application);

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

    @Transactional
    public void cashOutApprove(String applicationId, Integer status, String rejectReason) {
        if (status == null || (!status.equals(1) && !status.equals(2))) {
            throw new DramaException(DO015);
        }

        if (status.equals(2) && StringUtil.isEmpty(rejectReason)) {
            throw new DramaException(DO018);
        }

        //校验状态是否是
        UserCashOutAccountApplication application =
                userCashOutAccountApplicationMapper.findById(applicationId);
        if (application == null) {
            throw new DramaException(DO013);
        }

        if (!application.isToApprove()) {
            throw new DramaException(DO014);
        }
        //审批通过
        if (status.equals(1)) {
            CashOutCondition condition = CacheLoadRunner.getByLevel(application.getLevel());
            if (condition == null) {
                throw new DramaException(DO001);
            }
            configCashOutLimitation.cashOutApproveCheck(condition);
            //扣余额
            UserAccountRecord record = application.toUserAccountRecord(CurrentUserUtil.currentUserId());
            userAccountRecordMapper.insert(record);
        }
        application.setStatus(status);
        application.setApproveUserId(CurrentUserUtil.currentUserId());
        application.setApproveUserName(CurrentUserUtil.currentUsername());
        application.setRejectReason(rejectReason);

        userCashOutAccountApplicationMapper.updateApproveById(application);
    }
}
