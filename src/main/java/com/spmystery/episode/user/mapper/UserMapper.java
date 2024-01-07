package com.spmystery.episode.user.mapper;

import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.user.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {

    int insert(User user);

    int insertUserRole(UserRole userRole);

    User findByOpenId(String openId);

    User findById(String id);

    int findCountByOpenId(String openId);

    int updateById(User user);

    int updateWatchAdCountsById(String id);

    int updateAccountAndNameById(@Param("id") String id, @Param("alipayAccount") String alipayAccount,
                                 @Param("alipayAccountName") String alipayAccountName);
}
