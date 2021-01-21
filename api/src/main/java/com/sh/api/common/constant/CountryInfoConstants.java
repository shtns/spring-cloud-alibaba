package com.sh.api.common.constant;

/**
 * 国家信息常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/19 21:42
 */
public interface CountryInfoConstants {

    /**
     * 前台提示语
     */
    interface ForegroundPrompt {

        /**
         * 前台提示语：国家id不能为空
         */
        String COUNTRY_ID_CANNOT_BE_EMPTY = "国家id不能为空";


        /**
         * 前台提示语：国家二字码不能为空
         */
        String COUNTRY_2_CODE_CANNOT_BE_EMPTY = "国家二字码不能为空";

        /**
         * 前台提示语：国家三字码不能为空
         */
        String COUNTRY_3_CODE_CANNOT_BE_EMPTY = "国家三字码不能为空";

        /**
         * 前台提示语：国家名称中文不能为空
         */
        String COUNTRY_NAME_CN_CANNOT_BE_EMPTY = "国家名称中文不能为空";

        /**
         * 前台提示语：国家名称英文不能为空
         */
        String COUNTRY_NAME_EN_CANNOT_BE_EMPTY = "国家名称英文不能为空";
    }
}
