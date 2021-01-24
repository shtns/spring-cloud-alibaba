package com.sh.organization.notable.controller;

import com.sh.api.common.util.R;
import com.sh.organization.notable.service.NoTableServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单号自动生成管理
 *
 *
 * @author 盛浩
 * @date 2021/1/22 17:42
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/generate_order_number")
public class NoTableController {

    private final NoTableServiceImpl noTableService;

    @PostMapping
    public R<String> generateOrderNumber(@RequestBody String orderType) {
        return R.ok(this.noTableService.generateOrderNumber(orderType));
    }
}
