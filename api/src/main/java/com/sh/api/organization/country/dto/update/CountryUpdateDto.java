package com.sh.api.organization.country.dto.update;

import com.sh.api.common.constant.CountryInfoConstant;
import com.sh.api.organization.country.entity.CountryInfo;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * 国家更新dto
 *
 *
 * @author 盛浩
 * @date 2020/12/29 22:09
 */
@Getter
@Setter
public class CountryUpdateDto {

    /**
     * 国家id
     */
    @NotNull(message = CountryInfoConstant.ForegroundPrompt.COUNTRY_ID_CANNOT_BE_EMPTY)
    private Long countryId;

    /**
     * 国家2字码
     */
    private String country2Code;

    /**
     * 国家3字码
     */
    private String country3Code;

    /**
     * 国家数字code
     */
    private String countryFigureCode;

    /**
     * 国家中文名
     */
    private String countryNameCn;

    /**
     * 国家中文名简写
     */
    private String countryAbbreviateNameCn;

    /**
     * 国家英文名
     */
    private String countryNameEn;

    /**
     * 国家英文名简写
     */
    private String countryAbbreviateNameEn;

    /**
     * 国家手机区号
     */
    private String countryMobileAreaNum;

    /**
     * 国家所指定时区
     */
    private String countryTimeZone;

    /**
     * 大洲
     */
    private String continent;

    /**
     * dto转entity
     *
     * @return 国家信息
     */
    public CountryInfo toCountryInfo() {
        CountryInfo countryInfo = new CountryInfo();
        countryInfo.setCountryId(this.countryId);
        countryInfo.setCountry2Code(this.country2Code);
        countryInfo.setCountry3Code(this.country3Code);
        countryInfo.setCountryFigureCode(this.countryFigureCode);
        countryInfo.setCountryNameCn(this.countryNameCn);
        countryInfo.setCountryNameEn(this.countryNameEn);
        countryInfo.setCountryAbbreviateNameCn(this.countryAbbreviateNameCn);
        countryInfo.setCountryAbbreviateNameEn(this.countryAbbreviateNameEn);
        countryInfo.setCountryMobileAreaNum(this.countryMobileAreaNum);
        countryInfo.setCountryTimeZone(this.countryTimeZone);
        countryInfo.setContinent(this.continent);
        return countryInfo;
    }
}
