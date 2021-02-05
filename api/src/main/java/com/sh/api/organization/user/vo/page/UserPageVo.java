package com.sh.api.organization.user.vo.page;

import com.sh.api.organization.user.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户分页vo
 *
 *
 * @author 盛浩
 * @date 2021/2/3 20:10
 */
@Getter
@Setter
public class UserPageVo {

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
     * entity转vo
     *
     * @param userInfo 用户信息
     */
    public UserPageVo(UserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.roleId = userInfo.getRoleId();
        this.loginAccount = userInfo.getLoginAccount();
        this.password = userInfo.getPassword();
        this.status = userInfo.getStatus();
        this.headPortrait = userInfo.getHeadPortrait();
        this.nameEn = userInfo.getNameEn();
        this.nameCn = userInfo.getNameCn();
        this.mobile = userInfo.getMobile();
        this.address = userInfo.getAddress();
    }
}
