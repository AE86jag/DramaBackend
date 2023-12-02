package com.spmystery.episode.account.mapper;

import com.spmystery.episode.account.entity.UserAccountRecord;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;


@Mapper
public interface UserAccountRecordMapper {

    int insert(UserAccountRecord userAccountRecord);

    BigDecimal findSumByUserId(String userId);
}
