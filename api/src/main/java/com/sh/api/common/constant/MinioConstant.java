package com.sh.api.common.constant;

/**
 * minio常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/24 18:29
 */
public interface MinioConstant {

    /**
     * 配置
     */
    interface Config {

        /**
         * 读取yml配置前缀
         */
        String MINIO = "minio";
    }

    /**
     * 桶名称
     */
    interface BucketName {

        /**
         * 头像
         */
        String HEAD_PORTRAIT = "head";
    }
}
