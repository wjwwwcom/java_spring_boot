package com.hqyj.JavaSpringBoot.modules.account.service;


import com.github.pagehelper.PageInfo;
import com.hqyj.JavaSpringBoot.modules.account.entity.Role;
import com.hqyj.JavaSpringBoot.modules.account.entity.User;
import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;

import java.util.List;

/**
 * @Description RoleService
 * @Author HymanHu
 * @Date 2020/8/21 14:00
 */
public interface RoleService {

    List<Role> getRoles();

    PageInfo<Role> getUsersBySearchVo(SearchVo searchVo);

    Result<Role> insertRole(Role role);

    Result<Role> updateRole(Role role);

    Result<Object> deleteRoleByRoleId(int roleId);

    Role getRoleByRoleId(int roleId);
}
