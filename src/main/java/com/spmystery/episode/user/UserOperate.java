package com.spmystery.episode.user;

import com.spmystery.episode.account.AccountOperate;
import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.drama.entity.UserWatchDramaRecord;
import com.spmystery.episode.drama.mapper.UserWatchDramaRecordMapper;
import com.spmystery.episode.exception.DramaException;
import com.spmystery.episode.systemconfig.SystemConfigOperate;
import com.spmystery.episode.user.entity.DramaTotal;
import com.spmystery.episode.user.entity.Token;
import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.user.entity.UserRole;
import com.spmystery.episode.user.mapper.TokenOperate;
import com.spmystery.episode.user.mapper.UserMapper;
import com.spmystery.episode.util.CurrentUserUtil;
import com.spmystery.episode.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.spmystery.episode.exception.DramaErrorCode.*;
import static com.spmystery.episode.systemconfig.SystemConfigOperate.ADD_ACCOUNT_BALANCE_LIMIT_WHEN_WATCH_AD;
import static com.spmystery.episode.systemconfig.SystemConfigOperate.NEED_AD_COUNTS_PER_LEVEL;

@Component
@Slf4j
public class UserOperate {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenOperate tokenOperate;

    @Autowired
    private AccountOperate accountOperate;

    @Autowired
    private SystemConfigOperate systemConfigOperate;

    @Autowired
    private UserWatchDramaRecordMapper userWatchDramaRecordMapper;

    @Transactional
    public Token register(User user) {
        //删除以前的Token, 只能单点登录，防止刷余额
        tokenOperate.delete(user.getId());

        User existUser = userMapper.findByOpenId(user.getOpenId());
        if (existUser == null) {
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
            return tokenOperate.create(user);
        }
        //进行登录
        return tokenOperate.create(existUser);
    }

    @Transactional
    public void addUserAdCount() {
        if (!CurrentUserUtil.isLogin()) {
            return;
        }

        String userId = CurrentUserUtil.currentUserId();
        int count = accountOperate.getCurrentDayUserWatchAdCount(userId, UserAccountRecord.ChangeType.A);

        Integer limit = systemConfigOperate.findIntegerByKey(ADD_ACCOUNT_BALANCE_LIMIT_WHEN_WATCH_AD);
        if (limit == null || limit <count) {
            throw new DramaException(DD004);
        }

        userMapper.updateWatchAdCountsById(userId);

        UserAccountRecord accountRecord = new UserAccountRecord();
        accountRecord.setUserId(userId);
        accountRecord.setChangeAmount(getRandomScore(userId));
        accountRecord.setChangeMessage("观看广告");
        accountRecord.setChangeType(UserAccountRecord.ChangeType.A);
        accountOperate.saveUserAccountRecord(accountRecord);
    }

    @Transactional
    public void inviteUser(String userId) {
        User user = userMapper.findById(userId);
        if (user == null || !user.isValid()) {
            log.warn("user not exist or is invalid");
            return;
        }

        userMapper.updateInviteUserCountById(userId);

        UserAccountRecord accountRecord = new UserAccountRecord();
        accountRecord.setUserId(userId);
        accountRecord.setChangeAmount(getRandomScore(userId));
        accountRecord.setChangeMessage("邀请用户");
        accountRecord.setChangeType(UserAccountRecord.ChangeType.I);
        accountOperate.saveUserAccountRecord(accountRecord);
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

    public List<Long> getUserWatchedDrama() {
        return userMapper.findDistinctDramaIdById(CurrentUserUtil.currentUserId());
    }

    public Map<Long, Integer> getUserWatchRecordGroubyDramaId() {
        List<DramaTotal> watchRecord = userMapper.findWatchRecordByUserId(CurrentUserUtil.currentUserId());
        Map<Long, Integer> result = new HashMap<>();
        if (watchRecord == null) {
            return result;
        }

        for (DramaTotal dramaTotal : watchRecord) {
            result.put(dramaTotal.getId(), dramaTotal.getSum());
        }
        return result;
    }

    public boolean isBindCashOutAccount() {
        User user = getById(CurrentUserUtil.currentUserId());
        return user.isBindCashOutAccount();
    }

    public CashOutBindAccount getUserCashOutBindAccount() {
        User user = getById(CurrentUserUtil.currentUserId());
        return user.toCashOutBindAccount();
    }

    @Transactional
    public BigDecimal watchDrama(UserWatchDramaParam param) {
        if (!CurrentUserUtil.isLogin()) {
            return BigDecimal.ZERO;
        }

        String userId = CurrentUserUtil.currentUserId();
        int count = userWatchDramaRecordMapper.findByKey(userId, param.getDramaId(), param.getDramaIndex());
        if (count > 0) {
            log.info("duplicate watch");
            return BigDecimal.ZERO;
        }

        //加观看记录
        UserWatchDramaRecord record = param.toUserWatchDramaRecord(userId);
        userWatchDramaRecordMapper.insert(record);

        //加积分
        BigDecimal decimal = getRandomScore(userId);
        UserAccountRecord accountRecord = param.toUserAccountRecord(decimal, userId);
        accountOperate.saveUserAccountRecord(accountRecord);
        return decimal;
    }

    public Integer getUserLevel(String userId) {
        User user = getById(userId);
        Integer watchAdCounts = user.getWatchAdCounts();
        Integer addCountsPerLevel = systemConfigOperate.findIntegerByKey(NEED_AD_COUNTS_PER_LEVEL);
        return watchAdCounts / addCountsPerLevel + 1;
    }

    public BigDecimal getRandomScore(String userId) {
        Integer userLevel = getUserLevel(userId);
        double amount = Math.random() * userLevel * 10;
        BigDecimal result = BigDecimal.valueOf(amount);
        return result.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void signIn() {
        if (!CurrentUserUtil.isLogin()) {
            throw new DramaException(DU004);
        }

        String userId = CurrentUserUtil.currentUserId();
        int count = accountOperate.getCurrentDayUserWatchAdCount(userId, UserAccountRecord.ChangeType.S);
        if (count > 0) {
            throw new DramaException(DU005);
        }

        UserAccountRecord accountRecord = new UserAccountRecord();
        accountRecord.setUserId(userId);
        accountRecord.setChangeAmount(getRandomScore(userId));
        accountRecord.setChangeMessage("签到");
        accountRecord.setChangeType(UserAccountRecord.ChangeType.S);
        accountOperate.saveUserAccountRecord(accountRecord);
    }
}
