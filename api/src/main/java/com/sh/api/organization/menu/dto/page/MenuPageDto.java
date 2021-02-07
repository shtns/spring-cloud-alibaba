package com.sh.api.organization.menu.dto.page;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单分页dto
 *
 *
 * @author 盛浩
 * @date 2021/2/3 20:01
 */
@Getter
@Setter
public class MenuPageDto {

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单路径
     */
    private String menuPath;
}
