package com.cinema.controller.admin;

import com.cinema.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


@RestController("adminCinemaController")
@Api(tags = "影院相关接口")
@Slf4j
@RequestMapping("/admin/cinema")
public class CinemaController {
    @Autowired
    private RedisTemplate redisTemplate;
    public static final String STATUS="CINEMA_STATUS";

    /**
     * 设置影院营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation(value = "设置影院营业状态")
    public Result setStatus(@PathVariable Integer status) {
        log.info("影院的营业状态为：{}",status ==1? "营业中":"休息中");
        redisTemplate.opsForValue().set(STATUS,status);
        return  Result.success();
    }

    @GetMapping("/status")
    @ApiOperation(value = "获取影院营业状态")
    public Result<Integer> getStatus() {
         Integer status = (Integer) redisTemplate.opsForValue().get(STATUS);
         log.info("店铺的营业状态为:{}",status ==1 ? "营业中":"打烊中");
         return Result.success(status);
    }
}
