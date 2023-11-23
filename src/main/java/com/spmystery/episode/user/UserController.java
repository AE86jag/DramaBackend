package com.spmystery.episode.user;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterParam userRegisterParam) {

    }

    @GetMapping("/open-id/{openId}")
    public String register(@PathVariable String openId) {

    }
}
