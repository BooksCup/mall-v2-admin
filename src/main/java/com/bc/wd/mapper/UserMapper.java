package com.bc.wd.mapper;

import com.bc.wd.entity.model.UserModel;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.Map;
/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 10:09
 **/
@Repository
public interface UserMapper {

    Page<UserModel> getPage(Map<String,Object> params);
}
