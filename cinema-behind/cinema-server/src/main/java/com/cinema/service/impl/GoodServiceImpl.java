package com.cinema.service.impl;

import com.cinema.dto.GoodDTO;
import com.cinema.entity.Good;
import com.cinema.entity.GoodFlavor;
import com.cinema.mapper.GoodMapper;
import com.cinema.service.GoodService;
import com.cinema.vo.GoodVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 通过id查询商品
     *
     * @param id
     * @return
     */
    public GoodVO getGoodById(String id) {
        Good good=goodMapper.getGoodById(id);
        List<GoodFlavor> flavors =goodFlavorMapper.getByGoodId(id);
        GoodVO goodVO=new GoodVO();
        BeanUtils.copyProperties(good,goodVO);
        goodVO.setFlavors(flavors);
        return goodVO;
    }
}
