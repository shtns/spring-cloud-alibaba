package com.sh.organization.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.organization.role.entity.RoleInfo;
import com.sh.organization.role.mapper.RoleInfoMapper;
import org.springframework.stereotype.Service;

/**
 * 角色信息业务
 *
 *
 * @author 盛浩
 * @date 2021/1/19 18:00
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements IService<RoleInfo> {
}
