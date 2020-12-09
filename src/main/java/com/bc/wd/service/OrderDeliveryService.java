package com.bc.wd.service;

import com.bc.wd.entity.model.OrderDeliveryModel;
import com.bc.wd.entity.model.OrderModel;
import com.bc.wd.mapper.OrderDeliveryMapper;
import com.bc.wd.mapper.OrderMapper;
import com.bc.wd.utils.CommonUtils;
import com.bc.wd.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 11:47
 **/
@Service
public class OrderDeliveryService {

    @Autowired
    private OrderDeliveryMapper orderDeliveryMapper;

    @Autowired
    private OrderMapper orderMapper;

    public Result getByOrderId(String orderId) {
        OrderDeliveryModel orderDeliveryModel = orderDeliveryMapper.getByOrderId(orderId);
        return Result.success(orderDeliveryModel);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result insert(OrderDeliveryModel orderDeliveryModel) {
        String id = CommonUtils.generateID();
        orderDeliveryModel.setId(CommonUtils.generateID());
        orderDeliveryMapper.insert(orderDeliveryModel);

        // 修改待收货
        orderMapper.updateOrderStatus(orderDeliveryModel.getOrderId(),"2");
        return Result.success(id);
    }

}
