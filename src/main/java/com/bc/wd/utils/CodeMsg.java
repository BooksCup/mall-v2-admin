package com.bc.wd.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-01 10:52
 **/
@Getter
@Setter
@ToString
public class CodeMsg {

    /**
     * 状态码
     */
    protected int code;

    /**
     * 状态信息
     */
    protected String msg;

    /**
     * 通用的状态码和状态信息
     *
     * @author tangyifei
     */
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常！");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500101, "请求非法！");
    public static CodeMsg ACCESS_LIMIT_REACHED = new CodeMsg(500102, "访问太频繁！");
    public static CodeMsg TIME_OUT = new CodeMsg(500103, "访问超时！");
    public static CodeMsg BIND_ERROR = new CodeMsg(500104, "参数校验异常：%s");
    public static CodeMsg REQUEST_METHOD_NOT_SUPPORT = new CodeMsg(500105, "不支持当前请求方法！");
    public static CodeMsg MEDIA_NOT_SUPPORT = new CodeMsg(500106, "不支持当前媒体类型！");
    public static CodeMsg PARAM_NOT_READ = new CodeMsg(500107, "参数解析失败！");
    public static CodeMsg PARAM_MISS = new CodeMsg(500108, "缺少请求参数！");
    public static CodeMsg DATA_ALREADY_EXISTS = new CodeMsg(500109, "数据已存在！");
    public static CodeMsg GOODS_DATA_ALREADY_EXISTS = new CodeMsg(500110, "物品已存在！");

    public static CodeMsg THIRD_PARTY_PRINTER_ERROR = new CodeMsg(600001, "第三方打印机接口失败！");

    public static CodeMsg GOODS_ALREADY_UP = new CodeMsg(700001, "该商品已经上架！");
    public static CodeMsg GOODS_ALREADY_DOWN = new CodeMsg(700002, "该商品已经下架！");
    public static CodeMsg GOODS_ALREADY_DELETE = new CodeMsg(700002, "该商品已经删除！");

    protected CodeMsg(){

    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

}
