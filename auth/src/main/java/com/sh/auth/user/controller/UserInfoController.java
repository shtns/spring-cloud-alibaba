package com.sh.auth.user.controller;

import com.sh.api.common.util.R;
import com.sh.auth.user.service.UserInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息管理
 *
 *
 * @author 盛浩
 * @date 2021/1/20 15:19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserInfoController {

    private final UserInfoServiceImpl userInfoService;

    /**
     * sentinel测试
     *
     * @param id id
     * @return 端口+id
     */
    @GetMapping(value = "/sentinel_test/{id}")
    public R<String> sentinelTest(@PathVariable Long id) {
        return this.userInfoService.sentinelTest(id);
    }
}
