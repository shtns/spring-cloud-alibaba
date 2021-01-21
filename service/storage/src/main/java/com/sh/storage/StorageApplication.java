package com.sh.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 库存主启动
 *
 *
 * @author 盛浩
 * @date 2020/12/27 22:14
 */
@EnableDiscoveryClient
@MapperScan(value = "com.sh.storage.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }
}
