package com.hqyj.JavaSpringBoot.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.JavaSpringBoot.modules.account.dao.UserDao;
import com.hqyj.JavaSpringBoot.modules.account.entity.User;
import com.hqyj.JavaSpringBoot.modules.account.service.UserService;
import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.JavaSpringBoot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Result<User> login(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null &&
                userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {
            return new Result<>(Result.ResultStatus.SUCCESS.status, "Success.", userTemp);
        }
        return new Result<User>(Result.ResultStatus.FAILD.status,
                "UserName or password is error.");
    }

    @Override
    public Result<User> insertUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null) {
            return new Result<User>(
                    Result.ResultStatus.FAILD.status, "User name is repeat.");
        }

        user.setCreateDate(LocalDateTime.now());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userDao.insertUser(user);
        return new Result<User>(
                Result.ResultStatus.SUCCESS.status, "Insert success.", user);
    }

    @Override
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<User>(
                Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }
}
