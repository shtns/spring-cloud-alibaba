package com.sh.api.common.constant;

/**
 * Oauth2常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/17 21:55
 */
public interface OauthTwoConstant {

    /**
     * 角色权限前缀
     */
    String ROLE_PERMISSIONS_PREFIX = "ROLE_";

    /**
     * 授权
     */
    String AUTHORITIES = "authorities";

    /**
     * 白名单
     */
    String SECURE = "secure.ignore";

    /**
     * JWT令牌
     */
    interface Jwt {

        // keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks

        /**
         * 文件名称
         */
        String FILE_NAME = "jwt.jks";

        /**
         * 密钥口令
         */
        String KEY_PASSWORD = "jwt";

        /**
         * 密钥
         */
        String SECRET_KEY = "123456";
    }

    interface Token {

        /**
         * 名称英文
         */
        String NAME_EN = "nameEn";

        /**
         * jti
         */
        String JTI = "jti";

        /**
         *  令牌类型
         */
        String TOKEN_TYPE = "Bearer";

        /**
         * 认证信息Http请求头
         */
        String AUTHORIZATION = "Authorization";

        /**
         * 授权类型
         */
        String GRANT_TYPE = "grant_type";

        /**
         * 刷新token
         */
        String REFRESH_TOKEN = "refresh_token";
    }

    /**
     * 用户
     */
    interface User {

        /**
         * 用户名
         */
        String USERNAME = "username";

        /**
         * 密码
         */
        String PASSWORD = "password";
    }

    /**
     * 前台提示语
     */
    interface ForegroundPrompt {

        /**
         * 暂未登录或token已过期
         */
        String MSG_NO_LOGIN_OR_TOKEN_HAS_EXPIRED = "暂未登录或token已过期";

        /**
         * 没有访问权限
         */
        String MSG_NO_ACCESS_RIGHTS = "没有访问权限";
    }
}
