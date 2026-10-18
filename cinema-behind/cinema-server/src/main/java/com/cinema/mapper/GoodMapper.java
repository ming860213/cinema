package com.cinema.mapper;

import com.cinema.entity.Good;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodMapper {

    /**
     * 新增商品
     * @param good
     */
    void save(Good good);
}
