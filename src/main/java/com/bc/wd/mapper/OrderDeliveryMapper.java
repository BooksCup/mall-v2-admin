package com.bc.wd.mapper;

import com.bc.wd.entity.model.OrderDeliveryModel;
import org.springframework.stereotype.Repository;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:09
 **/
@Repository
public interface OrderDeliveryMapper {

    OrderDeliveryModel getByOrderId(String orderId);

    void insert(OrderDeliveryModel orderDeliveryModel);
}
