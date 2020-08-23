package com.hqyj.JavaSpringBoot.modules.account.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.JavaSpringBoot.modules.account.dao.RoleDao;
import com.hqyj.JavaSpringBoot.modules.account.dao.UserRoleDao;
import com.hqyj.JavaSpringBoot.modules.account.entity.Role;
import com.hqyj.JavaSpringBoot.modules.account.entity.User;
import com.hqyj.JavaSpringBoot.modules.account.service.RoleService;
import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.JavaSpringBoot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Description RoleServiceImpl
 * @Author HymanHu
 * @Date 2020/8/21 14:00
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<Role> getRoles() {
        return Optional.ofNullable(roleDao.getRoles())
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<Role> getUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<Role>(
                Optional.ofNullable(roleDao.getRolesBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public Result<Role> insertRole(Role role) {
            Role roleTemp = roleDao.getRoleByRoleName(role.getRoleName());
            if (roleTemp != null) {
                return new Result<Role>(
                        Result.ResultStatus.FAILD.status, "Role name is repeat!");
            }else {
                roleDao.insertRole(role);
                return new Result<Role>(
                        Result.ResultStatus.SUCCESS.status, "Insert success.", role);
            }

    }

    @Override
    @Transactional
    public Result<Role> updateRole(Role role) {
        Role roleTemp = roleDao.getRoleByRoleName(role.getRoleName());
        if (roleTemp != null) {
            return new Result<Role>(
                    Result.ResultStatus.FAILD.status, "Role name is repeat!");
        }
        roleDao.updateRole(role);
        return new Result<Role>(Result.ResultStatus.SUCCESS.status,"Update success");
    }

    @Override
    @Transactional
    public Result<Object> deleteRoleByRoleId(int roleId) {
        roleDao.deleteRoleByRoleId(roleId);
        userRoleDao.deleteUserRoleByRoleId(roleId);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"Delete success");
    }

    @Override
    public Role getRoleByRoleId(int roleId) {
        return roleDao.getRoleByRoleId(roleId);
    }


}
