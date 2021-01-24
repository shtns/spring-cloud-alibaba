package com.sh.organization.config;

import com.sh.api.common.constant.MinioConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 读取minio配置
 *
 *
 * @author 盛浩
 * @date 2021/1/23 19:44
 */
@Data
@Configuration
@ConfigurationProperties(prefix = MinioConstants.Config.MINIO)
public class MinioProperties {

    /**
     * 服务器地址
     */
    private String endpoint;

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * 桶名称
     */
    private String bucketName;
}
