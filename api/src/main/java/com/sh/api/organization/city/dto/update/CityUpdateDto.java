package com.sh.api.organization.city.dto.update;

import com.sh.api.common.constant.CityInfoConstants;
import com.sh.api.organization.city.entity.CityInfo;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * 城市修改dto
 *
 *
 * @author 盛浩
 * @date 2020/12/29 21:51
 */
@Getter
@Setter
public class CityUpdateDto  {

    /**
     * 城市id
     */
    @NotNull(message = CityInfoConstants.ForegroundPrompt.CITY_ID_CANNOT_BE_EMPTY)
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
     * dto转entity
     *
     */
    public CityInfo toCityInfo() {
        CityInfo cityInfo = new CityInfo();
        cityInfo.setCityId(this.cityId);
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
