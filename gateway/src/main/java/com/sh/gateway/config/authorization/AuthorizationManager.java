package com.sh.gateway.config.authorization;

import cn.hutool.core.util.StrUtil;
import com.sh.api.common.constant.OauthTwoConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 鉴权管理器
 *
 *
 * @author 盛浩
 * @date 2021/1/12 19:51
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {

        ServerHttpRequest request = authorizationContext.getExchange().getRequest();

        //跨域请求放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(Boolean.TRUE));
        }

        //token为空拒绝访问
        String token = request.getHeaders().getFirst(OauthTwoConstant.Token.AUTHORIZATION);
        if (StrUtil.isBlank(token)) {
            return Mono.just(new AuthorizationDecision(Boolean.FALSE));
        }

        //获取访问路径
        String accessPath = request.getURI().getPath();
        //访问路径加上ROLE_前缀
        String rolePath = StrUtil.concat(Boolean.TRUE, OauthTwoConstant.ROLE_PERMISSIONS_PREFIX, accessPath);

        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(role -> {
                    log.info("访问路径：{}", accessPath);
                    log.info("用户角色信息：{}", role);
                    log.info("访问资源需要的权限：{}", rolePath);
                   log.info("------------------------------------------");
                    return StrUtil.equals(rolePath, role);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(Boolean.FALSE));
    }
}
