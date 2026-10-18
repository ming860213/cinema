package com.cinema.controller.admin;

import com.cinema.dto.GoodDTO;
import com.cinema.dto.GoodPageQueryDTO;
import com.cinema.entity.Good;
import com.cinema.result.PageResult;
import com.cinema.result.Result;
import com.cinema.service.GoodService;
import com.cinema.vo.GoodVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/good")
@Api(tags ="商品相关接口")
@Slf4j
public class GoodController {
    @Autowired
    private GoodService goodService;

    /**
     * 新增商品
     * @param goodDTO
     * @return
     */
    //TODO 商品口味没添加
    @PostMapping
    @ApiOperation(value = "新增商品")
    public Result addGoodWithFlavors(@RequestBody GoodDTO goodDTO) {
        goodService.saveWithFlavors(goodDTO);
        return Result.success();
    }

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询商品")
    public Result<GoodVO> getGoodById(@PathVariable String id) {
        log.info("查询商品id：{}",id);
        GoodVO goodVO=goodService.getGoodByIdWithFlavors(id);
        return Result.success(goodVO);
    }

    /**
     * 商品分页查询
     * @param goodPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "商品分页查询")
    public Result<PageResult> pageQuery(@RequestBody GoodPageQueryDTO goodPageQueryDTO) {
        PageResult pageResult=goodService.pageQuery(goodPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据分类id查询商品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "根据分类id查询商品")
    public Result<List<Good>> getByCategoryId(Long categoryId) {
        List<Good>goods =goodService.getByCategoryId(categoryId);
        return Result.success(goods);
    }
}
