package com.sh.organization.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置
 *
 *
 * @author 盛浩
 * @date 2020/9/19 16:39
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件（物理分页）
     *
     * mybatis自带的分页RowBounds属于内存分页，它是把符合条件的数据全查出来到内存中，然后返回你需要的那部分
     * 假设这个表它有一百条数据或者一千条问题都不大啊，但是如果有50万条数据或者上百万条数据
     * 它会产生两个严重的问题，第一是消耗内存让内存一直被占用，而且这些数据不一定是经常要查的
     * 第二查询速度慢，比如三百万条数据等了五分钟才能出来结果，这个用户体验也是非常差的，不合适
     *
     *
     * 多租户概念介绍
     * 多租户技术是一种软件架构，是实现如何在多用户环境下此处的多用户一般是指面向企业的用户，共用相同的系统，并且能确保各用户之间数据的隔离性
     * 简单来说多租户是一种架构，目的是多用户环境下使用同一套系统，且呢保证用户数据之间隔离
     * 一般有三种数据隔离方案：
     * 1、独立数据库：一个租户对应一个数据库，这种方案用户之间的数据隔离级别最高。
     * 优点：为不同的租户提供了独立的数据库，有助于数据模型的扩展和设计，满足不同租户的独特需求
     *           如果出现故障恢复数据比较简单。
     * 缺点：增加了数据库的安装数量，随时带来维护的成本和购置成本的增加。
     *
     * @return 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}