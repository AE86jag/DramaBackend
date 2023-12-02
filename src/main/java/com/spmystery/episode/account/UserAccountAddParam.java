package com.spmystery.episode.account;

import com.spmystery.episode.account.entity.UserAccountRecord;
import lombok.Data;


@Data
public class UserAccountAddParam {

    private UserAccountRecord.ChangType changType;

    private String changeMessage;

    public UserAccountRecord to() {
        UserAccountRecord record = new UserAccountRecord();
        //TODO 当前登录信息里面取用户ID
        record.setUserId("");
        record.setChangType(changType);
        record.setChangeMessage(changeMessage);
        return record;
    }
}
