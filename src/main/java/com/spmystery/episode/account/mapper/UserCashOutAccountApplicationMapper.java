package com.spmystery.episode.account.mapper;

import com.spmystery.episode.account.entity.UserCashOutAccountApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserCashOutAccountApplicationMapper {

    int insert(UserCashOutAccountApplication userCashOutAccountApplication);

    List<UserCashOutAccountApplication> findByUserId(String userId);

    int updateApproveById(UserCashOutAccountApplication userCashOutAccountApplication);

    int findCountByUserIdAndStatus(@Param("userId") String userId, @Param("statuses") List<Integer> statuses);

    int findCountByUserIdAndLevel(@Param("userId") String userId, @Param("level") Integer level);

    BigDecimal findSumInDoingByUserId(String userId);

    UserCashOutAccountApplication findById(String id);
}
