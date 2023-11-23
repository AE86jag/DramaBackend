package com.spmystery.episode.user;

import com.spmystery.episode.user.entity.Token;
import com.spmystery.episode.user.mapper.TokenOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserOperate userOperate;

    @Autowired
    private TokenOperate tokenOperate;

    @PostMapping("/register")
    public Token register(@RequestBody UserRegisterParam userRegisterParam) {
        return userOperate.register(userRegisterParam.toUser());
    }

    @GetMapping("/token/{tokenId}")
    public Token getUserByTokenId(@PathVariable String tokenId) {
        return tokenOperate.getById(tokenId);
    }
}
