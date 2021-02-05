package com.sh.api.organization.menu.vo.details;

import com.sh.api.organization.menu.vo.query.MenuQueryVo;
import com.sh.api.organization.resource.vo.query.ResourceQueryVo;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * 菜单详情vo
 *
 *
 * @author 盛浩
 * @date 2021/2/3 19:59
 */
@Getter
@Setter
public class MenuDetailsVo {

    /**
     * 菜单查询vo列表
     */
    private List<MenuQueryVo> menuQueryVos;

    /**
     * 资源查询vo列表
     */
    private List<ResourceQueryVo> resourceQueryVos;
}
