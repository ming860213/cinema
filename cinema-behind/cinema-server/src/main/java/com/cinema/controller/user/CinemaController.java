package com.cinema.controller.user;

import com.cinema.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController("userCinemaController")
@RequestMapping("/user/cinema")
@Slf4j
@Api(tags = "用户端影院接口")
public class CinemaController {
    @Autowired
    private RedisTemplate redisTemplate;

    public static final String STATUS="CINEMA_STATUS";

    /**
     * 获取影院营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation(value = "获取影院营业状态")
    public Result<Integer> getStatus(){
        Integer status =(Integer) redisTemplate.opsForValue().get(STATUS);
        log.info("影院营业状态为：{}",status==1 ? "营业中":"休息中");
        return  Result.success(status);
    }
}
