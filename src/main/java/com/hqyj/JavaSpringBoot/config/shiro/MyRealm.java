package com.hqyj.JavaSpringBoot.config.shiro;

import com.hqyj.JavaSpringBoot.modules.account.entity.Resource;
import com.hqyj.JavaSpringBoot.modules.account.entity.Role;
import com.hqyj.JavaSpringBoot.modules.account.entity.User;
import com.hqyj.JavaSpringBoot.modules.account.service.ResourceService;
import com.hqyj.JavaSpringBoot.modules.account.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.shiro.realm.AuthorizingRealm;

import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm  {

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user= (User) principal.getPrimaryPrincipal();
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()){
            roles.stream().forEach(item ->{
                simpleAuthorizationInfo.addRole(item.getRoleName());
                List<Resource> resources =
                        resourceService.getResourcesByRoleId(item.getRoleId());
                if (resources != null && !resources.isEmpty()) {
                    resources.stream().forEach(resource -> {
                        simpleAuthorizationInfo.addStringPermission(resource.getPermission());
                    });
                }
            });
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        String userName = (String) Token.getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException("The account do not exist.");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
