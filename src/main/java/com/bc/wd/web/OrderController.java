package com.bc.wd.web;

import com.bc.wd.entity.cons.CommonConstant;
import com.bc.wd.service.OrderService;
import com.bc.wd.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单
 *
 * @author zhou
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result getPage(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId,
            @RequestParam(required = false) String orderNo,
            @RequestParam(required = false) String orderStatus,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>(CommonConstant.DEFAULT_HASH_MAP_CAPACITY);
        paramMap.put(CommonConstant.HEADER_STORE_ID, storeId);
        paramMap.put("orderNo", orderNo);
        paramMap.put("status", orderStatus);
        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        return orderService.getPage(paramMap, page, pageSize);
    }
}
