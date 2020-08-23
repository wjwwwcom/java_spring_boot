package com.hqyj.JavaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.JavaSpringBoot.modules.account.entity.User;
import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;

public interface UserService {

    Result<User> login(User user);

    Result<User> insertUser(User user);

    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);

    Result<User> updateUser(User user);

    Result<Object> deleteUserByUserId(int userId);

    User getUserByUserId(int userId);
}
