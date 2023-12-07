package com.spmystery.episode.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
