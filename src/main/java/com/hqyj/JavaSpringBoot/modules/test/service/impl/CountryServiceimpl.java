package com.hqyj.JavaSpringBoot.modules.test.service.impl;


import com.hqyj.JavaSpringBoot.modules.test.dao.CountryDao;
import com.hqyj.JavaSpringBoot.modules.test.entity.Country;
import com.hqyj.JavaSpringBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Country getCountryByCountryId(int countryId) {

        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }
}
