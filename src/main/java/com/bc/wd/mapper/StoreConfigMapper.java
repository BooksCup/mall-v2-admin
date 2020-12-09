package com.bc.wd.mapper;

import com.bc.wd.entity.model.StoreConfigModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Mr.Wang
 * @create: 2020-12-03 10:29
 **/
@Repository
public interface StoreConfigMapper {

    List<StoreConfigModel> getAllByStoreId(String storeId);

    void update(StoreConfigModel storeConfigModel);
}
