package com.bc.wd.service;

import com.bc.wd.entity.model.OrderModel;
import com.bc.wd.mapper.OrderMapper;
import com.bc.wd.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单
 *
 * @author zhou
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 获取订单分页数据
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 订单分页数据
     */
    public Result getPage(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<OrderModel> orderModelPage = orderMapper.getPage(paramMap);
        return Result.success(orderModelPage.getResult(), orderModelPage.getTotal());
    }

}
