package com.shencrypt;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.common.constant.ResourceConstants;
import com.sh.auth.AuthApplication;
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
@SpringBootTest(classes = AuthApplication.class)
public class EncryptApplicationTests {

    @Resource
    private StringEncryptor stringEncryptor;

    private final PasswordEncoder encrypt = new BCryptPasswordEncoder();

    @Test
    public void jasyptEncryptTest() {
        System.out.println("jasyptPwd：" + stringEncryptor.encrypt("sh20000907"));
    }

    @Test
    public void encodeEncryptTest() {
        System.out.println("encodePwd："+ this.encrypt.encode("123456"));
    }

    @Test
    public void zonedTest() {
        System.out.println(ZonedDateTime.now());
    }

    @Test
    public void pathTest() {
        String filePath = ResourceConstants.Url.MINIO_SAVE_UPLOAD_TEST;
        String fileName = StrUtil.sub(filePath, filePath.lastIndexOf(StringPool.BACK_SLASH), filePath.length());
        System.out.println("filePath："+ filePath);
        System.out.println("fileName：" + fileName.substring(DigitalConstants.ONE));
    }
}
