package com.bc.wd.mapper;

import com.bc.wd.entity.model.GoodsImageModel;
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
public interface GoodsImageMapper {


    List<GoodsImageModel> getByGoodsId(@Param("goodsId") String goodsId);

    void insertList(List<GoodsImageModel> goodsImageModelList);

    void deleteByGoodsId(@Param("goodsId") String goodsId);
}
