package com.spmystery.episode.account;


import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.account.mapper.UserAccountRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountOperate {

    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;



    public void add(UserAccountRecord record) {
        //TODO 根据类型取配置表里面的数值
        if (record.getChangType() == UserAccountRecord.ChangType.R) {

        }
        userAccountRecordMapper.insert(record);
    }

    public BigDecimal getUserBalance() {
        String userId = "TODO 从登录信息取用户ID";
        return userAccountRecordMapper.findSumByUserId(userId);
    }
}
