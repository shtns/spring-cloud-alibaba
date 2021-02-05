package com.sh.api.organization.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 角色信息
 *
 *
 * @author 盛浩
 * @date 2021/1/19 17:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleInfo extends Model<RoleInfo> {

    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色英文名称
     */
    private String roleNameEn;

    /**
     * 角色备注
     */
    private String remark;

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
