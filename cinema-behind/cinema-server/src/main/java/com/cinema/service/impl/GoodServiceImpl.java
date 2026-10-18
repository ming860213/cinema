package com.cinema.service.impl;

import com.cinema.constant.StatusConstant;
import com.cinema.dto.GoodDTO;
import com.cinema.dto.GoodPageQueryDTO;
import com.cinema.entity.Good;
import com.cinema.entity.GoodFlavor;
import com.cinema.mapper.GoodFlavorMapper;
import com.cinema.mapper.GoodMapper;
import com.cinema.result.PageResult;
import com.cinema.service.GoodService;
import com.cinema.vo.GoodVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private GoodFlavorMapper goodFlavorMapper;
    /**
     * 新增商品
     * @param goodDTO
     */
    @Transactional
    public void saveWithFlavors(GoodDTO goodDTO) {
        Good good = new Good();
        BeanUtils.copyProperties(goodDTO, good);
        goodMapper.save(good);//现在商品进入数据库拥有id
        Long goodId = good.getId();
        List<GoodFlavor> goodFlavors = goodDTO.getFlavors();
        if(goodFlavors!=null&&goodFlavors.size()>0){
            for(GoodFlavor flavor:goodFlavors){
                flavor.setGoodId(goodId);
            }
        }
        goodFlavorMapper.saveFlavors(goodFlavors);

    }

    /**
     * 通过id查询商品
     *
     * @param id
     * @return
     */
    public GoodVO getGoodByIdWithFlavors(String id) {
        Good good=goodMapper.getGoodById(id);
        List<GoodFlavor> flavors =goodFlavorMapper.getByGoodId(id);
        GoodVO goodVO=new GoodVO();
        BeanUtils.copyProperties(good,goodVO);
        goodVO.setFlavors(flavors);
        return goodVO;
    }

    /**
     * 商品分页查询
     * @param goodPageQueryDTO
     * @return
     */
    public PageResult pageQuery(GoodPageQueryDTO goodPageQueryDTO) {
        PageHelper.startPage(goodPageQueryDTO.getPage(),goodPageQueryDTO.getPageSize());

        Page<GoodVO>page =goodMapper.pageQuery(goodPageQueryDTO);
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    /**
     * 根据分类id查询商品
     * @param categoryId
     * @return
     */
    public List<Good> getByCategoryId(Long categoryId) {
        Good good=Good.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();
        return goodMapper.getGoodByCategoryId(good);
    }

    /**
     * 修改菜品
     * @param goodDTO
     */
    @Transactional
    public void updateWithFlavors(GoodDTO goodDTO) {
        Good good = new Good();
        BeanUtils.copyProperties(goodDTO, good);
        goodMapper.update(good);

        //口味修改
        List<Long>ids=new ArrayList<>();
        ids.add(good.getId());
        List<GoodFlavor> goodFlavors = goodDTO.getFlavors();
        if(goodFlavors!=null&&goodFlavors.size()>0){
          for(GoodFlavor flavor:goodFlavors){
              flavor.setGoodId(good.getId());
          }
        }
        goodFlavorMapper.deleteBatchByGoodId(ids);
        goodFlavorMapper.saveFlavors(goodFlavors);
    }


}
