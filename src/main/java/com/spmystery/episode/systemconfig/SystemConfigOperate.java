package com.spmystery.episode.systemconfig;

import com.spmystery.episode.systemconfig.entity.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SystemConfigOperate {

    public static final String USER_REGISTER_REWARD = "user_register_reward";

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    public SystemConfig findByKey(String key) {
        return systemConfigMapper.findByKey(key);
    }
}
