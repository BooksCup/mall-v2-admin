package com.bc.wd.entity.model;

import com.bc.wd.entity.Goods;
import com.bc.wd.entity.GoodsImage;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-01 10:33
 **/
@Data
public class GoodsModel extends Goods implements Serializable{

    @Valid
    private List<GoodsImageModel> goodsImageModelList;

    @Valid
    private List<GoodsSkuModel> goodsSkuModelList;

    @Valid
    private List<GoodsLabelModel> goodsLabelModelList;

}
