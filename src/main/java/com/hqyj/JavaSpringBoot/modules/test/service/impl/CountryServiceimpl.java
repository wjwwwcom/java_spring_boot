package com.hqyj.JavaSpringBoot.modules.test.service.impl;


import com.hqyj.JavaSpringBoot.config.RedisConfig;
import com.hqyj.JavaSpringBoot.modules.test.dao.CountryDao;
import com.hqyj.JavaSpringBoot.modules.test.entity.Country;
import com.hqyj.JavaSpringBoot.modules.test.service.CountryService;
import com.hqyj.JavaSpringBoot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description CountryServcieImpl
 * @Author HymanHu
 * @Date 2020/8/11 13:59
 */
@Service
public class CountryServiceimpl implements CountryService {

    @Autowired
    private CountryDao countryDao;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Country getCountryByCountryId(int countryId) {

        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country getCountryByRedis(int countryId) {
        Country country=countryDao.getCountryByCountryId(countryId);
        String countryKey=String.format("country%d",countryId);
        redisTemplate.opsForValue().set(countryKey,country);
        return (Country)redisTemplate.opsForValue().get(countryKey);
       // redisUtils.set(countryKey,country);
        //return (Country)redisUtils.get(countryKey);
    }
}
