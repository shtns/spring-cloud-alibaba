package com.sh.api.organization.country.vo.query;

import com.sh.api.organization.country.entity.CountryInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 国家查询vo
 *
 *
 * @author 盛浩
 * @date 2020/12/29 15:59
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryQueryVo {

    /**
     * 国家id
     */
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
     * 国家英文名
     */
    private String countryNameEn;

    /**
     * 国家中文名简写
     */
    private String countryAbbreviateNameCn;

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
     * entity转vo
     *
     * @param countryInfo 国家信息
     */
    public CountryQueryVo(CountryInfo countryInfo) {
        this.countryId = countryInfo.getCountryId();
        this.country2Code = countryInfo.getCountry2Code();
        this.country3Code = countryInfo.getCountry3Code();
        this.countryFigureCode = countryInfo.getCountryFigureCode();
        this.countryNameCn = countryInfo.getCountryNameCn();
        this.countryNameEn = countryInfo.getCountryNameEn();
        this.countryAbbreviateNameCn = countryInfo.getCountryAbbreviateNameCn();
        this.countryAbbreviateNameEn = countryInfo.getCountryAbbreviateNameEn();
        this.countryMobileAreaNum = countryInfo.getCountryMobileAreaNum();
        this.countryTimeZone = countryInfo.getCountryTimeZone();
        this.continent = countryInfo.getContinent();
    }
}
