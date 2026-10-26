package com.cinema.mapper;

import com.cinema.entity.SetmealGood;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealGoodMapper {

    /**
     * 插入对应套餐菜品
     * @param setmealGoods
     */
    static void insertBatch(List<SetmealGood> setmealGoods) {
    }
}
