package com.sh.auth.key.service;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * RSA公钥业务
 *
 *
 * @author 盛浩
 * @date 2021/1/14 14:41
 */
@Service
@RequiredArgsConstructor
public class KeyPairServiceImpl {

    private final KeyPair keyPair;

    /**
     * 获取公钥
     *
     * @return 公钥信息
     */
    public Map<String, Object> gainKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return new JWKSet(new RSAKey.Builder(publicKey).build()).toJSONObject();
    }
}
