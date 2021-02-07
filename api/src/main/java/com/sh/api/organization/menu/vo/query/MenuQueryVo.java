package com.sh.api.organization.menu.vo.query;

import com.sh.api.organization.menu.entity.MenuInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 菜单查询vo
 *
 *
 * @author 盛浩
 * @date 2021/2/3 18:21
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuQueryVo {

    /**
     * 菜单id
     */
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
     * 菜单路径
     */
    private String menuPath;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 排序值（默认为1）
     */
    private Integer sort;

    /**
     * entity转vo
     *
     * @param menuInfo 菜单信息
     */
    public MenuQueryVo(MenuInfo menuInfo) {
        this.menuId = menuInfo.getMenuId();
        this.parentId = menuInfo.getParentId();
        this.menuName = menuInfo.getMenuName();
        this.menuPath = menuInfo.getMenuPath();
        this.menuIcon = menuInfo.getMenuIcon();
        this.sort = menuInfo.getSort();
    }
}
