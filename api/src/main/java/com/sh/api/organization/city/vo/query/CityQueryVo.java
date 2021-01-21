package com.sh.api.organization.city.vo.query;

import com.sh.api.organization.city.entity.CityInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

/**
 * 城市查询vo
 *
 *
 * @author 盛浩
 * @date 2020/12/29 16:25
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityQueryVo implements Serializable {

    /**
     * 城市id
     */
    private Long cityId;

    /**
     * 城市三字码
     */
    private String city3Code;

    /**
     * 城市名称中文
     */
    private String cityNameCn;

    /**
     * 城市名称英文
     */
    private String cityNameEn;

    /**
     * 城市时区
     */
    private String cityTimeZone;

    /**
     * 城市名称全拼
     */
    private String cityNameAllSpell;

    /**
     * 城市名称首拼
     */
    private String cityNameHeadSpell;

    /**
     * 热门城市（0不热门 1热门）
     */
    private String cityPopular;

    /**
     * 国家二字码
     */
    private String country2Code;

    /**
     * entity转vo
     *
     * @param cityInfo 城市信息
     */
    public CityQueryVo(CityInfo cityInfo) {
        this.cityId = cityInfo.getCityId();
        this.city3Code = cityInfo.getCity3Code();
        this.cityNameCn = cityInfo.getCityNameCn();
        this.cityNameEn = cityInfo.getCityNameEn();
        this.cityTimeZone = cityInfo.getCityTimeZone();
        this.cityNameAllSpell = cityInfo.getCityNameAllSpell();
        this.cityNameHeadSpell = cityInfo.getCityNameHeadSpell();
        this.cityPopular = cityInfo.getCityPopular();
        this.country2Code = cityInfo.getCountry2Code();
    }
}
