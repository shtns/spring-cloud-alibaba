package com.sh.organization.role.controller;

import com.sh.api.common.dto.PageReqDto;
import com.sh.api.common.util.R;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.role.dto.page.RolePageDto;
import com.sh.api.organization.role.dto.save.RoleSaveDto;
import com.sh.api.organization.role.dto.update.RoleUpdateDto;
import com.sh.api.organization.role.entity.RoleInfo;
import com.sh.api.organization.role.vo.details.RoleDetailsVo;
import com.sh.api.organization.role.vo.query.RoleQueryVo;
import com.sh.organization.role.service.RoleInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 角色信息管理
 *
 *
 * @author 盛浩
 * @date 2021/1/19 17:59
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/role")
public class RoleInfoController {

    private final RoleInfoServiceImpl roleInfoService;

    /**
     * 保存角色信息
     *
     * @param roleSaveDto 角色保存dto
     * @return 是否新增成功
     */
    @PostMapping
    public R<Boolean> saveRoleInfo(@RequestBody @Valid RoleSaveDto roleSaveDto) {
        return R.ok(this.roleInfoService.saveRoleInfo(roleSaveDto));
    }

    /**
     * 删除角色信息
     *
     * @param roleId 角色id
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/{roleId}")
    public R<Boolean> removeRoleInfo(@PathVariable Long roleId) {
        return R.ok(this.roleInfoService.removeRoleInfo(roleId));
    }

    /**
     * 更新角色信息
     *
     * @param roleUpdateDto 角色更新dto
     * @return 是否更新成功
     */
    @PutMapping
    public R<Boolean> updateRoleInfo(@RequestBody @Valid RoleUpdateDto roleUpdateDto) {
        return R.ok(this.roleInfoService.updateRoleInfo(roleUpdateDto));
    }

    /**
     * 查询角色信息
     *
     * @param roleId 角色id
     * @return 角色信息vo
     */
    @GetMapping(value = "/{roleId}")
    public R<RoleQueryVo> queryRoleInfo(@PathVariable Long roleId) {
        return R.ok(this.roleInfoService.queryRoleInfo(roleId));
    }

    /**
     * 分页查询角色信息
     *
     * @param pageReqDto 分页插件
     * @param rolePageDto 角色分页dto
     * @return 角色信息分页vo
     */
    @GetMapping(value = "/page")
    public R<PageRespVo<RoleQueryVo>> pageQueryRoleInfo(PageReqDto<RoleInfo> pageReqDto, RolePageDto rolePageDto) {
        return R.ok(this.roleInfoService.pageQueryRoleInfo(pageReqDto.toPlusPage(), rolePageDto));
    }

    /**
     * 分页查询角色详情
     *
     * @param pageReqDto 分页插件
     * @param rolePageDto 角色分页dto
     * @return 角色详情分页vo
     */
    @GetMapping(value = "/details")
    public R<PageRespVo<RoleDetailsVo>> pageQueryRoleDetails(PageReqDto<RoleInfo> pageReqDto, RolePageDto rolePageDto) {
        return R.ok(this.roleInfoService.pageQueryRoleDetails(pageReqDto.toPlusPage(), rolePageDto));
    }
}
