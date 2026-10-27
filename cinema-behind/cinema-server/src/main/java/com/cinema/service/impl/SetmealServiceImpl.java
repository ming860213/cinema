
package com.cinema.service.impl;

import com.cinema.constant.MessageConstant;
import com.cinema.constant.StatusConstant;
import com.cinema.dto.SetmealDTO;
import com.cinema.dto.SetmealPageQueryDTO;
import com.cinema.entity.Setmeal;
import com.cinema.entity.SetmealGood;
import com.cinema.exception.DeletionNotAllowedException;
import com.cinema.mapper.SetmealGoodMapper;
import com.cinema.mapper.SetmealMapper;
import com.cinema.result.PageResult;
import com.cinema.service.SetmealService;
import com.cinema.vo.SetmealVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        setmealGoodMapper.insertBatch(setmealGoods);

    }

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    public PageResult page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        Page<SetmealVO>page=setmealMapper.pageQuery(setmealPageQueryDTO);

        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 根据id查询套餐
     * @param id
     */
    public SetmealVO getByIdWithGood(Long id) {
        //先从套餐表中取出对应套餐数据
        Setmeal setmeal=setmealMapper.getById(id);
        SetmealVO setmealVO=new SetmealVO();
        BeanUtils.copyProperties(setmeal,setmealVO);
        //再从套餐商品表中取出对应数据List<Setmeal>
        List<SetmealGood>setmealGoods=setmealGoodMapper.getBySetmealId(id);
        setmealVO.setSetmealGoods(setmealGoods);
        return setmealVO;
    }

    /**
     * 批量删除套餐
     * @param ids
     */
    @Transactional
    public void delete(List<Long> ids) {
        //先遍历，若套餐在起售中则抛异常
        for(Long id:ids){
            Setmeal setmeal =setmealMapper.getById(id);
            if(setmeal.getStatus()== StatusConstant.ENABLE){
                 throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        }
        setmealMapper.deleteBatch(ids);
        //删套餐商品表
        setmealGoodMapper.deleteBatch(ids);
    }

    /**
     * 套餐起售或停售
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        Setmeal setmeal=setmealMapper.getById(id);
        if(setmeal.getStatus()== StatusConstant.ENABLE){
            setmeal.setStatus(status);
            setmealMapper.update(setmeal);
        }
    }

    /**
     * 修改套餐
     * @param setmealDTO
     */
    public void update(SetmealDTO setmealDTO) {
        //先获取setmeal对象和对应套餐商品的列表
        Setmeal setmeal = new  Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        List<SetmealGood>setmealGoods= setmealDTO.getSetmealGoods();

        //给套餐商品set套餐id
        for(SetmealGood setmealGood:setmealGoods){
            setmealGood.setSetmealId(setmeal.getId());
        }
        //更新套餐表数据
        setmealMapper.update(setmeal);
        //删套餐商品表数据，再插入
        List<Long>setmealId=new ArrayList<>();
        setmealId.add(setmeal.getId());
        setmealGoodMapper.deleteBatch(setmealId);
        setmealGoodMapper.insertBatch(setmealGoods);


    }
}
