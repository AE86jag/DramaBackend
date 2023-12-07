package com.spmystery.episode.user;

import com.spmystery.episode.account.AccountOperate;
import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.user.entity.Token;
import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.user.entity.UserRole;
import com.spmystery.episode.user.mapper.TokenOperate;
import com.spmystery.episode.user.mapper.UserMapper;
import com.spmystery.episode.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserOperate {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenOperate tokenOperate;

    @Autowired
    private AccountOperate accountOperate;

    @Transactional
    public Token register(User user) {
        //删除以前的Token, 只能单点登录，防止刷余额
        tokenOperate.delete(user.getId());

        User existUser = userMapper.findByOpenId(user.getOpenId());
        if (existUser != null) {
            throw new RuntimeException("用户状态异常, 请重试");
        }
        user.setId(StringUtil.generateId());
        user.setStatus(1);
        //TODO 邀请人ID怎么传
        //user.setInviteUserId();
        userMapper.insert(user);
        userMapper.insertUserRole(UserRole.build(user.getId()));

        accountOperate.add(UserAccountRecord.buildRegisterRecord(user.getId()));

        //进行登录
        return tokenOperate.create(user);

    }
}
