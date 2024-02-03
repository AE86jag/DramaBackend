package com.spmystery.episode.user.mapper;

import com.spmystery.episode.account.entity.UserAccountRecord;
import com.spmystery.episode.user.entity.DramaTotal;
import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.user.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserMapper {

    int insert(User user);

    int insertUserRole(UserRole userRole);

    User findByOpenId(String openId);

    User findById(String id);

    int findCountByOpenId(String openId);

    int updateById(User user);

    int updateWatchAdCountsById(String id);

    int updateInviteUserCountById(String id);

    int updateAccountAndNameById(@Param("id") String id, @Param("alipayAccount") String alipayAccount,
                                 @Param("alipayAccountName") String alipayAccountName);

    List<Long> findDistinctDramaIdById(String userId);

    List<DramaTotal> findWatchRecordByUserId(String userId);
}
