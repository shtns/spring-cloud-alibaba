package com.sh.auth.token.controller;

import cn.hutool.core.util.ObjectUtil;
import com.sh.api.common.constant.UserInfoConstants;
import com.sh.api.common.util.R;
import com.sh.api.common.vo.Oauth2TokenVo;
import com.sh.auth.token.service.CustomTokenServiceImpl;
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
public class CustomTokenController {

    private final CustomTokenServiceImpl customGainTokenService;

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
        Oauth2TokenVo oauth2TokenVo = this.customGainTokenService.postAccessToken(principal, parameters);
        if (ObjectUtil.isNotNull(oauth2TokenVo)) {
            return R.ok(oauth2TokenVo);
        } else {
            return R.failed(UserInfoConstants.ForegroundPrompt.INCORRECT_ACCOUNT_OR_PASSWORD);
        }
    }

    /**
     * sentinel测试
     *
     * @param id id
     * @return 端口+id
     */
    @GetMapping(value = "/sentinel_test/{id}")
    public R<String> sentinelTest(@PathVariable Long id) {
        return this.customGainTokenService.sentinelTest(id);
    }
}
