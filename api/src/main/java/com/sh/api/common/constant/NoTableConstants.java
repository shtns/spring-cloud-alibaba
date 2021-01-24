package com.sh.api.common.constant;

/**
 * 订单号自动生成常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/22 20:20
 */
public interface NoTableConstants {

    /**
     * 前台提示语
     */
    interface Error {

        /**
         * 订单类型不能为空
         */
        String ORDER_TYPE_CANNOT_BE_EMPTY = "订单类型不能为空";

        /**
         * 未查询到此类型订单号
         */
        String NO_ORDER_NUMBER_OF_THIS_TYPE_WAS_FOUND = "未查询到此类型订单号";

        /**
         * 订单号生成失败
         */
        String ORDER_NUMBER_GENERATION_FAILED = "订单号生成失败";
    }
}
