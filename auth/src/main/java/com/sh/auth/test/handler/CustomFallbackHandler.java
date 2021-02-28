package com.sh.auth.test.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sh.api.common.constant.CommonConstants;
import com.sh.api.common.util.R;

/**
 * 自定义fallback异常处理提示
 *
 *
 * @author 盛浩
 * @date 2020/12/22 21:15
 */
public class CustomFallbackHandler {

    public static R<String> handlerFallbackException(Long id, Throwable e) {
        return R.failed(StrUtil.concat(Boolean.TRUE, CommonConstants.ForegroundPrompt.SERVER_INTERNAL_ERROR_PLEASE_TRY_AGAIN_LATER,
                StringPool.COLON, e.getMessage()));
    }
}
