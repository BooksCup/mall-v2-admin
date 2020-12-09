package com.bc.wd.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: whl-project
 * @description: 分页结果处理
 * @author: Mr.Wang
 * @create: 2020-04-22 12:55
 **/
@Getter
@Setter
public class PageResult {

    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List<?> content;

}
