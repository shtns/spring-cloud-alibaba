package com.sh.api.organization.role.dto.update;

import com.sh.api.common.constant.RoleInfoConstant;
import com.sh.api.organization.role.entity.RoleInfo;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色更新dto
 *
 *
 * @author 盛浩
 * @date 2021/2/3 17:05
 */
@Getter
@Setter
public class RoleUpdateDto {

    /**
     * 角色id
     */
    @NotNull(message = RoleInfoConstant.ForegroundPrompt.ROLE_ID_CANNOT_BE_EMPTY)
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
     * 菜单id列表
     */
    private List<Long> menuIds;

    /**
     * dto转entity
     *
     * @return 角色信息
     */
    public RoleInfo changeUpdateRoleInfo() {
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setRoleId(this.roleId);
        roleInfo.setRoleName(this.roleName);
        roleInfo.setRoleNameEn(this.roleNameEn);
        roleInfo.setRemark(this.remark);
        return roleInfo;
    }
}
