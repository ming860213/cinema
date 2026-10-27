package com.cinema.mapper;

import com.cinema.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 通过微信授权码寻找用户
     * @param openid
     * @return
     */
    User getByOpenid(String openid);

    /**
     * 新增用户
     * @param user
     */
    void insert(User user);
}
