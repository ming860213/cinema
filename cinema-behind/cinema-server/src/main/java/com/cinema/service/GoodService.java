package com.cinema.service;

import com.cinema.dto.GoodDTO;
import com.cinema.dto.GoodPageQueryDTO;
import com.cinema.result.PageResult;
import com.cinema.vo.GoodVO;

public interface GoodService {

    /**
     * 新增商品
     * @param goodDTO
     */
    void save(GoodDTO goodDTO);

    /**
     * 根据id查询商品
     *
     * @param id
     * @return
     */
    GoodVO getGoodByIdWithFlavors(String id);

    /**
     * 商品分页查询
     * @param goodPageQueryDTO
     * @return
     */
    PageResult pageQuery(GoodPageQueryDTO goodPageQueryDTO);
}
