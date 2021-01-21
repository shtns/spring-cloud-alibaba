package com.sh.organization.rolemenu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.organization.rolemenu.RoleMenuInfo;
import com.sh.organization.rolemenu.mapper.RoleMenuInfoMapper;
import org.springframework.stereotype.Service;

/**
 * 角色菜单关系信息业务
 *
 *
 * @author 盛浩
 * @date 2021/1/19 18:02
 */
@Service
public class RoleMenuInfoServiceImpl extends ServiceImpl<RoleMenuInfoMapper, RoleMenuInfo> implements IService<RoleMenuInfo> {
}
