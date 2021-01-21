package com.sh.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单主启动
 *
 *
 * @author 盛浩
 * @date 2020/12/27 20:13
 */
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(value = "com.sh.order.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
