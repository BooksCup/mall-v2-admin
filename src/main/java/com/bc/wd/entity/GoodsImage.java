package com.bc.wd.entity;

import com.bc.wd.utils.ValidationGroups;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-01 17:12
 **/
@Data
public class GoodsImage implements Serializable{

    private String id;
    private String goodsId;

    @NotBlank(groups = {ValidationGroups.New.class, ValidationGroups.Edit.class}, message = "商品图片不能为空")
    private String image;
    private String createTime;
    private int sort;

}
