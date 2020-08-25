package com.hqyj.JavaSpringBoot.modules.account.controller;

import com.hqyj.JavaSpringBoot.modules.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description AccountController
 * @Author HymanHu
 * @Date 2020/8/17 13:41
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;
    /**
     * 127.0.0.1/account/login ---- get
     */
    @GetMapping("/login")
    public String loginPage() {
        return "indexSimple";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "indexSimple";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "index";
    }

    @GetMapping("/users")
    public String usersPage() {
        return "index";
    }

    @GetMapping("/roles")
    public String rolesPage() {
        return "index";
    }

    @GetMapping("/resources")
    public String resourcesPage() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "index";
    }

    @GetMapping("/logout")
    public String Logout(ModelMap map) {
        map.addAttribute("template","account/login");
        userService.Logout();
        return "indexSimple";
    }

}
