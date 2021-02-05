package com.sh.organization.resource.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.organization.resource.entity.ResourceInfo;
import com.sh.organization.resource.mapper.ResourceInfoMapper;
import org.springframework.stereotype.Service;

/**
 * 资源信息业务
 *
 *
 * @author 盛浩
 * @date 2021/2/3 19:40
 */
@Service
public class ResourceInfoServiceImpl extends ServiceImpl<ResourceInfoMapper, ResourceInfo> implements IService<ResourceInfo> {

    /**
     * 删除资源信息
     *
     * @param menuId 菜单id
     * @return 是否删除成功
     */
    public Boolean removeResourceInfo(Long menuId) {
        if (this.count(Wrappers.<ResourceInfo>lambdaQuery().eq(ResourceInfo::getMenuId, menuId)) < DigitalConstants.ONE) {
            return Boolean.TRUE;
        }
        return this.remove(Wrappers.<ResourceInfo>lambdaQuery().eq(ResourceInfo::getMenuId, menuId));
    }
}
