package com.sh.organization.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sh.api.organization.role.dto.page.RolePageDto;
import com.sh.api.organization.role.entity.RoleInfo;
import com.sh.api.organization.role.vo.details.RoleDetailsVo;
import org.apache.ibatis.annotations.Param;

/**
 * 角色信息映射
 *
 *
 * @author 盛浩
 * @date 2021/1/19 18:00
 */
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {

    /**
     * 分页查询角色详情
     *
     * @param iPage 分页插件
     * @param rolePageDto 角色分页dto
     * @return 角色详情分页vo
     */
    IPage<RoleDetailsVo> pageQueryRoleDetails(IPage<RoleInfo> iPage, @Param(value = "query") RolePageDto rolePageDto);
}
