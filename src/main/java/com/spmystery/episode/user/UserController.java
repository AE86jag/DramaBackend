package com.spmystery.episode.user;

import com.spmystery.episode.account.AccountOperate;
import com.spmystery.episode.account.BindCashOutAccountParam;
import com.spmystery.episode.response.CommonResponse;
import com.spmystery.episode.user.entity.Token;
import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.user.mapper.TokenOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserOperate userOperate;

    @Autowired
    private TokenOperate tokenOperate;

    @Autowired
    private AccountOperate accountOperate;

    @PostMapping("/register")
    public Token register(@RequestBody UserRegisterParam userRegisterParam) {
        return userOperate.register(userRegisterParam.toUser());
    }

    @GetMapping("/token/{tokenId}")
    public Token getUserByTokenId(@PathVariable String tokenId) {
        return tokenOperate.getById(tokenId);
    }

    @PostMapping("/ad-counts")
    @PreAuthorize("hasAnyRole('APP_NORMAL')")
    //TODO 防刷 非对称加密
    public void addUserWatchAdCounts() {
        userOperate.addUserAdCount();
    }

    @PutMapping("/bind-cash-out-account")
    public void bindCashOutAccount(@RequestBody BindCashOutAccountParam param) {
        param.check();
        userOperate.bindCashOutAccount(param.getAlipayAccount(), param.getAlipayAccountName());
    }

    @GetMapping("/open-id/{openId}")
    public User getUserByOpenId(@PathVariable String openId) {
        return userOperate.getUserByOpenId(openId);
    }

    @GetMapping("/watch-record")
    @PreAuthorize("hasAnyRole('APP_NORMAL')")
    public List<Long> getDramaId() {
        return userOperate.getUserWatchedDrama();
    }

    @GetMapping("/cash-out-account")
    @PreAuthorize("hasAnyRole('APP_NORMAL')")
    public CashOutBindAccount getUserCashOutAccount() {
        return userOperate.getUserCashOutBindAccount();
    }

    //TODO 要带权限，要非对称加密，控制频率,要同一个用户ID1分钟之内不能调多次，一般短剧至少1分钟
    @PostMapping("/watch-drama")
    public BigDecimal userWatchDrama(@RequestBody UserWatchDramaParam param) {
        param.check();
        return userOperate.watchDrama(param);
    }

    @GetMapping("/balance")
    public CommonResponse<BigDecimal> getUserBalance() {
        BigDecimal userBalance = accountOperate.getUserBalance();
        return CommonResponse.build(userBalance);
    }

    @PostMapping("/sign-in")
    public void userSignIn() {
        userOperate.signIn();
    }
}
