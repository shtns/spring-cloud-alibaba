package com.sh.api.common.constant;

/**
 * 城市信息常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/19 21:38
 */
public interface CityInfoConstant {

    /**
     * 前台提示语
     */
    interface ForegroundPrompt {

        /**
         * 城市id不能为空
         */
        String CITY_ID_CANNOT_BE_EMPTY = "城市id不能为空";

        /**
         * 城市三字码不能为空
         */
        String CITY_3_CODE_CANNOT_BE_EMPTY = "城市三字码不能为空";

        /**
         * 城市名称中文不能为空
         */
        String CITY_NAME_CN_CANNOT_BE_EMPTY = "城市名称中文不能为空";

        /**
         * 城市名称英文不能为空
         */
        String CITY_NAME_EN_CANNOT_BE_EMPTY = "城市名称英文不能为空";

        /**
         * 热门城市不能为空
         */
        String CITY_POPULAR_CANNOT_BE_EMPTY = "热门城市不能为空";
    }
}
