package com.sh.organization.user.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.common.constant.UserInfoErrorConstants;
import com.sh.api.organization.user.dto.save.UserSaveDto;
import com.sh.api.organization.user.entity.UserInfo;
import com.sh.api.organization.user.vo.login.UserLoginVo;
import com.sh.organization.user.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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

    private final PasswordEncoder ENCODE = new BCryptPasswordEncoder();

    @Value(value = "${server.port}")
    private String serverPort;

    /**
     * 查询用户信息
     *
     * @param loginAccount 登入账号
     * @return 用户登入信息
     */
    public UserLoginVo queryUserInfo(String loginAccount) {

        if (StrUtil.isBlank(loginAccount)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoErrorConstants.ForegroundPrompt.LOGIN_ACCOUNT_CANNOT_BE_EMPTY);
        }

        UserInfo userInfo = this.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginAccount, loginAccount));
        if (ObjectUtil.isNull(userInfo)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoErrorConstants.ForegroundPrompt.USER_INFORMATION_NOT_FOUND);
        }

        return new UserLoginVo(userInfo);
    }

    /**
     * 保存用户信息
     *
     * @param userSaveDto 用户保存dto
     * @return 是否保存成功
     */
    public Boolean saveUserInfo(UserSaveDto userSaveDto) {

        if (this.count(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginAccount, userSaveDto.getLoginAccount()))
                > DigitalConstants.ZERO) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoErrorConstants.ForegroundPrompt.LOGIN_ACCOUNT_ALREADY_EXISTS);
        }

        UserInfo userInfo = userSaveDto.changeSaveUserInfo();
        userInfo.setPassword(ENCODE.encode(userSaveDto.getPassword()));
        return this.save(userInfo);
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
                    UserInfoErrorConstants.ForegroundPrompt.LOGIN_ACCOUNT_CANNOT_BE_EMPTY);
        }

        if (StrUtil.isBlank(password)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoErrorConstants.ForegroundPrompt.PASSWORD_CANNOT_BE_EMPTY);
        }

        boolean flag = Boolean.FALSE;

        UserInfo userInfo = this.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginAccount, loginAccount));
        if (ObjectUtil.isNotNull(userInfo) && this.ENCODE.matches(password, userInfo.getPassword())) {
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
        if (userId == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoErrorConstants.ForegroundPrompt.USER_ID_CANNOT_BE_EMPTY);
        }
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
}
