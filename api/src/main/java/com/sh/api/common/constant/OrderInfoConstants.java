package com.sh.api.common.constant;

/**
 * 订单信息常量值
 *
 *
 * @author 盛浩
 * @date 2021/1/19 20:51
 */
public interface OrderInfoConstants {

    /**
     * 前台提示语
     */
    interface ForegroundPrompt {

        /**
         * 订单id不能为空
         */
        String ORDER_ID_CANNOT_BE_EMPTY = "订单id不能为空";

        /**
         * 用户id不能为空
         */
        String USER_ID_CANNOT_BE_EMPTY = "用户id不能为空";

        /**
         * 产品id不能为空
         */
        String PRODUCT_ID_CANNOT_BE_EMPTY = "产品id不能为空";

        /**
         * 订单数量不能为空
         */
        String ORDER_NUMBER_CANNOT_BE_EMPTY = "订单数量不能为空";

        /**
         * 订单金额不能为空
         */
        String ORDER_MONEY_CANNOT_BE_EMPTY = "订单金额不能为空";
    }
}
