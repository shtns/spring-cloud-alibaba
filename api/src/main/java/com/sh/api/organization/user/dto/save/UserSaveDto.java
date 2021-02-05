package com.sh.api.organization.user.dto.save;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sh.api.common.constant.ResourceConstants;
import com.sh.api.common.constant.UserInfoConstants;
import com.sh.api.organization.user.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户保存dto
 *
 *
 * @author 盛浩
 * @date 2021/1/16 2:27
 */
@Getter
@Setter
public class UserSaveDto {

    /**
     * 角色id
     */
    @NotNull(message = UserInfoConstants.ForegroundPrompt.ROLE_ID_CANNOT_BE_EMPTY)
    private Long roleId;

    /**
     * 登入账号
     */
    @NotBlank(message = UserInfoConstants.ForegroundPrompt.LOGIN_ACCOUNT_CANNOT_BE_EMPTY)
    private String loginAccount;

    /**
     * 密码
     */
    @NotBlank(message = UserInfoConstants.ForegroundPrompt.PASSWORD_CANNOT_BE_EMPTY)
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
    public UserInfo changeSaveUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setRoleId(this.roleId);
        userInfo.setLoginAccount(this.loginAccount);
        userInfo.setHeadPortrait(ResourceConstants.Url.MINIO_SAVE_UPLOAD_TEST);
        userInfo.setStatus(StringPool.ZERO);
        userInfo.setNameCn(this.nameCn);
        userInfo.setNameEn(this.nameEn);
        userInfo.setMobile(this.mobile);
        userInfo.setAddress(this.address);
        return userInfo;
    }
}
