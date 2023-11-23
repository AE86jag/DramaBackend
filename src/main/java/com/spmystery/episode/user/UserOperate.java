package com.spmystery.episode.user;

import com.spmystery.episode.user.entity.Token;
import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.user.mapper.TokenMapper;
import com.spmystery.episode.user.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class UserOperate {

    private UserMapper userMapper;

    private TokenMapper tokenMapper;

    @Transactional
    public Token register(User user) {
        User existUser = userMapper.findByOpenId(user.getOpenId());
        if (existUser == null) {

        } else {
            if (existUser.isValid()) {
                //删除以前的Token, 只能单点登录，防止刷余额
                tokenMapper.deleteByUserId(user.getId());
                //删除
                String tokenId = UUID.randomUUID().toString();
                tokenMapper.insert(Token.build(tokenId, user));
            } else {

            }
        }
    }
}
