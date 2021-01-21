package com.sh.api.organization.country.dto.page;

import lombok.Getter;
import lombok.Setter;

/**
 * 国家分页查询dto
 *
 *
 * @author 盛浩
 * @date 2020/12/29 22:31
 */
@Getter
@Setter
public class CountryPageQueryDto {

    /**
     * 国家中文名
     */
    private String countryNameCn;

    /**
     * 国家英文名
     */
    private String countryNameEn;
}
