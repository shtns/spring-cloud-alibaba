package com.sh.order.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.constant.DigitalConstant;
import com.sh.api.seata.account.dto.reduce.AccountReduceDto;
import com.sh.api.seata.order.dto.save.OrderSaveDto;
import com.sh.api.seata.order.dto.details.OrderDetailsDto;
import com.sh.api.seata.order.entity.OrderInfo;
import com.sh.api.seata.order.vo.OrderDetailsVo;
import com.sh.api.seata.storage.dto.reduce.StorageReduceDto;
import com.sh.order.feign.AccountInfoService;
import com.sh.order.feign.StorageInfoService;
import com.sh.order.mapper.OrderInfoMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单业务
 *
 *
 * @author 盛浩
 * @date 2020/12/27 20:23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IService<OrderInfo> {

    private final StorageInfoService storageInfoService;

    private final AccountInfoService accountInfoService;

    /**
     * 保存订单信息
     *
     * @param orderSaveDto 订单添加dto
     * @return 是否保存成功
     */
    @GlobalTransactional(name = "create_order", rollbackFor = Exception.class)
    public Boolean saveOrderInfo(OrderSaveDto orderSaveDto) {

        log.info("------>开始新建订单");
        OrderInfo orderInfo = orderSaveDto.toOrder();
        this.save(orderInfo);

        log.info("------>订单微服务开始调用库存");
        storageInfoService.inventoryReduction(new StorageReduceDto(orderInfo.getProductId(), orderSaveDto.getCount()));

        log.info("------>订单微服务开始调用账户");
        accountInfoService.loseBalance(new AccountReduceDto(orderInfo.getUserId(), orderInfo.getMoney()));

        log.info("------>开始修改订单状态");
        updateOrderStatus(orderInfo.getOrderId());

        log.info("订单创建完成！");

        return true;
    }

    /**
     * 修改订单状态为已完成
     *
     * @param orderId 订单id
     */
    private void updateOrderStatus(Long orderId) {
        this.update(Wrappers.<OrderInfo>lambdaUpdate().set(OrderInfo::getStatus, DigitalConstant.ONE).eq(OrderInfo::getOrderId, orderId));
    }

    /**
     * 订单详情
     *
     * @param orderDetailsDto 订单详情dto
     * @return 订单详情vo
     */
    public OrderDetailsVo getOrderDetails(OrderDetailsDto orderDetailsDto) {
        return new OrderDetailsVo(getById(orderDetailsDto.getOrderId()),
                accountInfoService.queryAccountInfo(orderDetailsDto.getUserId()).getData(),
                storageInfoService.queryStorageInfo(orderDetailsDto.getProductId()).getData());
    }
}
