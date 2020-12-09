package com.bc.wd.web;

import com.bc.wd.entity.cons.CommonConstant;
import com.bc.wd.entity.model.OrderDeliveryModel;
import com.bc.wd.service.OrderDeliveryService;
import com.bc.wd.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:18
 **/
@RestController
@RequestMapping("delivery")
public class OrderDeliveryController {

    @Autowired
    private OrderDeliveryService orderDeliveryService;


    @ApiOperation(value = "查询物流记录", notes = "查询物流记录")
    @GetMapping("/{orderId}")
    public Result getPage(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId,
            @PathVariable("orderId") String orderId) {
        return orderDeliveryService.getByOrderId(orderId);
    }

    @ApiOperation(value = "新增物流记录", notes = "新增物流记录")
    @PostMapping("")
    public Result insert(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId,
            @RequestBody OrderDeliveryModel orderDeliveryModel) {
        return orderDeliveryService.insert(orderDeliveryModel);
    }
}
