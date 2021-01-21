package com.sh.api.organization.rolemenu;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单关系信息
 *
 *
 * @author 盛浩
 * @date 2021/1/19 17:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenuInfo extends Model<RoleMenuInfo> {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;
}
