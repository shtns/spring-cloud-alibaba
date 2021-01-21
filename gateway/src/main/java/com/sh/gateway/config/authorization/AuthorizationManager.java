package com.sh.gateway.config.authorization;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.common.constant.OauthTwoConstant;
import com.sh.api.common.constant.RedisConstants;
import com.sh.api.common.constant.ResourceConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;

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

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {

        ServerHttpRequest request = authorizationContext.getExchange().getRequest();

        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(Boolean.TRUE));
        }

        String token = request.getHeaders().getFirst(OauthTwoConstant.Token.AUTHORIZATION);
        if (StrUtil.isBlank(token)) {
            return Mono.just(new AuthorizationDecision(Boolean.FALSE));
        }

        String accessPath = request.getURI().getPath();
        if (StrUtil.contains(accessPath, ResourceConstants.Url.MENU_ACCESS_PATH_PREFIX)) {
            accessPath = StrUtil.sub(accessPath, DigitalConstants.FIVE, accessPath.length());
        }
        String assemblePath = accessPath;

        Object object = this.redisTemplate.opsForHash().get(RedisConstants.PermissionCacheKey.ACCESS_PATHS, assemblePath);
        if (ObjectUtil.isNull(object)) {
            return Mono.just(new AuthorizationDecision(Boolean.FALSE));
        }
        List<String> permissions = Convert.toList(String.class, object);
        permissions = permissions.stream().map(permission -> permission = OauthTwoConstant.ROLE_PERMISSIONS_PREFIX + permission)
                .collect(Collectors.toList());
        List<String> assemblePermissions = CollUtil.newArrayList(permissions);

        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(role -> {
                    // roleId是请求用户的角色(格式:ROLE_{roleId})，authorities是请求资源所需要角色的集合
                    log.info("访问路径：{}", assemblePath);
                    log.info("用户角色信息：{}", role);
                    log.info("访问资源需要的权限：{}", StrUtil.concat(Boolean.TRUE, OauthTwoConstant.ROLE_PERMISSIONS_PREFIX, assemblePath));
                    return assemblePermissions.contains(role);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(Boolean.FALSE));
    }
}
