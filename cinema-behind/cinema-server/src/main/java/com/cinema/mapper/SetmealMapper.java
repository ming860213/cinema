package com.cinema.mapper;

import com.cinema.annotation.AutoFill;
import com.cinema.entity.Setmeal;
import com.cinema.enumeration.OperationType;
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

    /**
     * 插入套餐
     * @param setmeal
     */
    @AutoFill(value= OperationType.INSERT)
    void insert(Setmeal setmeal);
}
