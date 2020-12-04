package com.bc.wd.entity.cons;

/**
 * 常量类
 *
 * @author zhou
 */
public class CommonConstant {

    public static final String HEADER_STORE_ID = "storeId";

    public static final String HEADER_USER_ID = "operatorId";

    public static final String SHIRO_PREFIX_USER_KEY = "appuser";

    public static final String CATEGORY_PREFIX_PARENT_KEY = "cate";

    public static final int DATA_DELETE = 1;
    public static final int DATA_UN_DELETE = 0;

    public static final int USER_SEX_MALE = 1;
    public static final int USER_SEX_FEMALE = 2;

    public static final String PRE_FIX_VERIFY_CODE = "msg:vcode:";

    public static final String GOODS_STATUS_UP = "2";
    public static final String GOODS_STATUS_DOWN = "3";
    public static final String GOODS_DELETE_STATUS_DEL = "1";

    public static final String DELIVERY_TYPE_SELF_RAISING = "0";
    public static final String DELIVERY_TYPE_MAIL = "1";

    /**
     * 初始化hashmap容量
     */
    public static final int DEFAULT_HASH_MAP_CAPACITY = 16;
}
