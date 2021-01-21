package com.sh.storage.service;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.seata.storage.dto.reduce.StorageReduceDto;
import com.sh.api.seata.storage.entity.StorageInfo;
import com.sh.storage.mapper.StorageInfoMapper;
import org.springframework.stereotype.Service;

/**
 * 库存信息业务
 *
 *
 * @author 盛浩
 * @date 2020/12/27 22:21
 */
@Service
public class StorageInfoServiceImpl extends ServiceImpl<StorageInfoMapper, StorageInfo> implements IService<StorageInfo> {

    /**
     * 减库存
     *
     * @param storageReduceDto 库存减少dto
     * @return 是否修改成功
     */
    public Boolean inventoryReduction(StorageReduceDto storageReduceDto) {

        StorageInfo storage = this.getOne(Wrappers.<StorageInfo>lambdaQuery().eq(StorageInfo::getProductId, storageReduceDto.getProductId()));

        return this.update(Wrappers.<StorageInfo>lambdaUpdate()
                .set(StorageInfo::getUsed, NumberUtil.add(storageReduceDto.getCount(), storage.getUsed()))
                .set(StorageInfo::getResidue, NumberUtil.sub(storage.getResidue(), storageReduceDto.getCount()))
                .eq(StorageInfo::getProductId, storageReduceDto.getProductId()));
    }

    /**
     * 查询库存信息
     *
     * @param productId 产品id
     * @return 库存信息
     */
    public StorageInfo queryStorageInfo(Long productId) {
        return this.getOne(Wrappers.<StorageInfo>lambdaQuery().eq(StorageInfo::getProductId, productId));
    }
}
