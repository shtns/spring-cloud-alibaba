package com.sh.auth.test.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sh.api.common.constant.CommonConstant;
import com.sh.api.common.util.R;

/**
 * 自定义降级异常处理提示
 * sentinel系统默认提示：Blocked by Sentinel（flow Limiting）
 *
 *
 * @author 盛浩
 * @date 2020/12/21 23:01
 */
public class CustomBlockHandler {

    public static R<String> handlerBackException(Long id, BlockException blockException) {
        return R.failed(CommonConstant.ForegroundPrompt.THE_SERVER_IS_PROCESSING_PLEASE_TRY_AGAIN_LATER);
    }
}
