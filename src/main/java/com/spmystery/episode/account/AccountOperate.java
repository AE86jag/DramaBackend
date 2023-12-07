package com.spmystery.episode.account;


import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.account.mapper.UserAccountRecordMapper;
import com.spmystery.episode.systemconfig.entity.SystemConfig;
import com.spmystery.episode.systemconfig.SystemConfigOperate;
import com.spmystery.episode.util.CurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;


@Component
public class AccountOperate {

    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;

    @Autowired
    private SystemConfigOperate systemConfigOperate;

    public void add(UserAccountRecord record) {
        String key = record.getChangeType().getKey();
        if (StringUtils.isEmpty(key)) {
            if (record.getChangeAmount() == null || record.getChangeAmount().compareTo(BigDecimal.ZERO) <= 0) {
                //如果是提现操作，而且传的变更金额为空或者提现金额
                throw new RuntimeException("金额参数异常");
            }
            //提现取金额相反数, 外面传的参数是正数
            record.setChangeAmount(record.getChangeAmount().negate());
        } else {
            //除了提现其他类型的都是使用系统配置的金额
            SystemConfig config = systemConfigOperate.findByKey(key);
            record.setChangeAmount(new BigDecimal(config.getValue()));
        }

        if (record.isRegister()) {
            //注册一个人只能加一次积分
            int count = userAccountRecordMapper.findCountByUserIdAndType(record.getUserId(), record.getChangeType());
            if (count > 0) {
                throw new RuntimeException("注册加积分已达上限");
            }
        }

        userAccountRecordMapper.insert(record);
    }

    public BigDecimal getUserBalance() {
        return userAccountRecordMapper.findSumByUserId(CurrentUserUtil.currentUserId());
    }
}
