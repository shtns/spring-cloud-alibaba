package com.sh.auth.token.service;

import cn.hutool.core.util.StrUtil;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.sh.api.common.config.ServerErrorException;
import com.sh.api.common.constant.OauthTwoConstant;
import com.sh.api.common.constant.UserInfoConstant;
import com.sh.api.common.vo.Oauth2TokenVo;
import com.sh.auth.feign.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * 自定义获取令牌业务
 *
 *
 * @author 盛浩
 * @date 2021/1/14 14:34
 */
@Service
@RequiredArgsConstructor
public class AuthTokenServiceImpl {

    private final TokenEndpoint tokenEndpoint;

    private final OrganizationService organizationService;

    private final KeyPair keyPair;

    /**
     * 获取令牌
     *
     * @param principal 用户相关信息
     * @param parameters 获取令牌参数
     * @return 令牌信息
     * @throws HttpRequestMethodNotSupportedException e
     */
    public Oauth2TokenVo postAccessToken(Principal principal, Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {

        //当授权类型不为刷新token时才进行用户登入操作
        if (! StrUtil.equals(parameters.get(OauthTwoConstant.Token.GRANT_TYPE), OauthTwoConstant.Token.REFRESH_TOKEN)) {
            //调用用户登入服务，返回true和false，false这里直接return，控制层判断给提示
            if (! organizationService.userLogin(parameters.get(OauthTwoConstant.User.USERNAME),
                    parameters.get(OauthTwoConstant.User.PASSWORD)).getData()) {
                throw new ServerErrorException(UserInfoConstant.ForegroundPrompt.INCORRECT_ACCOUNT_OR_PASSWORD);
            }
        }

        //传入相关参数，调用底层获取令牌接口
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();

        //返回令牌信息
        return Oauth2TokenVo.builder()
                .accessToken(oAuth2AccessToken.getValue())
                .tokenType(OauthTwoConstant.Token.TOKEN_TYPE)
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .scope(oAuth2AccessToken.getScope().toString())
                .nameEn(oAuth2AccessToken.getAdditionalInformation().get(OauthTwoConstant.Token.NAME_EN).toString())
                .jti(oAuth2AccessToken.getAdditionalInformation().get(OauthTwoConstant.Token.JTI).toString())
                .build();
    }

    /**
     * 获取公钥检查token是否合法
     *
     * @return 公钥
     */
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return new JWKSet(new RSAKey.Builder(publicKey).build()).toJSONObject();
    }
}
