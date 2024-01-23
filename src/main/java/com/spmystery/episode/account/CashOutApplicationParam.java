package com.spmystery.episode.account;

import com.spmystery.episode.account.entity.UserCashOutAccountApplication;
import com.spmystery.episode.config.CacheLoadRunner;
import com.spmystery.episode.systemconfig.entity.CashOutCondition;
import com.spmystery.episode.util.CurrentUserUtil;
import lombok.Data;

import java.util.List;

@Data
public class CashOutApplicationParam {

    private Integer level;

    private List<DramaTotalCount> dramaTotalParams;

    public UserCashOutAccountApplication to() {
        UserCashOutAccountApplication application = new UserCashOutAccountApplication();
        application.setUserId(CurrentUserUtil.currentUserId());
        CashOutCondition condition = CacheLoadRunner.getByLevel(level);
        application.setAmount(condition.getAmount());
        application.setLevel(level);
        application.setStatus(0);
        return application;
    }
}
