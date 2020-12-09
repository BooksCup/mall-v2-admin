package com.bc.wd.service;

import com.alibaba.fastjson.JSONObject;
import com.bc.wd.entity.cons.CommonConstant;
import com.bc.wd.entity.model.GoodsImageModel;
import com.bc.wd.entity.model.GoodsLabelModel;
import com.bc.wd.entity.model.GoodsModel;
import com.bc.wd.entity.model.GoodsSkuModel;
import com.bc.wd.mapper.*;
import com.bc.wd.utils.CodeMsg;
import com.bc.wd.utils.CommonUtils;
import com.bc.wd.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 11:47
 **/
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    @Autowired
    private GoodsLabelMapper goodsLabelMapper;

    @Autowired
    private SettingSkuMapper settingSkuMapper;

    public Result getPage(Map<String, Object> params, Integer pageSize, Integer page) {
        PageHelper.startPage(page, pageSize);
        Page<GoodsModel> goodsPage = goodsMapper.getPage(params);
        return Result.success(goodsPage.getResult(), goodsPage.getTotal());
    }

    public Result getDetail(String id) {
        GoodsModel goodsModel = goodsMapper.getDetail(id);
        List<GoodsImageModel> goodsImageModelList = goodsImageMapper.getByGoodsId(id);
        List<GoodsSkuModel> goodsSkuModelList = goodsSkuMapper.getByGoodsId(id);
        List<GoodsLabelModel> goodsLabelList = goodsLabelMapper.getByGoodsId(id);
        goodsModel.setGoodsImageModelList(goodsImageModelList);
        goodsModel.setGoodsSkuModelList(goodsSkuModelList);
        goodsModel.setGoodsLabelModelList(goodsLabelList);
        return Result.success(goodsModel);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result insert(GoodsModel goodsModel) {
        goodsModel.setId(CommonUtils.generateID());
        Integer maxSort = goodsMapper.getMaxSort(goodsModel.getStoreId());
        if(maxSort == null || maxSort.intValue() == 0){
            maxSort = 1;
        }else{
            maxSort += 1;
        }
        goodsModel.setSort(maxSort);
        goodsMapper.insert(goodsModel);
        if (CollectionUtils.isNotEmpty(goodsModel.getGoodsSkuModelList())) {
            int i = 1;
            for (GoodsSkuModel m : goodsModel.getGoodsSkuModelList()) {
                m.setId(CommonUtils.generateID());
                m.setSort(i++);
                m.setGoodsId(goodsModel.getId());
            }
            goodsSkuMapper.insertList(goodsModel.getGoodsSkuModelList());
        }
        if (CollectionUtils.isNotEmpty(goodsModel.getGoodsImageModelList())) {
            int i = 1;
            for (GoodsImageModel m : goodsModel.getGoodsImageModelList()) {
                m.setId(CommonUtils.generateID());
                m.setSort(i++);
                m.setGoodsId(goodsModel.getId());
            }
            goodsImageMapper.insertList(goodsModel.getGoodsImageModelList());
        }
        if (CollectionUtils.isNotEmpty(goodsModel.getGoodsLabelModelList())) {
            int i = 1;
            for (GoodsLabelModel m : goodsModel.getGoodsLabelModelList()) {
                m.setId(CommonUtils.generateID());
                m.setSort(i++);
                m.setGoodsId(goodsModel.getId());
            }
            goodsLabelMapper.insertList(goodsModel.getGoodsLabelModelList());
        }

        setUsedSku(goodsModel);
        return Result.success(goodsModel.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public Result update(GoodsModel goodsModel) {
        goodsMapper.update(goodsModel);
        goodsSkuMapper.deleteByGoodsId(goodsModel.getId());
        goodsImageMapper.deleteByGoodsId(goodsModel.getId());
        goodsLabelMapper.deleteByGoodsId(goodsModel.getId());
        if (CollectionUtils.isNotEmpty(goodsModel.getGoodsSkuModelList())) {
            int i = 1;
            for (GoodsSkuModel m : goodsModel.getGoodsSkuModelList()) {
                if (StringUtils.isEmpty(m.getId())) {
                    m.setId(CommonUtils.generateID());
                    m.setSort(i++);
                    m.setGoodsId(goodsModel.getId());
                }
            }
            goodsSkuMapper.insertList(goodsModel.getGoodsSkuModelList());
        }
        if (CollectionUtils.isNotEmpty(goodsModel.getGoodsImageModelList())) {
            int i = 1;
            for (GoodsImageModel m : goodsModel.getGoodsImageModelList()) {
                if (StringUtils.isEmpty(m.getId())) {
                    m.setId(CommonUtils.generateID());
                    m.setSort(i++);
                    m.setGoodsId(goodsModel.getId());
                }
            }
            goodsImageMapper.insertList(goodsModel.getGoodsImageModelList());
        }
        if (CollectionUtils.isNotEmpty(goodsModel.getGoodsLabelModelList())) {
            int i = 1;
            for (GoodsLabelModel m : goodsModel.getGoodsLabelModelList()) {
                if (StringUtils.isEmpty(m.getId())) {
                    m.setId(CommonUtils.generateID());
                    m.setSort(i++);
                    m.setGoodsId(goodsModel.getId());
                }
            }
            goodsLabelMapper.insertList(goodsModel.getGoodsLabelModelList());
        }
        setUsedSku(goodsModel);
        return Result.success();
    }

    public Result upGoods(String goodsId) {
        GoodsModel goodsModel = goodsMapper.getDetail(goodsId);
        if (CommonConstant.GOODS_STATUS_UP.equals(goodsModel.getStatus())) {
            return Result.error(CodeMsg.GOODS_ALREADY_UP);
        }
        goodsModel = new GoodsModel();
        goodsModel.setId(goodsId);
        goodsModel.setStatus(CommonConstant.GOODS_STATUS_UP);
        goodsMapper.update(goodsModel);
        return Result.success();
    }

    public Result downGoods(String goodsId) {
        GoodsModel goodsModel = goodsMapper.getDetail(goodsId);
        if (CommonConstant.GOODS_STATUS_DOWN.equals(goodsModel.getStatus())) {
            return Result.error(CodeMsg.GOODS_ALREADY_DOWN);
        }
        goodsModel = new GoodsModel();
        goodsModel.setId(goodsId);
        goodsModel.setStatus(CommonConstant.GOODS_STATUS_DOWN);
        goodsMapper.update(goodsModel);
        return Result.success();
    }

    public Result deleteGoods(String goodsId) {
        GoodsModel goodsModel = goodsMapper.getDetail(goodsId);
        if (CommonConstant.GOODS_DELETE_STATUS_DEL.equals(goodsModel.getDeleteStatus())) {
            return Result.error(CodeMsg.GOODS_ALREADY_DELETE);
        }
        goodsModel = new GoodsModel();
        goodsModel.setId(goodsId);
        goodsModel.setDeleteStatus(CommonConstant.GOODS_DELETE_STATUS_DEL);
        goodsMapper.update(goodsModel);
        return Result.success();
    }

    private void setUsedSku(GoodsModel goodsModel){
        // sku是否使用
        if (CollectionUtils.isNotEmpty(goodsModel.getGoodsSkuModelList())) {
            Set<String> keySet  = new HashSet<>();
            Set<String> valueSet  = new HashSet();
            for (GoodsSkuModel m : goodsModel.getGoodsSkuModelList()) {
                if(StringUtils.isNotEmpty(m.getAttr())){
                    JSONObject obj = JSONObject.parseObject(m.getAttr());
                    Iterator iter = obj.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry entry = (Map.Entry) iter.next();
                        keySet.add(entry.getKey().toString());
                        valueSet.add(entry.getValue().toString());
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(keySet)){
                settingSkuMapper.setKeyUsed(goodsModel.getStoreId(),new ArrayList<>(keySet));
            }
            if(CollectionUtils.isNotEmpty(valueSet)){
                settingSkuMapper.setValueUsed(goodsModel.getStoreId(),new ArrayList<>(valueSet));
            }
        }
    }
}
