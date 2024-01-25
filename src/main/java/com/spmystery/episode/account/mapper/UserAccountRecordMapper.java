package com.spmystery.episode.account.mapper;

import com.spmystery.episode.account.entity.UserAccountRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


@Mapper
public interface UserAccountRecordMapper {

    int insert(UserAccountRecord userAccountRecord);

    BigDecimal findSumByUserId(String userId);

    int findCountByUserIdAndType(@Param("userId") String userId,
                                 @Param("changeType")UserAccountRecord.ChangeType changeType);

    List<UserAccountRecord> findUserAccountRecordsById(String userId);

    int findCurrentDayCountByUserIdAndChangeType(@Param("userId") String userId,
                                                @Param("changeType") UserAccountRecord.ChangeType changeType);
}
