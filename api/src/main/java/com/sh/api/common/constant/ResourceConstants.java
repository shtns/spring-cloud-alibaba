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
         * sentinel测试
         */
        String SENTINEL_TEST = "/oauth/sentinel_test/*";

        /**
         * 组织服务转发头
         */
        String ORGANIZATION = "/organization";

        /**
         * minio新增上传测试
         */
        String MINIO_SAVE_UPLOAD_TEST = "C:\\Users\\e\\Desktop\\save.jpg";

        /**
         * minio更新上传测试
         */
        String MINIO_UPDATE_UPLOAD_TEST = "C:\\Users\\e\\Desktop\\update.jpg";

        /**
         * 资源请求路径地址
         */
        String RESOURCE_REQUEST_TYPE_PATH = "http://localhost:2097/resource/request_type?requestPath=";
        String PARAM = "&requestType=";

        /**
         * 资源地址列表
         */
        String RESOURCE_PATHS = "http://localhost:2097/resource/all";
    }

    /**
     * 前台提示语
     */
    interface ForegroundPrompt {

        /**
         * 请求资源不存在
         */
        String THE_REQUESTED_RESOURCE_DOES_NOT_EXIST = "请求资源不存在";

        /**
         * 请确认请求类型是否正确
         */
        String VERIFY_THAT_THE_REQUEST_TYPE_IS_CORRECT = "请确认请求类型是否正确";
    }
}
