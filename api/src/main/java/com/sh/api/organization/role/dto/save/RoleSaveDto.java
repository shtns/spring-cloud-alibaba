package com.sh.api.organization.role.dto.save;

import com.sh.api.common.constant.MenuInfoConstant;
import com.sh.api.common.constant.RoleInfoConstant;
import com.sh.api.organization.role.entity.RoleInfo;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色保存dto
 *
 *
 * @author 盛浩
 * @date 2021/2/3 16:03
 */
@Getter
@Setter
public class RoleSaveDto {

    /**
     * 角色名称
     */
    @NotBlank(message = RoleInfoConstant.ForegroundPrompt.ROLE_NAME_CANNOT_BE_EMPTY)
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
    @NotNull(message = MenuInfoConstant.ForegroundPrompt.MENU_IDS_CANNOT_BE_EMPTY)
    private List<Long> menuIds;

    /**
     * dto转entity
     *
     * @return 角色id
     */
    public Long changeSaveRoleInfo() {
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setRoleName(this.roleName);
        roleInfo.setRoleNameEn(this.roleNameEn);
        roleInfo.setRemark(this.remark);
        roleInfo.insert();
        return roleInfo.getRoleId();
    }
}
