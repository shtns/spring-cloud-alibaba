package com.sh.api.common.constant;

/**
 * 用户信息常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/16 1:54
 */
public interface UserInfoConstants {

    /**
     * 前台提示语
     */
    interface ForegroundPrompt {

        /**
         * 登入账号不能为空
         */
        String LOGIN_ACCOUNT_CANNOT_BE_EMPTY = "登入账号不能为空";

        /**
         * 未查询到用户信息
         */
        String USER_INFORMATION_NOT_FOUND = "未查询到用户信息";

        /**
         * 角色id不能为空
         */
        String ROLE_ID_CANNOT_BE_EMPTY = "角色id不能为空";

        /**
         * 密码不能为空
         */
        String PASSWORD_CANNOT_BE_EMPTY = "密码不能为空";

        /**
         * 登入账号已存在
         */
        String LOGIN_ACCOUNT_ALREADY_EXISTS = "登入账号已存在";

        /**
         * 账号或密码错误
         */
        String INCORRECT_ACCOUNT_OR_PASSWORD = "账号或密码错误";

        /**
         * 用户id不能为空
         */
        String USER_ID_CANNOT_BE_EMPTY = "用户id不能为空";
    }
}
