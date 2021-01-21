package com.sh.api.organization.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 菜单信息
 *
 *
 * @author 盛浩
 * @date 2021/1/18 22:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuInfo extends Model<MenuInfo> {

    /**
     * 菜单id
     */
    @TableId(type = IdType.AUTO)
    private Long menuId;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 访问路径
     */
    private String accessPath;

    /**
     * 排序值（默认为1）
     */
    private String sort;

    /**
     * 删除标志（0未删 1已删）
     */
    private String delFlag;

    /**
     * 创建时间（由数据库控制）
     */
    private LocalDateTime creationTime;

    /**
     * 最后修改时间（由数据库控制）
     */
    private LocalDateTime lastModifyTime;
}
