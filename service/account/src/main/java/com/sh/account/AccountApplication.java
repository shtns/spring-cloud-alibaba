package com.sh.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 账户主启动
 *
 *
 * @author 盛浩
 * @date 2020/12/27 22:55
 */
@EnableDiscoveryClient
@MapperScan(value = "com.sh.account.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
