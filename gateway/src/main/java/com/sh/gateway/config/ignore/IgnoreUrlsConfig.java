package com.sh.gateway.config.ignore;

import com.sh.api.common.constant.OauthTwoConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 网关白名单配置
 *
 *
 * @author 盛浩
 * @date 2021/1/12 19:56
 */
@Data
@Component
@ConfigurationProperties(prefix= OauthTwoConstant.SECURE)
public class IgnoreUrlsConfig {
    private List<String> urls;
}
