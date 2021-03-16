package com.sh.auth.config;

import cn.hutool.core.collection.CollUtil;
import com.sh.api.common.constant.OauthTwoConstant;
import com.sh.auth.token.service.UserInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 授权服务相关配置
 *
 *
 * @author 盛浩
 * @date 2021/1/9 16:40
 */
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserInfoServiceImpl userInfoService;

    /**
     * 客户端服务
     *
     * @param clients 客户端服务
     * @throws Exception e
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 令牌访问端点
     *
     * @param endpoints 端点
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenEnhancer> delegates = new ArrayList<>();
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        delegates.add(tokenEnhancer());
        delegates.add(accessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userInfoService)
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain);
    }

    /**
     * 令牌访问端点安全策略
     *
     * @param security 安全
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
    }

    /**
     * 使用非对称加密算法对token签名
     *
     * @return token签名
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    /**
     * 获取密钥
     *
     * @return 密钥
     */
    @Bean
    public KeyPair keyPair() {
        return new KeyStoreKeyFactory(
                new ClassPathResource(OauthTwoConstant.Jwt.FILE_NAME), OauthTwoConstant.Jwt.SECRET_KEY.toCharArray())
                .getKeyPair(OauthTwoConstant.Jwt.KEY_PASSWORD, OauthTwoConstant.Jwt.SECRET_KEY.toCharArray());
    }

    /**
     * JWT内容增强器
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> map = CollUtil.newHashMap();
            UserDetails  userDetails = (UserDetails) authentication.getUserAuthentication().getPrincipal();
            map.put("nameEn", userDetails.getUsername());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
            return accessToken;
        };
    }
}
