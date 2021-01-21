package com.sh.api.organization.city.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 城市信息
 *
 *
 * @author 盛浩
 * @date 2020/11/30 17:32
 */
@Data
@TableName("city_info")
@EqualsAndHashCode(callSuper = true)
public class CityInfo extends Model<CityInfo> {

    /**
     * 城市id
     */
    @TableId(type = IdType.AUTO)
    private Long cityId;

    /**
     * 城市三字码
     */
    @TableField(value = "city_3code")
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
    @TableField(value = "country_2code")
    private String country2Code;

    /**
     * 删除标志（0未删 1已删）
     */
    @TableLogic
    private String delFlag;

    /**
     * 创建时间（由数据库控制）
     */
    private LocalDateTime createTime;

    /**
     * 最后修改时间（由数据库控制）
     */
    private LocalDateTime lastModifyTime;
}
