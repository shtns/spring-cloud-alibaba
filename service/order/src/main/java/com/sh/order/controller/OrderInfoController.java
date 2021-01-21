package com.sh.order.controller;

import com.sh.api.common.util.R;
import com.sh.api.seata.order.dto.add.OrderSaveDto;
import com.sh.api.seata.order.dto.details.OrderDetailsDto;
import com.sh.api.seata.order.vo.OrderDetailsVo;
import com.sh.order.service.OrderInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 订单管理
 *
 *
 * @author 盛浩
 * @date 2020/12/27 20:15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderInfoController {

    private final OrderInfoServiceImpl orderInfoService;

    /**
     * 添加订单信息
     *
     * @param orderSaveDto 订单添加dto
     * @return 是否新增成功
     */
    @PostMapping
    public R<Boolean> saveOrderInfo(@RequestBody @Valid OrderSaveDto orderSaveDto) {
        return R.ok(this.orderInfoService.addOrder(orderSaveDto));
    }

    /**
     * 订单详情
     *
     * @param orderDetailsDto 订单详情dto
     * @return 订单详情vo
     */
    @GetMapping(value = "/details")
    public R<OrderDetailsVo> getOrderDetails(@Valid OrderDetailsDto orderDetailsDto) {
        return R.ok(this.orderInfoService.getOrderDetails(orderDetailsDto));
    }
}
