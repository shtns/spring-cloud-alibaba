package com.sh.api.organization.country.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 国家信息
 *
 *
 * @author 盛浩
 * @date 2020/11/30 17:45
 */
@Data
@TableName("country_info")
@EqualsAndHashCode(callSuper = true)
public class CountryInfo extends Model<CountryInfo> {

    /**
     * 国家id
     */
    @TableId(type = IdType.AUTO)
    private Long countryId;

    /**
     * 国家2字码
     */
    @TableField(value = "country_2code")
    private String country2Code;

    /**
     * 国家3字码
     */
    @TableField(value = "country_3code")
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
