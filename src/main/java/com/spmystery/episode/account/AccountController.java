package com.spmystery.episode.account;

import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.config.CacheLoadRunner;
import com.spmystery.episode.response.CommonResponse;
import com.spmystery.episode.systemconfig.entity.CashOutCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserCashOutAccountOperate userCashOutAccountOperate;

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
    //@PreAuthorize("hasAnyRole('APP_NORMAL', 'APP_ADMIN')")
    public void cashOutApplication(@RequestBody CashOutApplicationParam param) {
        userCashOutAccountOperate.cashOutApplication(param.getLevel(), param.to(), param.getDramaTotalParams());
    }

    @PutMapping("/cash-out/approve/{applicationId}")
    //@PreAuthorize("hasAnyRole('APP_ADMIN')")
    public void cashOutApprove(@PathVariable String applicationId, @RequestParam Integer status, String rejectReason) {
        userCashOutAccountOperate.cashOutApprove(applicationId, status, rejectReason);
    }

    @GetMapping("/records")
    public List<UserAccountRecord> getAccountRecord() {
        return accountOperate.getUserAccountRecords();
    }

    @GetMapping("/cash-out-amount/config")
    public List<CashOutConfigItem> getCashOutAmountConfig() {
        List<CashOutCondition> all = CacheLoadRunner.getAll();
        return all.stream().map(a -> CashOutConfigItem.from(a.getLevel(), a.getAmount().toString()))
                .collect(Collectors.toList());
    }
}
