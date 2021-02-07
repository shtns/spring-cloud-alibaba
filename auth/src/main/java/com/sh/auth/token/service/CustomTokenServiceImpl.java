package com.sh.auth.token.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.common.constant.OauthTwoConstant;
import com.sh.api.common.constant.SentinelConstants;
import com.sh.api.common.util.R;
import com.sh.api.common.vo.Oauth2TokenVo;
import com.sh.auth.feign.service.OrganizationService;
import com.sh.auth.token.handler.CustomBlockHandler;
import com.sh.auth.token.handler.CustomFallbackHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpServerErrorException;
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
public class CustomTokenServiceImpl {

    private final TokenEndpoint tokenEndpoint;

    private final OrganizationService organizationService;

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
            if (! this.organizationService.userLogin(parameters.get(OauthTwoConstant.User.USERNAME),
                    parameters.get(OauthTwoConstant.User.PASSWORD)).getData()) {
                return null;
            }
        }

        //传入相关参数，调用底层获取令牌接口
        OAuth2AccessToken oAuth2AccessToken = this.tokenEndpoint.postAccessToken(principal, parameters).getBody();

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
     * sentinel测试
     *
     * @param id id
     * @return 端口+id
     */
    @SentinelResource(value = "sentinelTest",
            blockHandlerClass = CustomBlockHandler.class, blockHandler = SentinelConstants.BlockHandler.SPECIFIC_TREATMENT_METHOD,
            fallbackClass = CustomFallbackHandler.class, fallback = SentinelConstants.Fallback.SPECIFIC_TREATMENT_METHOD)
    public R<String> sentinelTest(Long id) {

        if (id == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, SentinelConstants.ForegroundPrompt.ID_CANNOT_BE_EMPTY);
        }
        if (id.equals(Long.valueOf(DigitalConstants.ZERO))) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, SentinelConstants.ForegroundPrompt.ID_CANNOT_BE_ZERO);
        }
        return this.organizationService.sentinelTest(id);
    }
}
