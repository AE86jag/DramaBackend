package com.spmystery.episode.account;

import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.config.CacheLoadRunner;
import com.spmystery.episode.systemconfig.entity.CashOutCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    private AccountOperate accountOperate;

    @PostMapping
    public void add(@RequestBody UserAccountAddParam param) {
        //https, 签名, 时间戳
        accountOperate.add(param.to());
    }

    @GetMapping("/balance")
    @PreAuthorize("hasAnyRole('APP_NORMAL')")
    public Object getUserAccountBalance() {
        return accountOperate.getUserBalance();
    }

    @PostMapping("/cash-out/application")
    @PreAuthorize("hasAnyRole('APP_NORMAL', 'APP_ADMIN')")
    public Object cashOutApplication(@RequestBody CashOutApplicationParam param) {
        return null;
    }

    @GetMapping("/record")
    public List<UserAccountRecord> getAccountRecord() {
        List<UserAccountRecord> records = new ArrayList<>();
        UserAccountRecord r1 = new UserAccountRecord();
        r1.setId(1);
        r1.setUserId("u1");
        r1.setChangeAmount(new BigDecimal("+123.88"));
        r1.setChangeMessage("增加");
        r1.setCreateTime(LocalDateTime.now());
        records.add(r1);

        UserAccountRecord r2 = new UserAccountRecord();
        r2.setId(2);
        r2.setUserId("u2");
        r2.setChangeAmount(new BigDecimal("-223.88"));
        r2.setChangeMessage("增加3444");
        r2.setCreateTime(LocalDateTime.now());
        records.add(r2);
        records.add(r1);
        records.add(r2);
        records.add(r1);
        records.add(r2);
        records.add(r1);
        records.add(r2);
        records.add(r1);
        return records;
    }

    @GetMapping("/cash-out-amount/config")
    public List<Map<String, Object>> getCashOutAmountConfig() {
        List<CashOutCondition> all = CacheLoadRunner.getAll();
        return all.stream().map(a -> {
            Map<String, Object> map = new HashMap<>();
            map.put("level", a.getLevel());
            map.put("amount", a.getAmount().toString());
            return map;
        }).collect(Collectors.toList());
    }
}
