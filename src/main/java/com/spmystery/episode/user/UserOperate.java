package com.spmystery.episode.user;

import com.spmystery.episode.account.AccountOperate;
import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.exception.DramaException;
import com.spmystery.episode.user.entity.Token;
import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.user.entity.UserRole;
import com.spmystery.episode.user.mapper.TokenOperate;
import com.spmystery.episode.user.mapper.UserMapper;
import com.spmystery.episode.util.CurrentUserUtil;
import com.spmystery.episode.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.spmystery.episode.exception.DramaErrorCode.*;

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
        System.out.println("---------------");
        System.out.println(user);
        System.out.println("---------------");
        userMapper.insert(user);
        userMapper.insertUserRole(UserRole.build(user.getId()));

        accountOperate.add(UserAccountRecord.buildRegisterRecord(user.getId()));

        //进行登录
        return tokenOperate.create(user);

    }

    public void addUserAdCount() {
        String userId = CurrentUserUtil.currentUserId();
        userMapper.updateWatchAdCountsById(userId);
    }

    public User getById(String userId) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new DramaException(DU001);
        }

        if (!user.isValid()) {
            throw new DramaException(DU002);
        }

        return user;
    }

    public void bindCashOutAccount(String alipayAccount, String alipayAccountName) {
        String userId = CurrentUserUtil.currentUserId();
        User user = userMapper.findById(userId);
        if (user.isBindCashOutAccount()) {
            throw new DramaException(DU003);
        }

        userMapper.updateAccountAndNameById(userId, alipayAccount, alipayAccountName);
    }

    public User getUserByOpenId(String openId) {
        return userMapper.findByOpenId(openId);
    }
}
