package com.sh.auth.token.controller;

import com.sh.api.common.util.R;
import com.sh.api.common.vo.Oauth2TokenVo;
import com.sh.auth.token.service.AuthTokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌管理
 *
 *
 * @author 盛浩
 * @date 2021/1/14 14:18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/oauth")
public class AuthTokenController {

    private final AuthTokenServiceImpl authTokenService;

    /**
     * 获取令牌
     *
     * @param principal 用户相关信息
     * @param parameters 获取令牌参数
     * @return 令牌信息
     * @throws HttpRequestMethodNotSupportedException e
     */
    @PostMapping(value = "/token")
    public R<Oauth2TokenVo> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {
        return R.ok(this.authTokenService.postAccessToken(principal, parameters));
    }

    /**
     * 获取公钥检查token是否合法
     *
     * @return 公钥
     */
    @GetMapping("/rsa_key")
    public Map<String, Object> getKey() {
        return this.authTokenService.getKey();
    }
}
