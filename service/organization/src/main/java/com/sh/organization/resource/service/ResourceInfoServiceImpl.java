package com.sh.organization.resource.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.constant.DigitalConstant;
import com.sh.api.organization.resource.entity.ResourceInfo;
import com.sh.organization.resource.mapper.ResourceInfoMapper;
import org.springframework.stereotype.Service;
import java.util.List;

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
     * 查询用户资源地址列表
     *
     * @param roleId 角色id
     * @return 资源地址列表
     */
    public List<String> queryResourcePaths(Long roleId) {
        return this.baseMapper.queryResourcePaths(roleId);
    }

    /**
     * 删除资源信息
     *
     * @param menuId 菜单id
     * @return 是否删除成功
     */
    public Boolean removeResourceInfo(Long menuId) {
        if (this.count(Wrappers.<ResourceInfo>lambdaQuery().eq(ResourceInfo::getMenuId, menuId)) < DigitalConstant.ONE) {
            return Boolean.TRUE;
        }
        return this.remove(Wrappers.<ResourceInfo>lambdaQuery().eq(ResourceInfo::getMenuId, menuId));
    }

    /**
     * 通过资源地址请求类型返回具体的请求类型
     *
     * @param requestPath 请求地址
     * @param requestType 请求类型
     * @return 具体的请求类型
     */
    public String returnRequestType(String requestPath, String requestType) {
        return this.getOne(Wrappers.<ResourceInfo>lambdaQuery()
                .select(ResourceInfo::getRequestType)
                .eq(ResourceInfo::getResourcePath, requestPath)
                .eq(ResourceInfo::getRequestType, requestType)).getRequestType();
    }
}
