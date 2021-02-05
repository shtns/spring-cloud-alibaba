package com.sh.organization.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sh.api.organization.menu.dto.page.MenuPageDto;
import com.sh.api.organization.menu.entity.MenuInfo;
import com.sh.api.organization.menu.vo.details.MenuDetailsVo;
import org.apache.ibatis.annotations.Param;

/**
 * 菜单信息映射
 *
 *
 * @author 盛浩
 * @date 2021/1/18 22:00
 */
public interface MenuInfoMapper extends BaseMapper<MenuInfo> {

    /**
     * 分页查询菜单详情
     *
     * @param iPage 分页插件
     * @param menuPageDto 菜单分页dto
     * @return 菜单详情分页vo
     */
    IPage<MenuDetailsVo> pageQueryMenuDetails(IPage<MenuInfo> iPage, @Param(value = "query") MenuPageDto menuPageDto);
}
