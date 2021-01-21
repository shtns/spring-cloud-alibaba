package com.sh.auth.key.controller;

import com.sh.auth.key.service.KeyPairServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * RSA公钥管理
 *
 *
 * @author 盛浩
 * @date 2021/1/12 19:30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rsa")
public class KeyPairController {

    private final KeyPairServiceImpl keyPairService;

    @GetMapping("/public_key")
    public Map<String, Object> getKey() {
        return this.keyPairService.gainKey();
    }
}
