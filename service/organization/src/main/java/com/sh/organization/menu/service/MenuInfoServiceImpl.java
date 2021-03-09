package com.sh.organization.menu.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.config.ServerErrorException;
import com.sh.api.common.constant.MenuInfoConstant;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.menu.dto.page.MenuPageDto;
import com.sh.api.organization.menu.entity.MenuInfo;
import com.sh.api.organization.menu.vo.details.MenuDetailsVo;
import com.sh.api.organization.menu.vo.query.MenuQueryVo;
import com.sh.organization.menu.mapper.MenuInfoMapper;
import com.sh.organization.resource.service.ResourceInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单信息业务
 *
 *
 * @author 盛浩
 * @date 2021/1/18 22:03
 */
@Service
@RequiredArgsConstructor
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements IService<MenuInfo> {

    private final ResourceInfoServiceImpl resourceInfoService;

    /**
     * 删除菜单信息
     *
     * @param menuId 菜单id
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeMenuInfo(Long menuId) {

        this.checkMenuId(menuId);

        boolean removeFlag = Boolean.FALSE;
        if (this.removeById(menuId) && this.resourceInfoService.removeResourceInfo(menuId)) {
            removeFlag = Boolean.TRUE;
        }

        return removeFlag;
    }

    /**
     * 查询菜单信息
     *
     * @param menuId 菜单id
     * @return 菜单查询vo
     */
    public MenuQueryVo queryMenuInfo(Long menuId) {

        this.checkMenuId(menuId);

        MenuInfo menuInfo = this.getById(menuId);
        if (ObjectUtil.isNull(menuInfo)) {
            return null;
        }
        return new MenuQueryVo(menuInfo);
    }

    /**
     * 查询全部菜单信息
     *
     * @return 菜单信息列表
     */
    public List<MenuQueryVo> queryMenuInfos() {
        return this.list().stream().map(MenuQueryVo::new).collect(Collectors.toList());
    }

    /**
     * 分页查询菜单信息
     *
     * @param iPage 分页插件
     * @param menuPageDto 菜单分页dto
     * @return 菜单查询分页vo
     */
    public PageRespVo<MenuQueryVo> pageQueryMenuInfo(IPage<MenuInfo> iPage, MenuPageDto menuPageDto) {

        //通过菜单名称、访问路径进行模糊查询
        IPage<MenuInfo> menuPageInfo = this.page(iPage, Wrappers.<MenuInfo>lambdaQuery()
                .like(StrUtil.isNotBlank(menuPageDto.getMenuName()), MenuInfo::getMenuName, menuPageDto.getMenuName())
                .like(StrUtil.isNotBlank(menuPageDto.getMenuName()), MenuInfo::getMenuPath, menuPageDto.getMenuPath()));

        return new PageRespVo<>(menuPageInfo, menuPageInfo.getRecords().stream().map(MenuQueryVo::new).collect(Collectors.toList()));
    }

    /**
     * 分页查询菜单详情
     *
     * @param iPage 分页插件
     * @param menuPageDto 菜单分页dto
     * @return 菜单详情分页vo
     */
    public PageRespVo<MenuDetailsVo> pageQueryMenuDetails(IPage<MenuInfo> iPage, MenuPageDto menuPageDto) {
        return new PageRespVo<>(this.baseMapper.pageQueryMenuDetails(iPage, menuPageDto));
    }

    /**
     * 检查菜单id是否为空
     *
     * @param menuId 菜单id
     */
    public void checkMenuId(Long menuId) {
        if (menuId == null) {
            throw new ServerErrorException(MenuInfoConstant.ForegroundPrompt.MENU_ID_CANNOT_BE_EMPTY);
        }
    }
}
