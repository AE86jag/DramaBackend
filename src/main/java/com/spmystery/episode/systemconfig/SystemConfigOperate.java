package com.spmystery.episode.systemconfig;

import com.spmystery.episode.exception.DramaException;
import com.spmystery.episode.systemconfig.entity.SystemConfig;
import com.spmystery.episode.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.spmystery.episode.exception.DramaErrorCode.DO002;
import static com.spmystery.episode.exception.DramaErrorCode.DO003;

@Component
public class SystemConfigOperate {

    public static final String USER_REGISTER_REWARD = "user_register_reward";
    public static final String INVITE_USER_REWARD = "invite_user_reward";
    public static final String USER_WATCHING_ADVERTISEMENTS = "user_watching_advertisements";
    public static final String USER_WATCHING_ONE_DRAMA = "user_watching_one_drama";
    public static final String USER_CASH_OUT_COUNT_PER_DAY = "user_cash_out_count_per_day";
    public static final String SYSTEM_CASH_OUT_CONDITION_CONFIG = "system_cash_out_condition_config";
    public static final String NEED_AD_COUNTS_PER_LEVEL = "need_ad_counts_per_level";
    public static final String ADD_ACCOUNT_BALANCE_LIMIT_WHEN_WATCH_AD = "add_account_balance_limit_when_watch_ad";

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    public SystemConfig findByKey(String key) {
        SystemConfig config = systemConfigMapper.findByKey(key);
        if (config == null) {
            throw new DramaException(DO002);
        }
        return config;
    }

    public Integer findIntegerByKey(String key) {
        SystemConfig config = systemConfigMapper.findByKey(key);
        if (config == null) {
            throw new DramaException(DO002);
        }

        String value = config.getValue();
        if (StringUtil.isEmpty(value)) {
            throw new DramaException(DO003);
        }

        return Integer.valueOf(value);
    }
}
