package com.spmystery.episode.account.mapper;

import com.spmystery.episode.account.entity.UserCashOutAccountApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCashOutAccountApplicationMapper {

    int insert(UserCashOutAccountApplication userCashOutAccountApplication);

    List<UserCashOutAccountApplication> findByUserId(String userId);

    int updateApproveById(UserCashOutAccountApplication userCashOutAccountApplication);
}
