package com.bc.wd.entity;

import com.bc.wd.utils.ValidationGroups;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @program: server
 * @description:
 *
 * @NotNull 和 @NotEmpty  和@NotBlank 区别

 @NotEmpty 用在集合类上面
 @NotBlank 用在String上面
 @NotNull    用在基本类型上
 *
 * @author: Mr.Wang
 * @create: 2020-12-01 10:33
 **/
@Data
public class Goods implements Serializable{

    private String id;
    private String storeId;
    private String goodsNumber;
    private String goodsClassId;
    private String image;

    @NotBlank(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品名称不能为空")
    private String name;
    private String shortName;

    @NotBlank(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品实际价格不能为空")
    private String goodsPrice;
    private String originalPrice;
    private String content;
    private int sort;
    private String salesVolume;
    private String tag;

    @NotNull(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品数量不能为空")
    private int num;
    private int warningInventory;
    private String status;
    private int brandId;
    private String keyword;
    private String freight;
    private String activity;
    private String shopId;
    private String showAddress;
    private String createTime;
    private String modifyTime;
    private String deleteStatus;
}
