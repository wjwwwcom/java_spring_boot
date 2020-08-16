package com.hqyj.JavaSpringBoot.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pagetest")
public class thymeleafTest {

    /*
    * 127.0.0.1/pagetest/index
     */
    @GetMapping("/index")
    public String indexPageTest(ModelMap map){
        map.addAttribute("template", "test/index2");
        return "index";
    }
}
