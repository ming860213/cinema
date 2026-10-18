package com.cinema.mapper;

import com.cinema.annotation.AutoFill;
import com.cinema.dto.GoodPageQueryDTO;
import com.cinema.entity.Good;
import com.cinema.enumeration.OperationType;
import com.cinema.vo.GoodVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodMapper {

    /**
     * 新增商品
     * @param good
     */
    @AutoFill(value = OperationType.INSERT)
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
    Page<GoodVO> pageQuery(GoodPageQueryDTO goodPageQueryDTO);

    /**
     * 根据分类id查询商品
     * @param good
     * @return
     */
    List<Good> getGoodByCategoryId(Good good);

    /**
     * 修改菜品
     * @param good
     */
    void update(Good good);
}
