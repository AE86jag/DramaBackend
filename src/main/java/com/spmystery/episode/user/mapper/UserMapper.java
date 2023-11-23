package com.spmystery.episode.user.mapper;

import com.spmystery.episode.user.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    int insert(User user);

    User findByOpenId(String openId);

    int findCountByOpenId(String openId);

    int updateById(User user);
}
