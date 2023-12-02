package com.spmystery.episode.systemconfig;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SystemConfigMapper {

    int save(SystemConfig systemConfig);

    SystemConfig findByKey(String key);

    int updateValueByKey(SystemConfig config);

    List<SystemConfig> findByGroups();

    Integer findMaxValueOfFocus();

    int deleteById(String id);

    List<SystemConfig> findFocus();


    int deleteWebsite();
}
