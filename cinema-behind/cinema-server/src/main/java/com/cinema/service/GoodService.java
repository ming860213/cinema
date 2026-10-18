package com.cinema.service;

import com.cinema.dto.GoodDTO;
import com.cinema.dto.GoodPageQueryDTO;
import com.cinema.entity.Good;
import com.cinema.result.PageResult;
import com.cinema.vo.GoodVO;

import java.util.List;

public interface GoodService {

    /**
     * 新增商品
     * @param goodDTO
     */
    void saveWithFlavors(GoodDTO goodDTO);

    /**
     * 根据id查询商品
     *
     * @param id
     * @return
     */
    GoodVO getGoodByIdWithFlavors(Long id);

    /**
     * 商品分页查询
     * @param goodPageQueryDTO
     * @return
     */
    PageResult pageQuery(GoodPageQueryDTO goodPageQueryDTO);

    /**
     * 根据分类id查询商品
     * @param categoryId
     * @return
     */
    List<Good> getByCategoryId(Long categoryId);

    /**
     * 修改菜品
     * @param goodDTO
     */
    void updateWithFlavors(GoodDTO goodDTO);

    /**
     * 删除商品
     * @param goodIds
     */
    void deleteBatch(List<Long> goodIds);

    /**
     * 改变商品的起售或禁售状态
     * @param id,status
     */
    void updateStatusByDishId(Integer status, Long id);
}
