package com.tingyun.center.mapper;

import com.tingyun.soso.service.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查重
     */
    int count(User user);

    List<User> selectByConditions(User user);
}