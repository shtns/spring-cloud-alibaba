package com.sh.api.organization.city.dto.save;

import com.sh.api.common.constant.CityInfoConstants;
import com.sh.api.common.constant.CountryInfoConstants;
import com.sh.api.organization.city.entity.CityInfo;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

/**
 * 城市添加dto
 *
 *
 * @author 盛浩
 * @date 2020/12/29 21:35
 */
@Getter
@Setter
public class CitySaveDto {

    /**
     * 城市三字码
     */
    @NotBlank(message = CityInfoConstants.ForegroundPrompt.CITY_3_CODE_CANNOT_BE_EMPTY)
    private String city3Code;

    /**
     * 城市名称中文
     */
    @NotBlank(message = CityInfoConstants.ForegroundPrompt.CITY_NAME_CN_CANNOT_BE_EMPTY)
    private String cityNameCn;

    /**
     * 城市名称英文
     */
    @NotBlank(message = CityInfoConstants.ForegroundPrompt.CITY_NAME_EN_CANNOT_BE_EMPTY)
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
    @NotBlank(message = CityInfoConstants.ForegroundPrompt.CITY_POPULAR_CANNOT_BE_EMPTY)
    private String cityPopular;

    /**
     * 国家二字码
     */
    @NotBlank(message = CountryInfoConstants.ForegroundPrompt.COUNTRY_2_CODE_CANNOT_BE_EMPTY)
    private String country2Code;

    /**
     * dto转entity
     *
     * @return 城市信息
     */
    public CityInfo toCityInfo() {
        CityInfo cityInfo = new CityInfo();
        cityInfo.setCity3Code(this.city3Code);
        cityInfo.setCityNameCn(this.cityNameCn);
        cityInfo.setCityNameEn(this.cityNameEn);
        cityInfo.setCityTimeZone(this.cityTimeZone);
        cityInfo.setCityNameAllSpell(this.cityNameAllSpell);
        cityInfo.setCityNameHeadSpell(this.cityNameHeadSpell);
        cityInfo.setCityPopular(this.cityPopular);
        cityInfo.setCountry2Code(this.country2Code);
        return cityInfo;
    }
}
