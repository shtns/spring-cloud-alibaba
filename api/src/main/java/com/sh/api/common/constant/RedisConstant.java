package com.sh.api.common.constant;

/**
 * redis常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/17 22:32
 */
public interface RedisConstant {

    /**
     * 权限缓存key
     */
    interface PermissionCacheKey {

        /**
         * 权限访问路径列表key
         */
        String ACCESS_PATHS= "access_paths_key";
    }

    /**
     * 国家缓存key
     */
    interface CountryCacheKey {

        /**
         * 国家key
         */
        String COUNTRY = "country_key";

        /**
         * 国家城市key
         */
        String COUNTRY_CITY = "country_city_key";
    }

    /**
     * 资源缓存key
     */
    interface ResourceCacheKey {

        /**
         * 资源地址列表key
         */
        String RESOURCE_PATHS = "resource_paths_key";
    }
}
