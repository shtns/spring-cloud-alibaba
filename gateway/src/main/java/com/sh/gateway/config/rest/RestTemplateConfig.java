package com.sh.gateway.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * restTemplate配置
 *
 *
 * @author 盛浩
 * @date 2020/12/25 22:19
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    //@LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }
}