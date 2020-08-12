package com.hqyj.JavaSpringBoot.modules.test.dao;


import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.JavaSpringBoot.modules.test.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CityDao层
 */
@Repository
@Mapper
public interface CityDao {
    //通过Id查询
    @Select("select * from m_city where country_id = #{countryId}")
    List<City> getCitiesByCountryId(int countryId);

    //模糊查询
    @Select("<script>" +
            "select * from m_city "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by city_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<City> getCitiesBySearchVo(SearchVo searchVo);

    //插入
    @Insert("insert into m_city (city_name, local_city_name, country_id, date_created) " +
            "values (#{cityName}, #{localCityName}, #{countryId}, #{dateCreated})")
    @Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty = "cityId")
    void insertCity(City city);

    //修改
    @Update("update m_city set city_name = #{cityName} where city_id = #{cityId}")
    void updateCity(City city);

    //删除
    @Delete("delete from m_city where city_id = #{cityId}")
    void deleteCity(int cityId);
}
