package com.sh.gateway.config.cros;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 跨域配置
 *
 *
 * @author 盛浩
 * @date 2021/1/17 21:13
 */
@Configuration
public class CrossDomainConfig {

    /**
     * 处理跨域
     *
     * @return CorsWebFilter
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod(StringPool.ASTERISK);
        config.addAllowedOrigin(StringPool.ASTERISK);
        config.addAllowedHeader(StringPool.ASTERISK);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration(StringPool.SLASH.concat(StringPool.ASTERISK.concat(StringPool.ASTERISK)), config);
        return new CorsWebFilter(source);
    }
}
