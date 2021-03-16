package com.sh.organization.rolemenu.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.organization.rolemenu.entity.RoleMenuInfo;
import com.sh.organization.rolemenu.mapper.RoleMenuInfoMapper;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 角色菜单关系信息业务
 *
 *
 * @author 盛浩
 * @date 2021/1/19 18:02
 */
@Service
public class RoleMenuInfoServiceImpl extends ServiceImpl<RoleMenuInfoMapper, RoleMenuInfo> implements IService<RoleMenuInfo> {

    /**
     * 保存角色菜单关系
     *
     * @param roleId 角色id
     * @param menuIds 菜单id列表
     * @return 是否新增成功
     */
    public Boolean saveRoleMenuInfo(Long roleId, List<Long> menuIds) {
        boolean saveFlag = Boolean.TRUE;
        for (Long menuId : menuIds) {
            if (! this.save(new RoleMenuInfo(roleId, menuId))) {
                saveFlag = Boolean.FALSE;
                break;
            }
        }
        return saveFlag;
    }

    /**
     * 删除角色菜单关系
     *
     * @param roleId 角色id
     * @return 是否删除成功
     */
    public boolean removeRoleMenuInfo(Long roleId) {
        return this.remove(Wrappers.<RoleMenuInfo>lambdaQuery().eq(RoleMenuInfo::getRoleId, roleId));
    }

    /**
     * 更新角色菜单关系
     *
     * @param roleId 角色id
     * @param menuIds 菜单id列表
     * @return 是否更新成功
     */
    public boolean updateRoleMenuInfo(Long roleId, List<Long> menuIds) {
        boolean updateFlag = Boolean.TRUE;
        if (this.remove(Wrappers.<RoleMenuInfo>lambdaQuery().eq(RoleMenuInfo::getRoleId, roleId))) {
            for (Long menuId : menuIds) {
                if (! save(new RoleMenuInfo(roleId, menuId))) {
                    updateFlag = Boolean.FALSE;
                    break;
                }
            }
        }
        return updateFlag;
    }
}
