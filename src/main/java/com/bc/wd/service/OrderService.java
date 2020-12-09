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
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 11:47
 **/
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public Result getPage(Map<String, Object> params, Integer pageSize, Integer page) {
        PageHelper.startPage(page, pageSize);
        Page<OrderModel> orderModelPage = orderMapper.getPage(params);
        return Result.success(orderModelPage.getResult(), orderModelPage.getTotal());
    }

}
