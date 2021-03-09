package com.sh.organization.role.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.config.ServerErrorException;
import com.sh.api.common.constant.RoleInfoConstant;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.role.dto.page.RolePageDto;
import com.sh.api.organization.role.dto.save.RoleSaveDto;
import com.sh.api.organization.role.dto.update.RoleUpdateDto;
import com.sh.api.organization.role.entity.RoleInfo;
import com.sh.api.organization.role.vo.details.RoleDetailsVo;
import com.sh.api.organization.role.vo.query.RoleQueryVo;
import com.sh.organization.role.mapper.RoleInfoMapper;
import com.sh.organization.rolemenu.service.RoleMenuInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * 角色信息业务
 *
 *
 * @author 盛浩
 * @date 2021/1/19 18:00
 */
@Service
@RequiredArgsConstructor
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements IService<RoleInfo> {

    private final RoleMenuInfoServiceImpl roleMenuInfoService;

    /**
     * 保存角色信息
     *
     * @param roleSaveDto 角色新保存dto
     * @return 是否保存成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveRoleInfo(RoleSaveDto roleSaveDto) {

        boolean saveFlag = Boolean.FALSE;

        Long roleId = roleSaveDto.changeSaveRoleInfo();
        if (roleId != null && this.roleMenuInfoService.saveRoleMenuInfo(roleId, roleSaveDto.getMenuIds())) {
            saveFlag = Boolean.TRUE;
        }

        return saveFlag;
    }

    /**
     * 删除角色信息
     *
     * @param roleId 角色id
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeRoleInfo(Long roleId) {

        this.checkRoleId(roleId);

        boolean removeFlag = Boolean.FALSE;
        if (this.removeById(roleId) && this.roleMenuInfoService.removeRoleMenuInfo(roleId)) {
            removeFlag = Boolean.TRUE;
        }

        return removeFlag;
    }

    /**
     * 更新角色信息
     *
     * @param roleUpdateDto 角色更新dto
     * @return 是否更新成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateRoleInfo(RoleUpdateDto roleUpdateDto) {

        boolean updateFlag = Boolean.FALSE;

        if (this.updateById(roleUpdateDto.changeUpdateRoleInfo())) {
            if (CollUtil.isNotEmpty(roleUpdateDto.getMenuIds()) &&
                    this.roleMenuInfoService.updateRoleMenuInfo(roleUpdateDto.getRoleId(), roleUpdateDto.getMenuIds())) {
                updateFlag = Boolean.TRUE;
            }
        }

        return updateFlag;
    }

    /**
     * 查询角色信息
     *
     * @param roleId 角色id
     * @return 角色信息vo
     */
    public RoleQueryVo queryRoleInfo(Long roleId) {

        this.checkRoleId(roleId);

        RoleInfo roleInfo = this.getById(roleId);
        if (ObjectUtil.isNull(roleInfo)) {
            return null;
        }

        return new RoleQueryVo(roleInfo);
    }

    /**
     * 分页查询角色信息
     *
     * @param iPage 分页插件
     * @param rolePageDto 角色分页dto
     * @return 角色信息分页vo
     */
    public PageRespVo<RoleQueryVo> pageQueryRoleInfo(IPage<RoleInfo> iPage, RolePageDto rolePageDto) {

        //通过角色名称、角色英文名称、备注进行模糊查询
        IPage<RoleInfo> rolePageInfo = this.page(iPage, Wrappers.<RoleInfo>lambdaQuery()
                .like(StrUtil.isNotBlank(rolePageDto.getRoleName()), RoleInfo::getRoleName, rolePageDto.getRoleName())
                .like(StrUtil.isNotBlank(rolePageDto.getRoleNameEn()), RoleInfo::getRoleNameEn, rolePageDto.getRoleNameEn())
                .like(StrUtil.isNotBlank(rolePageDto.getRemark()), RoleInfo::getRemark, rolePageDto.getRemark()));

        return new PageRespVo<>(rolePageInfo, rolePageInfo.getRecords().stream().map(RoleQueryVo::new).collect(Collectors.toList()));
    }

    /**
     * 分页查询角色详情
     *
     * @param iPage 分页插件
     * @param rolePageDto 角色分页dto
     * @return 角色详情分页vo
     */
    public PageRespVo<RoleDetailsVo> pageQueryRoleDetails(IPage<RoleInfo> iPage, RolePageDto rolePageDto) {
        return new PageRespVo<>(this.baseMapper.pageQueryRoleDetails(iPage, rolePageDto));
    }

    /**
     * 检查角色id是否为空
     *
     * @param roleId 角色id
     */
    public void checkRoleId(Long roleId) {
        if (roleId == null) {
            throw new ServerErrorException(RoleInfoConstant.ForegroundPrompt.ROLE_ID_CANNOT_BE_EMPTY);
        }
    }
}
