package com.cinema.mapper;

import com.cinema.entity.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GoodMapper {

    /**
     * 新增商品
     * @param good
     */
    void save(Good good);

    /**
     * 通过id查询商品
     * @param id
     * @return
     */
    @Select("select * from good where id =#{id}")
    Good getGoodById(String id);
}
