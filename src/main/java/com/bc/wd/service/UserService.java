package com.bc.wd.service;

import com.bc.wd.entity.model.UserModel;
import com.bc.wd.mapper.UserMapper;
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
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Result getPage(Map<String, Object> params, Integer pageSize, Integer page) {
        PageHelper.startPage(page, pageSize);
        Page<UserModel> userModelPage = userMapper.getPage(params);
        return Result.success(userModelPage.getResult(), userModelPage.getTotal());
    }

}
