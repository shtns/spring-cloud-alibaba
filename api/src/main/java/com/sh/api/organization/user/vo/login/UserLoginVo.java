package com.sh.api.organization.user.vo.login;

import com.sh.api.organization.user.entity.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户登入vo
 *
 *
 * @author 盛浩
 * @date 2021/1/16 2:04
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserLoginVo", description = "用户登入vo")
public class UserLoginVo {

    /**
     * 用户id
     */
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
     * 名称英文
     */
    private String nameEn;

    /**
     * 名称中文
     */
    private String nameCn;

    /**
     * 状态（0 正常 1 锁定）
     */
    private String status;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 住址
     */
    private String address;

    /**
     * entity转vo
     *
     * @param userInfo 用户信息
     */
    public UserLoginVo(UserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.roleId = userInfo.getRoleId();
        this.loginAccount = userInfo.getLoginAccount();
        this.password = userInfo.getPassword();
        this.status = userInfo.getStatus();
        this.nameEn = userInfo.getNameEn();
        this.nameCn = userInfo.getNameCn();
        this.password = userInfo.getPassword();
        this.mobile = userInfo.getMobile();
        this.address = userInfo.getAddress();
    }
}
