package com.shencrypt;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sh.api.common.constant.DigitalConstant;
import com.sh.api.common.constant.ResourceConstant;
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
        System.out.println("encodePwd："+ this.encrypt.encode("test"));
    }

    @Test
    public void zonedTest() {
        System.out.println(ZonedDateTime.now());
    }

    @Test
    public void pathTest() {
        String filePath = ResourceConstant.Url.MINIO_SAVE_UPLOAD_TEST;
        String fileName = StrUtil.sub(filePath, filePath.lastIndexOf(StringPool.BACK_SLASH), filePath.length());
        System.out.println("filePath："+ filePath);
        System.out.println("fileName：" + fileName.substring(DigitalConstant.ONE));
    }

    @Test
    public void test() {

        String requestPath = "/resource/check/path_value/123";
        System.out.println("原始：".concat(requestPath));

        String queryPath = null;
        int slashTwoIndex = 0;
        int start = 0;
        for (int i = 0; i < requestPath.length(); i++) {
            char str = requestPath.charAt(i);
            if (str == CharUtil.SLASH) {
                start++;
                slashTwoIndex = i++;
            }
            if (start == 2) {
                break;
            }
        }
        queryPath = StrUtil.sub(requestPath, DigitalConstant.ZERO, slashTwoIndex);
        System.out.println("1：".concat(queryPath));

        queryPath = StrUtil.sub(requestPath, DigitalConstant.ZERO, requestPath.lastIndexOf(StringPool.SLASH));
        System.out.println("2：".concat(queryPath));
    }
}
