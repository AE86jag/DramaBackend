package com.spmystery.episode.user.mapper;

import com.spmystery.episode.user.entity.Token;
import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TokenOperate {

    @Autowired
    private TokenMapper tokenMapper;

    public Token create(User user) {
        String tokenId = StringUtil.generateId();
        Token token = Token.build(tokenId, user);
        tokenMapper.insert(token);
        return token;
    }

    public void delete(String userId) {
        tokenMapper.deleteByUserId(userId);
    }

    public Token getById(String id) {
        //是否过期，过期就删除，并返回401
        Token token = tokenMapper.findById(id);
        if (token == null) {
            throw new RuntimeException("Token不存在");
        }

        if (token.isExpired()) {
            //TODO 定义异常
            throw new RuntimeException("已过期");
        }

        return token;
    }
}
