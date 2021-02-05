package com.sh.api.organization.user.dto.page;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户分页dto
 *
 *
 * @author 盛浩
 * @date 2021/2/3 20:08
 */
@Getter
@Setter
public class UserPageDto {

    /**
     * 登入账号
     */
    private String loginAccount;

    /**
     * 状态（0 正常 1 锁定）
     */
    private String status;

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
}
