package com.bc.wd.web;

import com.bc.wd.entity.cons.CommonConstant;
import com.bc.wd.entity.model.GoodsModel;
import com.bc.wd.service.GoodsService;
import com.bc.wd.utils.Result;
import com.bc.wd.utils.ValidationGroups;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:18
 **/
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/page")
    public Result getPage(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId,
            @RequestBody Map<String, Object> params,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("page") Integer page) {
        params.put(CommonConstant.HEADER_STORE_ID, storeId);
        return goodsService.getPage(params, pageSize, page);
    }

    @ApiOperation(value = "详情", notes = "查询")
    @GetMapping("/{id}")
    public Result getDetail(
            @PathVariable("id") String id) {
        return goodsService.getDetail(id);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("")
    public Result insertGoods(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId,
            @RequestBody @Validated(ValidationGroups.New.class) GoodsModel goodsModel) {
        goodsModel.setStoreId(storeId);
        return goodsService.insert(goodsModel);
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("/{id}")
    public Result updateGoods(
            @PathVariable("id") String id,
            @RequestBody @Validated(ValidationGroups.New.class) GoodsModel goodsModel) {
        goodsModel.setId(id);
        return goodsService.update(goodsModel);
    }

    @ApiOperation(value = "上架", notes = "上架")
    @PutMapping("/{id}/up")
    public Result upGoods(@PathVariable("id") String goodsId) {
        return goodsService.upGoods(goodsId);
    }

    @ApiOperation(value = "下架", notes = "下架")
    @PutMapping("/{id}/down")
    public Result downGoods(@PathVariable("id") String goodsId) {
        return goodsService.downGoods(goodsId);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/{id}")
    public Result deleteGoods(@PathVariable("id") String goodsId) {
        return goodsService.deleteGoods(goodsId);
    }
}
