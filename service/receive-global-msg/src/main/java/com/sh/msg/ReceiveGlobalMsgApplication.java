package com.sh.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 接收全局消息主启动
 * 
 * 
 * @author 盛浩
 * @date 2021/1/19 22:20
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ReceiveGlobalMsgApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiveGlobalMsgApplication.class, args);
    }
}
