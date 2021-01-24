package com.sh.auth.token.service;

import com.sh.api.common.constant.OauthTwoConstant;
import com.sh.api.common.constant.UserInfoConstants;
import com.sh.api.common.util.R;
import com.sh.api.common.vo.Oauth2TokenVo;
import com.sh.auth.feign.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import java.security.Principal;
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
public class CustomGainTokenServiceImpl {

    private final TokenEndpoint tokenEndpoint;

    private final OrganizationService userInfoService;

    /**
     * 获取令牌
     *
     * @param principal 用户相关信息
     * @param parameters 获取令牌参数
     * @return 令牌信息
     * @throws HttpRequestMethodNotSupportedException e
     */
    public R<Oauth2TokenVo> postAccessToken(Principal principal, Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {

        if (! this.userInfoService.userLogin(parameters.get("username"), parameters.get("password")).getData()) {
            return R.failed(UserInfoConstants.ForegroundPrompt.INCORRECT_ACCOUNT_OR_PASSWORD);
        }

        OAuth2AccessToken oAuth2AccessToken = this.tokenEndpoint.postAccessToken(principal, parameters).getBody();
        return R.ok(Oauth2TokenVo.builder()
                .nameEn(oAuth2AccessToken.getAdditionalInformation().get(OauthTwoConstant.Token.NAME_EN).toString())
                .scope(oAuth2AccessToken.getScope().toString())
                .jti(oAuth2AccessToken.getAdditionalInformation().get(OauthTwoConstant.Token.JTI).toString())
                .tokenType(OauthTwoConstant.Token.TOKEN_TYPE)
                .accessToken(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .build());
    }
}
