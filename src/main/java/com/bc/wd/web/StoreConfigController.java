package com.bc.wd.web;

import com.bc.wd.entity.cons.CommonConstant;
import com.bc.wd.entity.model.StoreConfigModel;
import com.bc.wd.service.StoreConfigService;
import com.bc.wd.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:18
 **/
@RestController
@RequestMapping("storeConfig")
public class StoreConfigController {

    @Autowired
    private StoreConfigService storeConfigService;

    @ApiOperation(value = "查询", notes = "查询")
    @GetMapping("")
    public Result getPage(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId) {
        return storeConfigService.getList(storeId);
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("{/id}")
    public Result updateGoods(
            @RequestBody StoreConfigModel StoreConfigModel,
            @PathVariable("id") String id) {
        StoreConfigModel.setId(id);
        return storeConfigService.update(StoreConfigModel);
    }
}
