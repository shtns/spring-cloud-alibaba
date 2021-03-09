package com.sh.api.seata.order.dto.details;

import com.sh.api.common.constant.OrderInfoConstant;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * 订单详情dto
 *
 *
 * @author 盛浩
 * @date 2020/12/27 23:09
 */
@Getter
@Setter
public class OrderDetailsDto {

    /**
     * 订单id
     */
    @NotNull(message = OrderInfoConstant.ForegroundPrompt.ORDER_ID_CANNOT_BE_EMPTY)
    private Long orderId;

    /**
     * 用户id
     */
    @NotNull(message = OrderInfoConstant.ForegroundPrompt.USER_ID_CANNOT_BE_EMPTY)
    private Long userId;

    /**
     * 产品id
     */
    @NotNull(message = OrderInfoConstant.ForegroundPrompt.PRODUCT_ID_CANNOT_BE_EMPTY)
    private Long productId;
}
