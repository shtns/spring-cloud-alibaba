package com.sh.gateway.config.authorization;

import cn.hutool.core.util.StrUtil;
import com.sh.api.common.config.ServerErrorException;
import com.sh.api.common.constant.CommonConstant;
import com.sh.api.common.constant.OauthTwoConstant;
import com.sh.api.common.constant.RedisConstant;
import com.sh.api.common.constant.ResourceConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Objects;

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

    private final RedisTemplate<String, List<String>> redisTemplate;

    private final RestTemplate restTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {

        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String requestPath = request.getURI().getPath();

        //请求类型为空拒绝报错异常
        String requestType = request.getMethodValue();
        if (StrUtil.isBlank(requestType)) {
            throw new ServerErrorException(CommonConstant.ForegroundPrompt.THE_REQUEST_TYPE_WAS_NOT_OBTAINED);
        }

        //请求地址不在资源地址列表中，报错404
        if (! Objects.requireNonNull(this.redisTemplate.opsForValue().get(RedisConstant.ResourceCacheKey.RESOURCE_PATHS)).contains(requestPath)) {
            throw new ServerErrorException(ResourceConstant.ForegroundPrompt.THE_REQUESTED_RESOURCE_DOES_NOT_EXIST);
        }

        //token为空拒绝访问
        String token = request.getHeaders().getFirst(OauthTwoConstant.Token.AUTHORIZATION);
        if (StrUtil.isBlank(token)) {
            throw new ServerErrorException(OauthTwoConstant.ForegroundPrompt.MSG_NO_LOGIN_OR_TOKEN_HAS_EXPIRED);
            //return Mono.just(new AuthorizationDecision(Boolean.FALSE));
        }

        //正常情况只需要判断路径就可以了，这里不用再校验请求类型，但是请求风格使用的是restful规范，这就导致了比如说用户管理类路径为/user
        //这时候写两个接口，保存、修改、按照restful规范这里不用再写路径名，而是不同接口采用不同类型注解，如：PostMapping、PutMapping
        //前提是项目中不用路径传值，这就导致了一个路径可能出现多个接口的问题，所以需要再判断请求类型确保唯一
        //获取此次访问的请求类型
        String specificRequestType = this.restTemplate.getForObject(ResourceConstant.Url.RESOURCE_REQUEST_TYPE_PATH.concat(requestPath)
                .concat(ResourceConstant.Url.PARAM).concat(requestType), String.class);
        if (StrUtil.isBlank(specificRequestType)) {
            throw new ServerErrorException(ResourceConstant.ForegroundPrompt.VERIFY_THAT_THE_REQUEST_TYPE_IS_CORRECT);
        }

        //访问路径加上ROLE_前缀
        String roleResource = StrUtil.concat(Boolean.TRUE, OauthTwoConstant.ROLE_PERMISSIONS_PREFIX, requestPath);

        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(resource -> {
                    log.info("请求地址：{}", requestPath);
                    log.info("用户角色信息：{}", resource);
                    log.info("需要的权限：{}", roleResource);
                   log.info("------------------------------------------");
                    return StrUtil.equals(roleResource, resource) && StrUtil.equals(requestType, specificRequestType);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(Boolean.FALSE));
    }
}
