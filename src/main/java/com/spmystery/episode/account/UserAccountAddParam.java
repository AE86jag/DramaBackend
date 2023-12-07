package com.spmystery.episode.account;

import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.util.CurrentUserUtil;
import lombok.Data;


@Data
public class UserAccountAddParam {

    private UserAccountRecord.ChangeType changeType;

    private String changeMessage;

    public UserAccountRecord to() {
        UserAccountRecord record = new UserAccountRecord();
        //TODO 当前登录信息里面取用户ID
        record.setUserId(CurrentUserUtil.currentUserId());
        record.setChangeType(changeType);
        record.setChangeMessage(changeMessage);
        return record;
    }
}
