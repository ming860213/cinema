package com.cinema.controller.admin;

import com.cinema.dto.GoodDTO;
import com.cinema.entity.Good;
import com.cinema.result.Result;
import com.cinema.service.GoodService;
import com.cinema.vo.GoodVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    @ApiOperation(value = "新增商品")
    public Result addGood(@RequestBody GoodDTO goodDTO) {
        goodService.save(goodDTO);
        return Result.success();
    }

    //TODO 需要加入口味
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询商品")
    public Result<GoodVO> getGoodById(@PathVariable String id) {
        log.info("查询商品id：{}",id);
        GoodVO goodVO=goodService.getGoodByIdWithFalvors(id);
        return Result.success(goodVO);
    }
}
