package com.sh.api.organization.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 资源信息
 *
 *
 * @author 盛浩
 * @date 2021/2/3 16:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResourceInfo extends Model<ResourceInfo> {

    /**
     * 资源id
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 删除标志（0未删 1已删）
     */
    @TableLogic
    private String delFlag;

    /**
     * 创建时间（由数据库控制）
     */
    private LocalDateTime createTime;

    /**
     * 最后修改时间（由数据库控制）
     */
    private LocalDateTime lastModifyTime;
}
