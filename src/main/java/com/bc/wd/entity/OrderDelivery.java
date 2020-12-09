package com.bc.wd.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: server
 * @description:发货
 * @author: Mr.Wang
 * @create: 2020-12-07 14:41
 **/
@Data
public class OrderDelivery implements Serializable{

    private String id;
    private String orderId;
    private String deliveryType;
    private String deliveryCompanyId;
    private String deliveryCompanyName;
    private String deliveryNumber;
    private String createTime;

}
