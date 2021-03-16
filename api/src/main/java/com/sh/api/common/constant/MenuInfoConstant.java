package com.sh.api.common.constant;

/**
 * 菜单信息常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/18 7:29
 */
public interface MenuInfoConstant {

    /**
     * 前台提示语
     */
    interface ForegroundPrompt {

        /**
         * 菜单id列表不能为空
         */
        String MENU_IDS_CANNOT_BE_EMPTY = "菜单id列表不能为空";

        /**
         * 父菜单id不能为空
         */
        String PARENT_ID_CANNOT_BE_EMPTY = "父菜单id不能为空";

        /**
         * 菜单名称不能为空
         */
        String NAME_CANNOT_BE_EMPTY = "菜单名称不能为空";

        /**
         * 访问路径不能为空
         */
        String ACCESS_PATH_CANNOT_BE_EMPTY = "访问路径不能为空";

        /**
         * 排序值不能为空
         */
        String SORT_CANNOT_BE_EMPTY = "排序值不能为空";

        /**
         * 菜单id不能为空
         */
        String MENU_ID_CANNOT_BE_EMPTY = "菜单id不能为空";
    }

    /**
     * id
     */
    interface Id {

        /**
         * 用户信息管理
         */
        Long USER = 1L;

        /**
         * 角色信息管理
         */
        Long ROLE = 2L;

        /**
         * 菜单信息管理
         */
        Long MENU = 3L;

        /**
         * 国家信息管理
         */
        Long COUNTRY = 4L;

        /**
         * 城市信息管理
         */
        Long CITY = 5L;
    }
}
