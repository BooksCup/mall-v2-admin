package com.bc.wd.web;

import com.bc.wd.entity.cons.CommonConstant;
import com.bc.wd.entity.model.SettingSkuKeyModel;
import com.bc.wd.entity.model.SettingSkuValueModel;
import com.bc.wd.service.SettingSkuService;
import com.bc.wd.service.UserService;
import com.bc.wd.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:18
 **/
@RestController
@RequestMapping("settingSku")
public class SettingSkuController {

    @Autowired
    private SettingSkuService settingSkuService;

    @ApiOperation(value = "获取所有", notes = "获取所有")
    @GetMapping("")
    public Result getAll(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId) {
        return settingSkuService.getAll(storeId);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("")
    public Result insert(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId,
            @RequestBody SettingSkuKeyModel settingSkuKeyModel) {
        settingSkuKeyModel.setStoreId(storeId);
        return settingSkuService.insert(settingSkuKeyModel);
    }

    @ApiOperation(value = "新增属性", notes = "新增属性")
    @PostMapping("/key")
    public Result insertKey(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId,
            @RequestBody SettingSkuKeyModel settingSkuKeyModel) {
        settingSkuKeyModel.setStoreId(storeId);
        return settingSkuService.insertKey(settingSkuKeyModel);
    }

    @ApiOperation(value = "新增属性值", notes = "新增属性值")
    @PostMapping("/value")
    public Result insertValue(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId,
            @RequestBody SettingSkuValueModel settingSkuValueModel) {
        settingSkuValueModel.setStoreId(storeId);
        return settingSkuService.insertValue(settingSkuValueModel);
    }
}
