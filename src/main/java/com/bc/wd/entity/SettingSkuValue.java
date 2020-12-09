package com.bc.wd.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-04 10:43
 **/
@Data
public class SettingSkuValue implements Serializable {

    private String id;
    private String storeId;
    private String keyId;
    private String code;
    private String value;
    private String status;
    private String isUsed;
    private String createTime;
    private int sort;

}
