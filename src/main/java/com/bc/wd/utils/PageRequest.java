package com.bc.wd.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 12:57
 **/
@Getter
@Setter
public class PageRequest {

    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
}
