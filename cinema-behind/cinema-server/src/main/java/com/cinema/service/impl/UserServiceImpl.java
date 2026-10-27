package com.cinema.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cinema.constant.MessageConstant;
import com.cinema.dto.UserLoginDTO;
import com.cinema.entity.User;
import com.cinema.exception.LoginFailedException;
import com.cinema.mapper.UserMapper;
import com.cinema.properties.WeChatProperties;
import com.cinema.service.UserService;
import com.cinema.utils.HttpClientUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public static final String WX_LOGIN="https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatProperties weChatProperties;


    public User login(UserLoginDTO userLoginDTO) {
        String openid=getOpenid(userLoginDTO.getCode());
        //若调用接口返回的openId为空，则抛异常
        if(openid==null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        //先查数据库，若有则返回，没用就注册
        User user=userMapper.getByOpenid(openid);
        if(user==null){
            user=User.builder()
                    .openid(openid)
                    .build();
            userMapper.insert(user);
        }
        return user;
    }

    public String getOpenid(String code){
        HashMap<String,String> map=new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        //调用接口
        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        //解析成json对象
        JSONObject jsonObject = JSONObject.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
