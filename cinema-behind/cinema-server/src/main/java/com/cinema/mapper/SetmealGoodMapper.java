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
    void insertBatch(List<SetmealGood> setmealGoods) {
    }

    /**
     * 通过套餐id查询套餐内商品
     * @param setmealId
     * @return
     */
    List<SetmealGood> getBySetmealId(Long setmealId);

    /**
     * 批量删除套餐商品
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
