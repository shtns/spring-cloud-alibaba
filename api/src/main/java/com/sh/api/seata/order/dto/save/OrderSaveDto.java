package com.sh.api.seata.order.dto.save;

import com.sh.api.common.constant.OrderInfoConstants;
import com.sh.api.seata.order.entity.OrderInfo;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 订单添加dto
 *
 *
 * @author 盛浩
 * @date 2020/12/25 21:01
 */
@Getter
@Setter
public class OrderSaveDto {

    /**
     * 用户id
     */
    @NotNull(message = OrderInfoConstants.ForegroundPrompt.USER_ID_CANNOT_BE_EMPTY)
    private Long userId;

    /**
     * 产品id
     */
    @NotNull(message = OrderInfoConstants.ForegroundPrompt.PRODUCT_ID_CANNOT_BE_EMPTY)
    private Long productId;

    /**
     * 数量
     */
    @NotNull(message = OrderInfoConstants.ForegroundPrompt.ORDER_NUMBER_CANNOT_BE_EMPTY)
    private Integer count;

    /**
     * 金额
     */
    @NotNull(message = OrderInfoConstants.ForegroundPrompt.ORDER_MONEY_CANNOT_BE_EMPTY)
    private BigDecimal money;

    /**
     * dto转entity
     *
     * @return 订单
     */
    public OrderInfo toOrder () {
        OrderInfo order = new OrderInfo();
        order.setUserId(this.userId);
        order.setProductId(this.productId);
        order.setCount(this.count);
        order.setMoney(this.money);
        return order;
    }
}
