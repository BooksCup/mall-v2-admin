package com.bc.wd.entity;

import com.bc.wd.utils.ValidationGroups;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-01 17:11
 **/
@Data
public class GoodsSku implements Serializable{

    private String id;
    private String goodsId;

    @NotBlank(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品成本价不能为空")
    private String costPrice;

    @NotBlank(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品出售价不能为空")
    private String sellPrice;

    @NotBlank(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品原价不能为空")
    private String origPrice;

    @NotBlank(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品规格图片不能为空")
    private String image;

    @NotNull(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品总库存不能为空")
    private int totalStock;

    @NotNull(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品剩余库存不能为空")
    private int remainStock;

    @NotBlank(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品单位不能为空")
    private String  unit;
    private String  attr;

    @NotNull(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品预警库存不能为空")
    private int warnStock;
    private String modifyTime;
    private String deleteStatus;
    private int sort;
}
