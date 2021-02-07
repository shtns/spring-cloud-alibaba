package com.sh.api.organization.user.dto.update;

import com.sh.api.common.constant.ResourceConstants;
import com.sh.api.common.constant.UserInfoConstants;
import com.sh.api.organization.user.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * 用户更新dto
 *
 *
 * @author 盛浩
 * @date 2021/1/24 21:56
 */
@Getter
@Setter
public class UserUpdateDto {

    /**
     * 用户id
     */
    @NotNull(message = UserInfoConstants.ForegroundPrompt.USER_ID_CANNOT_BE_EMPTY)
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
     * dto转entity
     *
     * @return 用户信息
     */
    public UserInfo changeUpdateUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(this.userId);
        userInfo.setRoleId(this.roleId);
        userInfo.setHeadPortrait(ResourceConstants.Url.MINIO_UPDATE_UPLOAD_TEST);
        userInfo.setNameCn(this.nameCn);
        userInfo.setNameEn(this.nameEn);
        userInfo.setMobile(this.mobile);
        userInfo.setAddress(this.address);
        return userInfo;
    }
}
