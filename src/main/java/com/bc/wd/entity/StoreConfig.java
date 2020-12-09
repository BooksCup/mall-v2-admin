package com.bc.wd.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-03 10:24
 **/
@Data
public class StoreConfig implements Serializable {

    private String id;
    private String storeId;
    private String isRegister;
    private String storeLogo;
    private String homePageLogo;
    private String storeName;
    private String appId;
    private String appSecret;
    private String domain;
    private String appDomainName;
    private String H5Domain;
    private String mchId;
    private String ip;
    private String uploadImgDomain;
    private String upserver;
    private String uploadImg;
    private String uploadFile;
    private String modifyDate;
    private String mchKey;
    private String mchCert;
    private String userId;
    private String wxDefaultName;
    private String wxDefaultAvatar;
    private String customerService;
    private String agreement;
    private String aboutUs;
    private String messageDay;
    private String afterSalesIssues;
    private String paymentIssues;
    private String shoppingProcess;
    private String paymentMethod;
    private String returnPolicy;
    private String cancellationOrder;
    private String refundProcess;
    private String refundInstructions;

}
