package com.sh.api.organization.role.vo.query;

import com.sh.api.organization.role.entity.RoleInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 角色查询vo
 *
 *
 * @author 盛浩
 * @date 2021/2/3 18:09
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleQueryVo {

    /**
     * 角色id
     */
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
     * entity转vo
     *
     * @param roleInfo 角色信息
     */
    public RoleQueryVo(RoleInfo roleInfo) {
        this.roleId = roleInfo.getRoleId();
        this.roleName = roleInfo.getRoleName();
        this.roleNameEn = roleInfo.getRoleNameEn();
        this.remark = roleInfo.getRemark();
    }
}
