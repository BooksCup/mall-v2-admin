package com.bc.wd.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 17:35
 **/
public class CommonUtils {

    public static final String generateID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static final String generateVerifyCodeCode(){
        return String.format("%04d",new Random().nextInt(9999));
    }
}
