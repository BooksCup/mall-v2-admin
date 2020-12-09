package com.bc.wd.service;

import com.bc.wd.entity.model.OrderModel;
import com.bc.wd.entity.model.StoreConfigModel;
import com.bc.wd.mapper.OrderMapper;
import com.bc.wd.mapper.StoreConfigMapper;
import com.bc.wd.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.bouncycastle.asn1.ua.DSTU4145NamedCurves.params;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 11:47
 **/
@Service
public class StoreConfigService {

    @Autowired
    private StoreConfigMapper storeConfigMapper;

    public Result getList(String storeId) {
        List<StoreConfigModel> storeConfigModelList = storeConfigMapper.getAllByStoreId(storeId);
        return Result.success(storeConfigModelList);
    }

    public Result update(StoreConfigModel storeConfigModel){
        storeConfigMapper.update(storeConfigModel);
        return Result.success();
    }

}
