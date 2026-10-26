package com.cinema.service;

import com.cinema.dto.SetmealDTO;
import com.cinema.dto.SetmealPageQueryDTO;
import com.cinema.result.PageResult;

public interface SetmealService {

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void save(SetmealDTO setmealDTO);


    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);
}
