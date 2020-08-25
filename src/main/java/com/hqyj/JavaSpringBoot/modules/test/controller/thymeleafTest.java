package com.hqyj.JavaSpringBoot.modules.test.controller;

import com.hqyj.JavaSpringBoot.aspect.ServiceAnnotation;
import com.hqyj.JavaSpringBoot.modules.test.entity.City;
import com.hqyj.JavaSpringBoot.modules.test.entity.Country;
import com.hqyj.JavaSpringBoot.modules.test.service.CityService;
import com.hqyj.JavaSpringBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
     * 127.0.0.1/pagetest/indexSimple ----get
     * */
    @GetMapping("/indexSimple")
    public String indexSimpleTest() {
        return "indexSimple";
    }

    @GetMapping("/downloadfile")
    public ResponseEntity<Resource> downloadfile(@RequestParam String fileName, RedirectAttributes red) {
        Resource resource = null;
        try {
            resource = new UrlResource(Paths.get("D:\\upload\\" + fileName).toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                        .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", resource.getFilename()))
                        .body(resource);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 127.0.0.1/pagetest/files ---- post
     */
    @PostMapping(value = "/files", consumes = "multipart/form-data")
    public String uploadFiles(@RequestParam MultipartFile[] files, RedirectAttributes red) {
        boolean empty = true;
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                String destFilePath = "D:\\FileTest\\" + file.getOriginalFilename();
                File destFile = new File(destFilePath);
                file.transferTo(destFile);
                empty = false;
            }
            if (empty) {
                red.addFlashAttribute("message", "Please select file");
            } else {
                red.addFlashAttribute("message", "Upload file success!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            red.addFlashAttribute("message", "Upload file failed!");
        }
        return "redirect:/pagetest/index";
    }

    /**
     * 127.0.0.1/pagetest/file ---- post
     */
    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes red) {
        if (file.isEmpty()) {
            red.addFlashAttribute("message", "Please select file.");
            return "redirect:/pagetest/index";
        }
        try {
            String destFilePath = "D:\\FileTest\\" + file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);
            red.addFlashAttribute("message", "Upload file success!");
        } catch (IOException e) {
            e.printStackTrace();
            red.addFlashAttribute("message", "Upload file failed!");
        }
        return "redirect:/pagetest/index";
    }


    /*
     *thymeleaf模块化碎片化测试
     * 127.0.0.1/pagetest/index
     */
    @GetMapping("/index")
    public String indexPageTest(ModelMap map) {
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
        // map.addAttribute("template", "test/index");
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
        return "Filter Test: one Day,Zhangsan to Lisi Say:“" + paramKey + " you mother and " + paramValue + " you daughter”";
    }


}
