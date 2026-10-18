package com.cinema.service.impl;

import com.cinema.dto.GoodDTO;
import com.cinema.entity.Good;
import com.cinema.mapper.GoodMapper;
import com.cinema.service.GoodService;
import com.cinema.service.SetMealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;
    /**
     * 新增商品
     * @param goodDTO
     */
    public void save(GoodDTO goodDTO) {
        Good good = new Good();
        BeanUtils.copyProperties(goodDTO, good);
        goodMapper.save(good);
    }
}
