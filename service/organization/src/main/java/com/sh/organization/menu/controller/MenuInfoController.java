package com.sh.organization.menu.controller;

import com.sh.api.common.dto.PageReqDto;
import com.sh.api.common.util.R;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.menu.dto.page.MenuPageDto;
import com.sh.api.organization.menu.dto.save.MenuSaveDto;
import com.sh.api.organization.menu.dto.update.MenuUpdateDto;
import com.sh.api.organization.menu.entity.MenuInfo;
import com.sh.api.organization.menu.vo.details.MenuDetailsVo;
import com.sh.api.organization.menu.vo.query.MenuQueryVo;
import com.sh.organization.menu.service.MenuInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 菜单信息管理
 *
 *
 * @author 盛浩
 * @date 2021/1/18 7:34
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/menu")
public class MenuInfoController {

    private final MenuInfoServiceImpl menuInfoService;

    /**
     * 新增菜单信息
     *
     * @param menuSaveDto 菜单新增dto
     * @return 是否新增成功
     */
    @PostMapping
    public R<Boolean> saveMenuInfo(@RequestBody @Valid MenuSaveDto menuSaveDto) {
        return R.ok(this.menuInfoService.save(menuSaveDto.changeSaveMenuInfo()));
    }

    /**
     * 删除菜单信息
     *
     * @param menuId 菜单id
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/{menuId}")
    public R<Boolean> removeMenuInfo(@PathVariable Long menuId) {
        return R.ok(this.menuInfoService.removeMenuInfo(menuId));
    }

    /**
     * 更新菜单信息
     *
     * @param menuUpdateDto 菜单更新dto
     * @return 是否更新成功
     */
    @PutMapping
    public R<Boolean> updateMenuInfo(@RequestBody @Valid MenuUpdateDto menuUpdateDto) {
        return R.ok(this.menuInfoService.updateById(menuUpdateDto.changeUpdateMenuInfo()));
    }

    /**
     * 查询菜单信息
     *
     * @param menuId 菜单id
     * @return 菜单查询vo
     */
    @GetMapping(value = "/{menuId}")
    public R<MenuQueryVo> queryMenuInfo(@PathVariable Long menuId) {
        return R.ok(this.menuInfoService.queryMenuInfo(menuId));
    }

    /**
     * 分页查询菜单信息
     *
     * @param pageReqDto 分页插件
     * @param menuPageDto 菜单分页dto
     * @return 菜单查询分页vo
     */
    @GetMapping(value = "/page")
    public R<PageRespVo<MenuQueryVo>> pageQueryMenuInfo(PageReqDto<MenuInfo> pageReqDto, MenuPageDto menuPageDto) {
        return R.ok(this.menuInfoService.pageQueryMenuInfo(pageReqDto.toPlusPage(), menuPageDto));
    }

    /**
     * 分页查询菜单详情
     *
     * @param pageReqDto 分页插件
     * @param menuPageDto 菜单分页dto
     * @return 菜单详情分页vo
     */
    @GetMapping(value = "/details")
    public R<PageRespVo<MenuDetailsVo>> pageQueryMenuDetails(PageReqDto<MenuInfo> pageReqDto, MenuPageDto menuPageDto) {
        return R.ok(this.menuInfoService.pageQueryMenuDetails(pageReqDto.toPlusPage(), menuPageDto));
    }
}
