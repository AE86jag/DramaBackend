package com.spmystery.episode.account;

import com.spmystery.episode.util.StringUtil;
import lombok.Data;

@Data
public class BindCashOutAccountParam {

    private String alipayAccount;

    private String alipayAccountName;

    public void check() {
        if (StringUtil.isEmpty(alipayAccount)) {
            throw new RuntimeException("支付宝账号不能为空");
        }

        if (StringUtil.isEmpty(alipayAccountName)) {
            throw new RuntimeException("支付宝收款方姓名不能为空");
        }
    }
}
