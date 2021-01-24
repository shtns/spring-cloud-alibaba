package com.sh.api.common.constant;

/**
 * 资源常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/16 3:47
 */
public interface ResourceConstants {

    /**
     * 请求地址
     */
    interface Url {

        /**
         * 获取公钥
         */
        String GAIN_RSA = "/rsa/public_key";

        /**
         * 菜单访问地址前缀
         */
        String MENU_ACCESS_PATH_PREFIX = "/user";

        /**
         * sentinel测试接
         */
        String SENTINEL_TEST = "/user/sentinel_test/*";

        /**
         * minio新增上传测试
         */
        String MINIO_ADD_UPLOAD_TEST = "C:\\Users\\e\\Desktop\\add.jpg";

        /**
         * minio修改上传测试
         */
        String MINIO_UPDATE_UPLOAD_TEST = "C:\\Users\\e\\Desktop\\update.jpg";
    }
}
