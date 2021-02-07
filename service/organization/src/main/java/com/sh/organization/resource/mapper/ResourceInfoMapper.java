package com.sh.organization.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sh.api.organization.resource.entity.ResourceInfo;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/**
 * 资源信息映射
 *
 *
 * @author 盛浩
 * @date 2021/2/3 19:40
 */
public interface ResourceInfoMapper extends BaseMapper<ResourceInfo> {

    /**
     * 删除所有资源信息
     */
    @Update("truncate table resource_info")
    void removeAllResourceInfo();

    /**
     * 查询用户资源地址列表
     *
     * @param roleId 角色id
     * @return 资源地址列表
     */
    List<String> queryResourcePaths(Long roleId);
}
