package com.hqyj.JavaSpringBoot.modules.test.controller;


import com.hqyj.JavaSpringBoot.modules.test.entity.Country;
import com.hqyj.JavaSpringBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *Country控制类
 */
@RestController
@RequestMapping("/cpi")
public class CountryController {

    @Autowired
    private CountryService countryService;

    /**
     * 通过Id查找国家
     *url:127.0.0.1/cpi/country/522 ---- get
     */
    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable int countryId) {
        return countryService.getCountryByCountryId(countryId);
    }

    /**
     * 通过名称查找国家
     * url:127.0.0.1/cpi/country?countryName=China ---- get
     */
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName) {
        return countryService.getCountryByCountryName(countryName);
    }

    /**
     * redis通过Id查找国家
     *url:127.0.0.1/cpi/redis/522 ---- get
     */
    @GetMapping("/redis/{countryId}")
    public Country getCountryByRedis(@PathVariable int countryId) {
        return countryService.getCountryByRedis(countryId);
    }
}
