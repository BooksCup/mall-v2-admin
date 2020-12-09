package com.bc.wd.mapper;

import com.bc.wd.entity.model.OrderModel;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:09
 **/
@Repository
public interface OrderMapper {

    Page<OrderModel> getPage(Map<String, Object> params);

    void updateOrderStatus(@Param("id")String id,@Param("status") String status);
}
