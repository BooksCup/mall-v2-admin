package com.bc.wd.entity.model;

import com.bc.wd.entity.GoodsSku;
import com.bc.wd.entity.Order;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-03 09:56
 **/
@Data
public class OrderModel extends Order implements Serializable {

    private String goodsName;

    private String  skuName;

    private String skuImage;

    private String deliveryNumber;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;


}
