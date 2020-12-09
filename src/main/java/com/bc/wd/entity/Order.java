package com.bc.wd.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-03 09:56
 **/
@Data
public class Order implements Serializable {

    private String id;
    private String storeId;
    private String userId;
    private String goodsId;
    private String skuId;
    private String addressId;
    private String orderNo;
    private int number;
    private String totalAmount;
    private String payId;
    private String status;
    private String freight;
    private String remark;
    private String payType;
    private String payTime;
    private int payStatus;
    private String createTime;

}
