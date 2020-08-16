package com.hqyj.JavaSpringBoot.modules.test.service;

import com.hqyj.JavaSpringBoot.modules.test.entity.Country;

public interface CountryService {
    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);

    Country getCountryByRedis(int countryId);

}
