package com.sh.api.organization.menu.dto.update;

import com.sh.api.common.constant.MenuInfoConstants;
import com.sh.api.organization.menu.entity.MenuInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 菜单更新dto
 *
 *
 * @author 盛浩
 * @date 2021/2/3 19:47
 */
@Getter
@Setter
public class MenuUpdateDto {

    /**
     * 菜单id
     */
    @NotNull(message = MenuInfoConstants.ForegroundPrompt.MENU_ID_CANNOT_BE_EMPTY)
    private Long menuId;

    /**
     * 父菜单id（默认为-1）
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
    private Integer sort;

    /**
     * dto转entity
     *
     * @return 菜单信息
     */
    public MenuInfo changeUpdateMenuInfo() {
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setMenuId(this.menuId);
        menuInfo.setParentId(this.parentId);
        menuInfo.setMenuName(this.menuName);
        menuInfo.setAccessPath(this.accessPath);
        menuInfo.setSort(this.sort);
        return menuInfo;
    }
}
