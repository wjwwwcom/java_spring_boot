package com.hqyj.JavaSpringBoot.modules.test.controller;

import com.hqyj.JavaSpringBoot.aspect.ServiceAnnotation;
import com.hqyj.JavaSpringBoot.modules.test.entity.City;
import com.hqyj.JavaSpringBoot.modules.test.entity.Country;
import com.hqyj.JavaSpringBoot.modules.test.service.CityService;
import com.hqyj.JavaSpringBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pagetest")
public class thymeleafTest {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    /*
     *thymeleaf模块化碎片化测试
    * 127.0.0.1/pagetest/index
     */
    @GetMapping("/index")
    public String indexPageTest(ModelMap map){
        int countryId = 522;
        List<City> cities = cityService.getCitiesByCountryId(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.getCountryByCountryId(countryId);

        map.addAttribute("thymeleafTitle", "scdscsadcsacd");
        map.addAttribute("checked", true);
        map.addAttribute("currentNumber", 99);
        map.addAttribute("changeType", "checkbox");
        map.addAttribute("baiduUrl", "/test/log");
        map.addAttribute("city", cities.get(0));
        map.addAttribute("shopLogo",
                "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        map.addAttribute("shopLogo",
                "/upload/test1.png");
        map.addAttribute("country", country);
        map.addAttribute("cities", cities);
        map.addAttribute("updateCityUri", "/cpi/city");
        map.addAttribute("template", "test/index");
        return "index";
    }

    /**
     * 过滤器测试
     * aop自定义注释测试
     * 127.0.0.1/pagetest/filtertest?paramKey=fuck ---- get
     */
    @GetMapping("/filtertest")
    @ResponseBody
    @ServiceAnnotation("aaa")
    public String testDesc(HttpServletRequest request, @RequestParam String paramKey) {
        String paramValue = request.getParameter("paramKey");
        return "Filter Test: one Day,Zhangsan to Lisi Say:“"+paramKey+" you mother and "+paramValue+" you daughter”";
    }




}
