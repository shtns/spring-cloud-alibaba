package com.sh.organization;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 组织主启动
 *
 *
 * @author 盛浩
 * @date 2021/1/16 13:24
 */
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"com.sh.organization.user.mapper","com.sh.organization.menu.mapper","com.sh.organization.role.mapper"
        ,"com.sh.organization.rolemenu.mapper","com.sh.organization.city.mapper","com.sh.organization.country.mapper"
        ,"com.sh.organization.tms.mapper","com.sh.organization.notable.mapper","com.sh.organization.resource.mapper"})
public class OrganizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrganizationApplication.class, args);
    }
}
