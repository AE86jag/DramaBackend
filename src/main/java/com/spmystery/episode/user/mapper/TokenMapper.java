package com.spmystery.episode.user.mapper;

import com.spmystery.episode.user.entity.Token;
import com.spmystery.episode.user.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TokenMapper {

    int insert(Token token);

    Token findById(String id);

    int deleteByUserId(String userId);
}
