package com.sh.api.seata.order.vo;

import com.sh.api.seata.account.entity.AccountInfo;
import com.sh.api.seata.order.entity.OrderInfo;
import com.sh.api.seata.storage.entity.StorageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 订单详情vo
 *
 *
 * @author 盛浩
 * @date 2020/12/27 23:13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsVo {

    /**
     * 订单信息
     */
    private OrderInfo orderInfo;

    /**
     * 账户信息
     */
    private AccountInfo accountInfo;

    /**
     * 库存信息
     */
    private StorageInfo storageInfo;

//    /**
//     * entity转vo
//     *
//     *
//     * @param orderInfo 订单信息
//     * @param accountInfo 账户信息
//     * @param storageInfo 库存信息
//     */
//    public OrderDetailsVo(OrderInfo orderInfo, AccountInfo accountInfo, StorageInfo storageInfo) {
//        this.orderInfo = orderInfo;
//        this.accountInfo = accountInfo;
//        this.storageInfo = storageInfo;
//    }
}
