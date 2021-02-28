//package com.sh.gateway.handler;
//
//import com.sh.api.common.config.ServerErrorException;
//import com.sh.api.common.constant.OauthTwoConstant;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * 处理未认证或token过期
// *
// *
// * @author 盛浩
// * @date 2021/1/12 20:01
// */
//@Component
//public class RestAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
//
//    @Override
//    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
//        throw new ServerErrorException(OauthTwoConstant.ForegroundPrompt.MSG_NO_LOGIN_OR_TOKEN_HAS_EXPIRED);
///*        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(HttpStatus.OK);
//        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        DataBuffer buffer =  response.bufferFactory().wrap(JSONObject.fromObject(R.forbidden()).toString().getBytes(StandardCharsets.UTF_8));
//        return response.writeWith(Mono.just(buffer));*/
//    }
//}
