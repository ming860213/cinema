package com.cinema.mapper;

import com.cinema.dto.GoodPageQueryDTO;
import com.cinema.entity.Good;
import com.github.pagehelper.Page;
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

    /**
     * 商品分页查询
     * @param goodPageQueryDTO
     * @return
     */
    Page<Good> pageQuery(GoodPageQueryDTO goodPageQueryDTO);
}
