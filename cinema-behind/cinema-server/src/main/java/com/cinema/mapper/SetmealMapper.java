package com.cinema.mapper;

import com.cinema.annotation.AutoFill;
import com.cinema.dto.SetmealPageQueryDTO;
import com.cinema.entity.Setmeal;
import com.cinema.enumeration.OperationType;
import com.cinema.vo.SetmealVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 通过id查询套餐内商品
     *
     * @param goodIds
     * @return
     */
    List<Long> getSetmealIdsByGoodIds(List<Long> goodIds);

    /**
     * 插入套餐
     * @param setmeal
     */
    @AutoFill(value= OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);


    /**
     * 通过id查询套餐
     * @param id
     * @return
     */
    @Select("select * from setmeal where id=#{id}")
    Setmeal getById(Long id);

    /**
     * 批量删除套餐
     * @param ids
     * @return
     */
    void deleteBatch(List<Long> ids);

}
