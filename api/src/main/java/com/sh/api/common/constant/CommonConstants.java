package com.sh.api.common.constant;

/**
 * 公共常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/19 19:02
 */
public interface CommonConstants {

    /**
     * 前台提示语
     */
    interface ForegroundPrompt {

        /**
         * 服务器繁忙，请稍后再试
         */
        String SERVER_BUSY_PLEASE_TRY_AGAIN_LATER = "服务器繁忙，请稍后再试";

        /**
         * 服务器内部出错，请稍后再试
         */
        String SERVER_INTERNAL_ERROR_PLEASE_TRY_AGAIN_LATER = "服务器内部出错，请稍后再试";

        /**
         * 服务器正在处理中，请稍后再试
         */
        String THE_SERVER_IS_PROCESSING_PLEASE_TRY_AGAIN_LATER = "服务器正在处理中，请稍后再试";

        /**
         * 未获取到请求类型
         */
        String THE_REQUEST_TYPE_WAS_NOT_OBTAINED = "未获取到请求类型";
    }

    /**
     * 接口文档
     */
    interface Swagger {

        String NAME = "e";

        String URL = "http://127.0.0.1/";

        String EMAIL = "980962735@qq.com";

        String TITLE = "项目API接口";

        String DESCRIPTION = "API测试接口";

        String VERSION = "0.0.1";
    }
}
