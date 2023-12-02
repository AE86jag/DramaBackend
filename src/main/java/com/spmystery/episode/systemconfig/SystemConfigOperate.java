package com.spmystery.episode.systemconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SystemConfigOperate {

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    public SystemConfig findByKey(String key) {
        return systemConfigMapper.findByKey(key);
    }
}
