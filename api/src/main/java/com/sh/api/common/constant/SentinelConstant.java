package com.sh.api.common.constant;

/**
 * sentinel常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/21 1:10
 */
public interface SentinelConstant {

    /**
     * 控制台异常
     */
    interface BlockHandler {

        /**
         * 具体处理方法
         */
        String SPECIFIC_TREATMENT_METHOD = "handlerBackException";
    }

    /**
     * 运行时异常
     */
    interface Fallback {

        /**
         * 具体处理方法
         */
        String SPECIFIC_TREATMENT_METHOD = "handlerFallbackException";
    }

    /**
     * 前台提示语
     */
    interface ForegroundPrompt {

        /**
         * id不能为空
         */
        String ID_CANNOT_BE_EMPTY = "id不能为空";

        /**
         * id不能为0
         */
        String ID_CANNOT_BE_ZERO = "id不能为0";
    }
}
