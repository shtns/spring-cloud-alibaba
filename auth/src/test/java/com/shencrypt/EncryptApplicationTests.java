package com.shencrypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.annotation.Resource;
import java.time.ZonedDateTime;

/**
 * 加密测试类
 *
 *
 * @author 盛浩
 * @date 2021/1/19 18:06
 */
@SpringBootTest
public class EncryptApplicationTests {

    @Resource
    private StringEncryptor stringEncryptor;

    private final PasswordEncoder ENCODE = new BCryptPasswordEncoder();

    @Test
    public void jasyptEncryptTest() {
        System.out.println("jasyptPwd：" + stringEncryptor.encrypt("sh20000907"));
    }

    @Test
    public void encodeEncryptTest() {
        System.out.println("encodePwd："+ ENCODE.encode("123456"));
    }

    @Test
    public void zonedTest() {
        System.out.println(ZonedDateTime.now());
    }
}
