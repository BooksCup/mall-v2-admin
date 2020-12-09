package com.bc.wd.mapper;

import com.bc.wd.entity.Goods;
import com.bc.wd.entity.model.GoodsModel;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:09
 **/
@Repository
public interface GoodsMapper {

    Page<GoodsModel> getPage(Map<String, Object> params);

    GoodsModel getDetail(String id);

    Integer getMaxSort(String storeId);

    void insert(GoodsModel goodsModel);

    void update(GoodsModel goodsModel);
}
