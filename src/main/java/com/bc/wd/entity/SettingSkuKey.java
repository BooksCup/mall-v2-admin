package com.bc.wd.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-04 10:42
 **/
@Data
public class SettingSkuKey implements Serializable {

    private String id;
    private String storeId;
    private String code;
    private String name;
    private String status;
    private String isUsed;
    private String createTime;
    private int sort;

}
