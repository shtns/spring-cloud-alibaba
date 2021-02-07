package com.sh.order.feign;

import com.sh.api.common.constant.MicroServiceNameConstants;
import com.sh.api.common.util.R;
import com.sh.api.seata.storage.dto.reduce.StorageReduceDto;
import com.sh.api.seata.storage.entity.StorageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 库存信息服务
 *
 *
 * @author 盛浩
 * @date 2020/12/27 20:21
 */
@FeignClient(value = MicroServiceNameConstants.STORAGE_SERVICE)
public interface StorageInfoService {

    /**
     * 减库存
     *
     * @param storageReduceDto 库存减少dto
     * @return 是否修改成功
     */
    @PostMapping(value = "/storage/inventory_reduction")
    R<Boolean> inventoryReduction(@RequestBody StorageReduceDto storageReduceDto);

    /**
     * 查询库存信息
     *
     * @param productId 产品id
     * @return 库存信息
     */
    @GetMapping(value = "/storage")
    R<StorageInfo> queryStorageInfo(@RequestParam(value = "productId") Long productId);
}
