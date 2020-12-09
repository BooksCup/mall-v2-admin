package com.bc.wd.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 14:37
 **/
@Getter
@Setter
public class BaseResult {

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAIL = 1;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回对象
     */
    private Object data;

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(Integer code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.data = object;
    }

    public static BaseResult Success(String message, Object object) {
        return new BaseResult(RESULT_SUCCESS, message, object);
    }

    public static BaseResult Error(String message, Object object) {
        return new BaseResult(RESULT_FAIL, message, object);
    }
}
