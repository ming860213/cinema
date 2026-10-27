package com.cinema.controller.admin;

import com.cinema.dto.SetmealDTO;
import com.cinema.dto.SetmealPageQueryDTO;
import com.cinema.mapper.SetmealMapper;
import com.cinema.result.PageResult;
import com.cinema.result.Result;
import com.cinema.service.SetmealService;
import com.cinema.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "套餐相关接口")
@RequestMapping("/admin/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        setmealService.save(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "套餐分页查询")
    public Result<PageResult>page( SetmealPageQueryDTO setmealPageQueryDTO){
        PageResult pageResult=setmealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id){
        SetmealVO setmealVO =setmealService.getByIdWithGood(id);
        return Result.success(setmealVO);
    }


    /**
     * 批量删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除套餐")
    public Result deleteBatch(@RequestBody List<Long> ids){
        setmealService.delete(ids);
        return Result.success();
    }

    /**
     * 套餐起售或停售
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/stastus/{status}")
    @ApiOperation(value = "套餐起售或停售")
    public Result startOrStop(@PathVariable Integer status,Long id){
        setmealService.startOrStop(status,id);
        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO){
        setmealService.update(setmealDTO);
        return Result.success();
    }
}
