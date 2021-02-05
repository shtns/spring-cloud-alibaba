package com.sh.api.organization.role.dto.page;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色分页dto
 *
 *
 * @author 盛浩
 * @date 2021/2/3 18:18
 */
@Getter
@Setter
public class RolePageDto {

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
}
