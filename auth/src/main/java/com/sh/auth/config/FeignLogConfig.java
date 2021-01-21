package com.sh.auth.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign调用日志配置
 *
 *
 * @author 盛浩
 * @date 2020/12/26 17:05
 */
@Configuration
public class FeignLogConfig {

    /**
     * feign 客户端的日志记录，默认级别为none
     * logger.level 的具体级别如下：
     * none：不记录任何信息
     * basic：仅记录请求方法、url以及响应状态码和执行时间
     * headers：除了记录 basic级别的信息外，还会记录请求和响应的头信息
     * full：记录所有请求与响应的明细，包括头信息、请求体、元数据
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
