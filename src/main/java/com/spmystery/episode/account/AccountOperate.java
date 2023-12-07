package com.spmystery.episode.account;


import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.account.mapper.UserAccountRecordMapper;
import com.spmystery.episode.systemconfig.entity.SystemConfig;
import com.spmystery.episode.systemconfig.SystemConfigOperate;
import com.spmystery.episode.util.CurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.spmystery.episode.systemconfig.SystemConfigOperate.USER_REGISTER_REWARD;

@Component
public class AccountOperate {

    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;

    @Autowired
    private SystemConfigOperate systemConfigOperate;

    public void add(UserAccountRecord record) {
        //TODO 根据类型取配置表里面的数值
        if (record.getChangeType() == UserAccountRecord.ChangeType.R) {
            SystemConfig config = systemConfigOperate.findByKey(USER_REGISTER_REWARD);
            record.setChangeAmount(new BigDecimal(config.getValue()));
        }
        userAccountRecordMapper.insert(record);
    }

    public BigDecimal getUserBalance() {
        return userAccountRecordMapper.findSumByUserId(CurrentUserUtil.currentUserId());
    }
}
