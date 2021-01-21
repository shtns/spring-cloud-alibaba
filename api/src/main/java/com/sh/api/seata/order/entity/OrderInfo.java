package com.sh.api.seata.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * 订单信息
 *
 *
 * @author 盛浩
 * @date 2020/12/25 21:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderInfo extends Model<OrderInfo> {

    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Long orderId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 状态（0 创建中 1 已完成）
     */
    private Integer status;
}
