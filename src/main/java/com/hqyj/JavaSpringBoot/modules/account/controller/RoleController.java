package com.hqyj.JavaSpringBoot.modules.account.controller;


import com.github.pagehelper.PageInfo;
import com.hqyj.JavaSpringBoot.modules.account.entity.Role;
import com.hqyj.JavaSpringBoot.modules.account.entity.User;
import com.hqyj.JavaSpringBoot.modules.account.service.RoleService;
import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * /cpi/roles ---- get
     */
    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    /*
     * /cpi/roles
     * */
    @PostMapping(value = "/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Role> getUsersBySearchVo(@RequestBody SearchVo searchVo) {
        return roleService.getUsersBySearchVo(searchVo);
    }

    @PostMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequiresPermissions(value = "/role/insert")
    public Result<Role> insertUser(@RequestBody Role role) {
        return roleService.insertRole(role);
    }

    @PutMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequiresPermissions(value = "/role/update")
    public Result<Role> updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/role/{roleId}")
    @RequiresPermissions(value = "/role/delete")
    public Result<Object> deleteRoleByRoleId(@PathVariable int roleId) {
        return roleService.deleteRoleByRoleId(roleId);
    }

    @GetMapping("/role/{roleId}")
    public Role getRoleByRoleId(@PathVariable int roleId) {
        return roleService.getRoleByRoleId(roleId);
    }
}
