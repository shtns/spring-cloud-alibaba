package com.sh.organization.user.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.common.constant.MinioConstants;
import com.sh.api.common.constant.ResourceConstants;
import com.sh.api.common.constant.UserInfoConstants;
import com.sh.api.organization.user.dto.save.UserSaveDto;
import com.sh.api.organization.user.dto.update.UserUpdateDto;
import com.sh.api.organization.user.entity.UserInfo;
import com.sh.api.organization.user.vo.login.UserLoginVo;
import com.sh.organization.config.MinIoUtils;
import com.sh.organization.user.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import java.util.List;

/**
 * 用户信息业务
 *
 *
 * @author 盛浩
 * @date 2021/1/16 1:48
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IService<UserInfo> {

    private final PasswordEncoder encrypt = new BCryptPasswordEncoder();

    @Value(value = "${server.port}")
    private String serverPort;

    /**
     * 保存用户信息
     *
     * @param userSaveDto 用户保存dto
     * @return 是否保存成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUserInfo(UserSaveDto userSaveDto) {

        this.checkLoginAccount(userSaveDto.getLoginAccount());

        UserInfo userInfo = userSaveDto.changeSaveUserInfo();
        userInfo.setPassword(this.encrypt.encode(userSaveDto.getPassword()));

        String filePath = ResourceConstants.Url.MINIO_ADD_UPLOAD_TEST;
        String fileName = this.getFileName(userSaveDto.getLoginAccount(), filePath);
        MinIoUtils.fileUpload(MinioConstants.BucketName.HEAD_PORTRAIT, fileName, filePath);
        userInfo.setHeadPortrait(fileName);
        return this.save(userInfo);
    }

    /**
     * 修改用户信息
     *
     * @param userUpdateDto 用户修改dto
     * @return 是否修改成功
     */
    public Boolean updateUserInfo(UserUpdateDto userUpdateDto) {

        UserInfo userInfo = userUpdateDto.changeUpdateUserInfo();

        UserInfo user = this.getById(userUpdateDto.getUserId());

        if (StrUtil.isNotBlank(userUpdateDto.getLoginAccount()) && ! StrUtil.equals(user.getLoginAccount(), userUpdateDto.getLoginAccount())) {
            this.checkLoginAccount(userUpdateDto.getLoginAccount());
            userInfo.setLoginAccount(userUpdateDto.getLoginAccount());
        }

        if (StrUtil.isNotBlank(userUpdateDto.getPassword()) && ! this.encrypt.matches(user.getPassword(), userUpdateDto.getPassword())) {
            userInfo.setPassword(this.encrypt.encode(userUpdateDto.getPassword()));
        }

        if (StrUtil.isNotBlank(userUpdateDto.getHeadPortrait()) && ! StrUtil.equals(user.getHeadPortrait(), userUpdateDto.getHeadPortrait())) {
            MinIoUtils.delFile(MinioConstants.BucketName.HEAD_PORTRAIT, user.getHeadPortrait());
            String filePath = ResourceConstants.Url.MINIO_UPDATE_UPLOAD_TEST;
            String fileName = this.getFileName(userUpdateDto.getLoginAccount(), filePath);
            MinIoUtils.fileUpload(MinioConstants.BucketName.HEAD_PORTRAIT, fileName, filePath);
            userInfo.setHeadPortrait(fileName);
        }

        return this.updateById(userInfo);
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户id
     * @return 是否删除成功
     */
    public Boolean delUserInfo(Long userId) {

        this.checkUserId(userId);

        UserInfo userInfo = this.getById(userId);
        if (StrUtil.isNotBlank(userInfo.getHeadPortrait())) {
            MinIoUtils.delFile(MinioConstants.BucketName.HEAD_PORTRAIT, userInfo.getHeadPortrait());
        }

        return this.removeById(userId);
    }

    /**
     * 通过登入账号查询用户信息
     *
     * @param loginAccount 登入账号
     * @return 用户登入信息
     */
    public UserLoginVo queryUserInfo(String loginAccount) {

        if (StrUtil.isBlank(loginAccount)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.LOGIN_ACCOUNT_CANNOT_BE_EMPTY);
        }

        UserInfo userInfo = this.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginAccount, loginAccount));
        if (ObjectUtil.isNull(userInfo)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.USER_INFORMATION_NOT_FOUND);
        }

        if (StrUtil.isNotBlank(userInfo.getHeadPortrait())) {
            userInfo.setHeadPortrait(MinIoUtils.getFileAccessPath(MinioConstants.BucketName.HEAD_PORTRAIT, userInfo.getHeadPortrait()));
        }

        return new UserLoginVo(userInfo);
    }

    /**
     * 用户登入
     *
     * @param loginAccount 登入账号
     * @param password 密码
     * @return 是否登入成功
     */
    public Boolean userLogin(String loginAccount, String password) {

        if (StrUtil.isBlank(loginAccount)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.LOGIN_ACCOUNT_CANNOT_BE_EMPTY);
        }

        if (StrUtil.isBlank(password)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.PASSWORD_CANNOT_BE_EMPTY);
        }

        boolean flag = Boolean.FALSE;

        UserInfo userInfo = this.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginAccount, loginAccount));
        if (ObjectUtil.isNotNull(userInfo) && this.encrypt.matches(password, userInfo.getPassword())) {
            flag = Boolean.TRUE;
        }

        return flag;
    }

    /**
     * 查询用户权限列表
     *
     * @param userId 用户id
     * @return 用户权限列表
     */
    public List<String> queryUserPermissions(Long userId) {
        this.checkUserId(userId);
        return this.baseMapper.queryUserPermissions(userId);
    }

    /**
     * sentinel测试
     *
     * @param id id
     * @return 端口+id
     */
    public String sentinelTest(Long id) {
        return StrUtil.concat(Boolean.TRUE, serverPort, StringPool.COMMA, String.valueOf(id));
    }

    /**
     * 检查用户id是否为空
     *
     * @param userId 用户id
     */
    private void checkUserId(Long userId) {
        if (userId == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.USER_ID_CANNOT_BE_EMPTY);
        }
    }

    /**
     * 拿到文件名
     *
     * @param filePath 文件路径
     * @return 登入账号+文件名=确保文件名不重复
     */
    private String getFileName(String loginAccount, String filePath) {
        return StrUtil.concat(Boolean.TRUE, loginAccount, StrUtil.UNDERLINE,
                StrUtil.sub(filePath, filePath.lastIndexOf(StringPool.BACK_SLASH), filePath.length()).substring(DigitalConstants.ONE));
    }

    /**
     * 检查登入账号书否存在
     *
     * @param loginAccount 登入账号
     */
    private void checkLoginAccount(String loginAccount) {
        if (this.count(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginAccount, loginAccount))
                > DigitalConstants.ZERO) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.LOGIN_ACCOUNT_ALREADY_EXISTS);
        }
    }
}
