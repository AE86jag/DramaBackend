package com.spmystery.episode.user.mapper;

import com.spmystery.episode.user.entity.User;
import com.spmystery.episode.user.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    int insert(User user);

    int insertUserRole(UserRole userRole);

    User findByOpenId(String openId);

    int findCountByOpenId(String openId);

    int updateById(User user);
}
