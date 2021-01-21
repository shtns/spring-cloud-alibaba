package com.sh.organization.menu.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.organization.menu.entity.MenuInfo;
import com.sh.organization.menu.mapper.MenuInfoMapper;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 菜单信息业务
 *
 *
 * @author 盛浩
 * @date 2021/1/18 22:03
 */
@Service
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements IService<MenuInfo> {

    public List<String> queryAccessPaths() {
        List<String> permissions = CollUtil.newArrayList();
        List<MenuInfo> menuInfos = this.list(new LambdaQueryWrapper<MenuInfo>().select(MenuInfo::getAccessPath));
        menuInfos.forEach(menuInfo -> permissions.add(menuInfo.getAccessPath()));
        return permissions;
    }
}
