package com.spmystery.episode.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spmystery.episode.exception.DramaException;
import com.spmystery.episode.systemconfig.SystemConfigOperate;
import com.spmystery.episode.systemconfig.entity.CashOutCondition;
import com.spmystery.episode.systemconfig.entity.SystemConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.spmystery.episode.exception.DramaErrorCode.DC002;
import static com.spmystery.episode.systemconfig.SystemConfigOperate.SYSTEM_CASH_OUT_CONDITION_CONFIG;

@Slf4j
@Component
public class CacheLoadRunner implements CommandLineRunner {

    private static Map<Integer, CashOutCondition> cashOutConditionCache = new HashMap<>();

    @Autowired
    private SystemConfigOperate systemConfigOperate;

    @Override
    public void run(String... args) {
        load();
        log.info("提现条件缓存初始化完成");
    }

    public static CashOutCondition getByLevel(Integer level) {
        return cashOutConditionCache.get(level);
    }

    public static List<CashOutCondition> getAll() {
        List<CashOutCondition> cashOutConditions = new ArrayList<>();
        cashOutConditionCache.forEach((key, value) -> {
            cashOutConditions.add(value);
        });
        return cashOutConditions;
    }

    //TODO 线程安全的问题，刷新后，上面查询的方法还是查的旧的
    public void refresh() {
        cashOutConditionCache.clear();
        load();
    }

    public void load() {
        SystemConfig conditionConfig = systemConfigOperate.findByKey(SYSTEM_CASH_OUT_CONDITION_CONFIG);
        if (conditionConfig == null || StringUtils.isEmpty(conditionConfig.getValue())) {
            throw new DramaException(DC002);
        }

        Type type = new TypeToken<List<CashOutCondition>>(){}.getType();
        List<CashOutCondition> cashOutConditions = new Gson().fromJson(conditionConfig.getValue(), type);
        if (CollectionUtils.isEmpty(cashOutConditions)) {
            throw new RuntimeException("提现条件未配置");
        }

        for (CashOutCondition cashOutCondition : cashOutConditions) {

            cashOutConditionCache.put(cashOutCondition.getLevel(), cashOutCondition);
        }
    }


}
