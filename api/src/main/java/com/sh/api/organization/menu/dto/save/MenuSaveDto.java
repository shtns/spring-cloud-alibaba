package com.sh.api.organization.menu.dto.save;

import com.sh.api.common.constant.MenuInfoConstants;
import com.sh.api.organization.menu.entity.MenuInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 菜单新增dto
 *
 *
 * @author 盛浩
 * @date 2021/2/3 19:27
 */
@Getter
@Setter
public class MenuSaveDto {

    /**
     * 父菜单id（默认为-1）
     */
    @NotNull(message = MenuInfoConstants.ForegroundPrompt.PARENT_ID_CANNOT_BE_EMPTY)
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotNull(message = MenuInfoConstants.ForegroundPrompt.NAME_CANNOT_BE_EMPTY)
    private String menuName;

    /**
     * 菜单路径
     */
    @NotNull(message = MenuInfoConstants.ForegroundPrompt.ACCESS_PATH_CANNOT_BE_EMPTY)
    private String menuPath;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 排序值（默认为1）
     */
    @NotNull(message = MenuInfoConstants.ForegroundPrompt.SORT_CANNOT_BE_EMPTY)
    private Integer sort;

    /**
     * dto转entity
     *
     * @return 菜单信息
     */
    public MenuInfo changeSaveMenuInfo() {
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setParentId(this.parentId);
        menuInfo.setMenuName(this.menuName);
        menuInfo.setMenuPath(this.menuPath);
        menuInfo.setMenuIcon(this.menuIcon);
        menuInfo.setSort(this.sort);
        return menuInfo;
    }
}
