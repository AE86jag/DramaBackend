package com.spmystery.episode.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
