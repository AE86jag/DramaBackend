package com.spmystery.episode.account;

import com.spmystery.episode.account.entity.UserCashOutAccountApplication;
import com.spmystery.episode.account.mapper.UserCashOutAccountApplicationMapper;
import org.springframework.stereotype.Component;

@Component
public class UserCashOutAccountOperate {

    private UserCashOutAccountApplicationMapper userCashOutAccountApplicationMapper;


    public void cashOutApplication(UserCashOutAccountApplication application) {
        //提现金额不能大于余额

        /**
         * [{
         *   "level": 1,
         *   "amount": 0.3
         *   "watchNumber": 3,
         *   "userClass": 0,
         *   "dramaCount": 0
         * },
         * {
         *   "level": 2,
         *   "amount": 0.5
         *   "watchNumber": 10,
         *   "userClass": 4,
         *   "dramaCount": 0
         * },
         * {
         *   "level": 3,
         *   "amount": 100
         *   "watchNumber": 20,
         *   "userClass": 10,
         *   "dramaCount": 1
         * },
         * {
         *   "level": 4,
         *   "amount": 200
         *   "watchNumber": 30,
         *   "userClass": 10,
         *   "dramaCount": 3
         * },{
         *   "level": 5,
         *   "amount": 300
         *   "watchNumber": 50,
         *   "userClass": 10,
         *   "dramaCount": 5
         * },{
         *   "level": 6,
         *   "amount": 500
         *   "watchNumber": 100,
         *   "userClass": 10,
         *   "dramaCount": 10
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
}
