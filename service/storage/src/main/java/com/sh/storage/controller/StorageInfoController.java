package com.sh.storage.controller;

import com.sh.api.common.util.R;
import com.sh.api.seata.storage.dto.reduce.StorageReduceDto;
import com.sh.api.seata.storage.entity.StorageInfo;
import com.sh.storage.service.StorageInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 库存信息管理
 *
 *
 * @author 盛浩
 * @date 2020/12/27 22:16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
public class StorageInfoController {

    private final StorageInfoServiceImpl storageInfoService;

    /**
     * 减库存
     *
     * @param storageReduceDto 库存减少dto
     * @return 是否修改成功
     */
    @PostMapping(value = "/inventory_reduction")
    public R<Boolean> inventoryReduction(@RequestBody StorageReduceDto storageReduceDto) {
        return R.ok(storageInfoService.inventoryReduction(storageReduceDto));
    }

    /**
     * 查询库存信息
     *
     * @param productId 产品id
     * @return 库存信息
     */
    @GetMapping
    public R<StorageInfo> queryStorageInfo(Long productId) { return R.ok(storageInfoService.queryStorageInfo(productId)); }
}
