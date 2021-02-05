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
         * minio新增上传测试
         */
        String MINIO_SAVE_UPLOAD_TEST = "C:\\Users\\e\\Desktop\\save.jpg";

        /**
         * minio更新上传测试
         */
        String MINIO_UPDATE_UPLOAD_TEST = "C:\\Users\\e\\Desktop\\update.jpg";
    }
}
