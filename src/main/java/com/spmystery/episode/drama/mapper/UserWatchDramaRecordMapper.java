package com.spmystery.episode.drama.mapper;

import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.drama.entity.UserWatchDramaRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


@Mapper
public interface UserWatchDramaRecordMapper {

    int insert(UserWatchDramaRecord userWatchDramaRecord);

    int findCountByUserId(String userId);

    int findByKey(@Param("userId") String userId, @Param("dramaId") Long dramaId, @Param("dramaIndex") Integer dramaIndex);
}
