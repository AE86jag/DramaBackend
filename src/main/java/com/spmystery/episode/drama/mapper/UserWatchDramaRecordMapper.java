package com.spmystery.episode.drama.mapper;

import com.spmystery.episode.account.entity.UserAccountRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


@Mapper
public interface UserWatchDramaRecordMapper {

    int insert(UserAccountRecord userAccountRecord);

    int findCountByUserId(String userId);
}
