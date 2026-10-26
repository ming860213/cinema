
package com.cinema.service.impl;

import com.cinema.dto.SetmealDTO;
import com.cinema.entity.Setmeal;
import com.cinema.entity.SetmealGood;
import com.cinema.mapper.SetmealGoodMapper;
import com.cinema.mapper.SetmealMapper;
import com.cinema.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealGoodMapper setmealGoodMapper;
    /**
     * 新增套餐
     * @param setmealDTO
     */
    public void save(SetmealDTO setmealDTO) {
        //套餐mapper插入套餐的信息
        Setmeal setmeal = new  Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        //获取套餐id
        setmealMapper.insert(setmeal);
        Long setmealId=setmeal.getId();
        //套餐菜品插入套餐的id
        List<SetmealGood>setmealGoods= setmealDTO.getSetmealGoods();
        for(SetmealGood setmealGood:setmealGoods){
            setmealGood.setSetmealId(setmealId);
        }
        //套餐菜品表插入list信息
        SetmealGoodMapper.insertBatch(setmealGoods);

    }
}
