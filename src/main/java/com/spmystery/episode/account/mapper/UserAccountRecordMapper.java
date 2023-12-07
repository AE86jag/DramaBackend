package com.spmystery.episode.account.mapper;

import com.spmystery.episode.account.entity.UserAccountRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


@Mapper
public interface UserAccountRecordMapper {

    int insert(UserAccountRecord userAccountRecord);

    BigDecimal findSumByUserId(String userId);

    int findCountByUserIdAndType(@Param("userId") String userId,
                                 @Param("changeType")UserAccountRecord.ChangeType changeType);
}
