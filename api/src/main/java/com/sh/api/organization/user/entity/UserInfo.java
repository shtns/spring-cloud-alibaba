package com.sh.api.organization.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 用户信息
 *
 *
 * @author 盛浩
 * @date 2021/1/5 16:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends Model<UserInfo> {

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 登入账号
     */
    private String loginAccount;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态（0 正常 1 锁定）
     */
    private String status;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 名称中文
     */
    private String nameCn;

    /**
     * 名称英文
     */
    private String nameEn;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 住址
     */
    private String address;

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
