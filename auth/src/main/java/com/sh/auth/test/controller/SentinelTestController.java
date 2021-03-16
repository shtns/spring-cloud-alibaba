package com.sh.auth.test.controller;

import com.sh.api.common.util.R;
import com.sh.auth.test.service.SentinelTestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sentinel测试管理
 *
 *
 * @author 盛浩
 * @date 2021/2/28 19:47
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sentinel")
public class SentinelTestController {

    private final SentinelTestServiceImpl sentinelTestService;

    /**
     * sentinel测试
     *
     * @param id id
     * @return 端口+id
     */
    @GetMapping(value = "/test")
    public R<String> sentinelTest(Long id) {
        return sentinelTestService.sentinelTest(id);
    }
}
