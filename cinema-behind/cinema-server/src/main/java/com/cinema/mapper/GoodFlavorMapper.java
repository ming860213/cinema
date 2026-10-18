package com.cinema.mapper;

import com.cinema.entity.GoodFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodFlavorMapper {
    /**
     * 根据商品id查询口味
     * @param goodId
     * @return
     */
    @Select("select * from good_flavor where good_id =#{goodId}")
    List<GoodFlavor> getByGoodId(String goodId);

    /**
     * 口味新增
     * @param goodFlavors
     */
    void saveFlavors(List<GoodFlavor> goodFlavors);
}
