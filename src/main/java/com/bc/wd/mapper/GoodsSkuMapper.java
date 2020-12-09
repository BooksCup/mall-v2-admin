package com.bc.wd.mapper;

import com.bc.wd.entity.model.GoodsSkuModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:09
 **/
@Repository
public interface GoodsSkuMapper {


    List<GoodsSkuModel> getByGoodsId(@Param("goodsId") String goodsId);

    void insertList(List<GoodsSkuModel> goodsSkuModelList);

    void deleteByGoodsId(@Param("goodsId") String goodsId);
}
