package com.bc.wd.web;

import com.bc.wd.entity.cons.CommonConstant;
import com.bc.wd.service.OrderService;
import com.bc.wd.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:18
 **/
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/page")
    public Result getPage(
            @RequestHeader(CommonConstant.HEADER_STORE_ID) String storeId,
            @RequestBody Map<String, Object> params,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("page") Integer page) {
        params.put(CommonConstant.HEADER_STORE_ID,storeId);
        return orderService.getPage(params, pageSize, page);
    }
}
