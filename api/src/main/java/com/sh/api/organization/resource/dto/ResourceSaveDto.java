package com.sh.api.organization.resource.dto;

import com.sh.api.organization.resource.entity.ResourceInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 资源新增dto
 *
 * @author 盛浩
 * @date 2021/2/5 21:24
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceSaveDto {

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

    /**
     * dto转entity
     *
     * @return 资源信息
     */
    public ResourceInfo changeSaveResourceInfo() {
        ResourceInfo resourceInfo = new ResourceInfo();
        resourceInfo.setMenuId(this.menuId);
        resourceInfo.setResourcePath(this.resourcePath);
        resourceInfo.setRequestType(this.requestType);
        return resourceInfo;
    }
}
