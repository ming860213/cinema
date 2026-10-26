package com.cinema.controller.admin;

import com.cinema.dto.SetmealDTO;
import com.cinema.result.Result;
import com.cinema.service.SetmealService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "套餐相关接口")
@RequestMapping("/admin/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    public Result save(SetmealDTO setmealDTO) {
        setmealService.save(setmealDTO);
        return Result.success();
    }
}
