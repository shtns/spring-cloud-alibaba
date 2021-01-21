package com.sh.api.common.constant;

/**
 * 响应信息主体常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/16 1:58
 */
public interface ResponseInfoBodyConstants {

    /**
     * 前台提示语
     */
    interface ForegroundPrompt {

        /**
         * 业务错误码（0成功    1失败）
         */
        Integer CODE_SUCCESS = 0;

        /**
         * 业务错误码（0成功    1失败）
         */
        Integer CODE_FAIL = 1;

        /**
         * 执行成功
         */
        String MSG_SUCCESS = "执行成功";

        /**
         * 执行失败
         */
        String MSG_FAILURE = "执行失败";

        /**
         * 未认证
         */
        Integer NOT_CERTIFICATION = 401;

        /**
         * 未授权
         */
        Integer NOT_AUTHORIZATION = 403;

        /**
         * 暂未登录或token已过期
         */
        String NO_LOGIN_OR_TOKEN_HAS_EXPIRED = "暂未登录或token已过期";

        /**
         * 没有访问权限
         */
        String NO_ACCESS_RIGHTS = "没有访问权限";
    }
}
