package com.cinema.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 通过id查询套餐内商品
     *
     * @param goodIds
     * @return
     */
    List<Long> getSetMealIdsByGoodIds(List<Long> goodIds);
}
