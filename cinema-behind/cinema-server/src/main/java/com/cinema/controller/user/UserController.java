package com.cinema.controller.user;

import com.cinema.constant.JwtClaimsConstant;
import com.cinema.dto.UserLoginDTO;
import com.cinema.entity.User;
import com.cinema.properties.JwtProperties;
import com.cinema.result.Result;
import com.cinema.service.UserService;
import com.cinema.utils.JwtUtil;
import com.cinema.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Api(tags = "用户端接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result<UserLoginVO>login(@RequestBody UserLoginDTO userLoginDTO){

        User user=userService.login(userLoginDTO);

        //生成jwt令牌
        Map<String,Object> claims=new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        String token=JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        //构建userLoginVO对象
        UserLoginVO userLoginVO=UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }
}
