package com.sh.api.organization.resource.vo.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 资源查询vo
 *
 *
 * @author 盛浩
 * @date 2021/2/3 18:31
 */
@Getter
@Setter
public class ResourceQueryVo {

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 资源地址
     */
    private String resourcePath;

    /**
     * 请求类型
     */
    private String requestType;
}
