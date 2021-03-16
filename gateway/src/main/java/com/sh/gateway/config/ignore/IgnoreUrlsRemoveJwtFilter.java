package com.sh.gateway.config.ignore;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sh.api.common.constant.OauthTwoConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * 白名单过滤器
 *
 *
 * @author 盛浩
 * @date 2021/1/12 20:04
 */
@Component
@RequiredArgsConstructor
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    private final IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //白名单路径访问时需要移除JWT请求头
        List<String> urls = this.ignoreUrlsConfig.getUrls();
        if (urls.contains(request.getURI().getPath())) {
            PathMatcher pathMatcher = new AntPathMatcher();
            for (String ignoreUrl : this.ignoreUrlsConfig.getUrls()) {
                if (pathMatcher.match(ignoreUrl, request.getURI().getPath())) {
                    request = exchange.getRequest().mutate().header(OauthTwoConstant.Token.AUTHORIZATION, StringPool.EMPTY).build();
                    return chain.filter(exchange.mutate().request(request).build());
                }
            }
        }
        return chain.filter(exchange);
    }
}
