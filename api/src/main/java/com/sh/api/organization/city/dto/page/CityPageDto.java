package com.sh.api.organization.city.dto.page;

import lombok.Getter;
import lombok.Setter;

/**
 * 城市分页dto
 *
 *
 * @author 盛浩
 * @date 2020/12/29 20:16
 */
@Getter
@Setter
public class CityPageDto {

    /**
     * 城市名称中文
     */
    private String cityNameCn;

    /**
     * 城市名称英文
     */
    private String cityNameEn;

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
}
