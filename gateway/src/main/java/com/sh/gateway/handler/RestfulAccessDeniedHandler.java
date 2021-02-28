package com.sh.gateway.handler;

import com.sh.api.common.config.ServerErrorException;
import com.sh.api.common.constant.OauthTwoConstant;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 处理没有权限访问
 *
 *
 * @author 盛浩
 * @date 2021/1/12 19:57
 */
@Component
public class RestfulAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        throw new ServerErrorException(OauthTwoConstant.ForegroundPrompt.MSG_NO_ACCESS_RIGHTS);
/*        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer buffer =  response.bufferFactory().wrap(JSONObject.fromObject(R.unauthorized()).toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));*/
    }
}
