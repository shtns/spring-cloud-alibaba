package com.sh.api.organization.role.vo.details;

import com.sh.api.organization.menu.vo.query.MenuQueryVo;
import com.sh.api.organization.role.vo.query.RoleQueryVo;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * 角色详情vo
 *
 *
 * @author 盛浩
 * @date 2021/2/3 18:19
 */
@Getter
@Setter
public class RoleDetailsVo {

    /**
     * 角色查询vo列表
     */
    private List<RoleQueryVo> roleQueryVos;

    /**
     * 菜单查询vo列表
     */
    private List<MenuQueryVo> menuQueryVos;
}
