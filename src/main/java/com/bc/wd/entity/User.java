package com.bc.wd.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-03 09:38
 **/
@Data
public class User implements Serializable {

    private String id;
    private String store_id;
    private String userName;
    private String phone;
    private String avatar;
    private String email;
    private String password;
    private String token;
    private String accessKey;
    private String wxOpenid;
    private String wxName;
    private String clientId;
    private String sex;
    private String province;
    private String city;
    private String county;
    private String detailedAddress;
    private String money;
    private String score;
    private String lockScore;
    private String paymentPassword;
    private String registerTime;
    private String realName;
    private String birthday;
    private String wechatId;
    private String address;
    private String bankName;
    private String cardHolder;
    private String bankCardNumber;
    private String shareNum;
    private String referee;
    private String accessToken;
    private String consumerMoney;
    private String imgToken;
    private String account;
    private String source;
    private String loginTimes;
    private String verificationTime;
    private String parameter;
    private String isLock;
    private String lastLoginTime;
    private String membersLevel;
    private String membersProviderId;
    private String membersLevelTime;
    private String membersType;
    private String membersExpireTime;
    private String membersExpireStatus;
    private String isRenewalBox;

}
