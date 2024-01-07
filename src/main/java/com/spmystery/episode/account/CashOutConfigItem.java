package com.spmystery.episode.account;

import lombok.Data;

@Data
public class CashOutConfigItem {

    private Integer level;

    private String amount;

    public static CashOutConfigItem from(Integer level, String amount) {
        CashOutConfigItem item = new CashOutConfigItem();
        item.setAmount(amount);
        item.setLevel(level);
        return item;
    }
}
