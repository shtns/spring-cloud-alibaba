package com.sh.organization.user.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.common.constant.MinioConstants;
import com.sh.api.common.constant.UserInfoConstants;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.user.dto.page.UserPageDto;
import com.sh.api.organization.user.dto.save.UserSaveDto;
import com.sh.api.organization.user.dto.update.UserUpdateDto;
import com.sh.api.organization.user.entity.UserInfo;
import com.sh.api.organization.user.vo.login.UserLoginVo;
import com.sh.api.organization.user.vo.page.UserPageVo;
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
import java.util.stream.Collectors;

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

        //检查登入账号是否存在
        this.checkUserExist(userSaveDto.getLoginAccount());

        //用户密码加密
        UserInfo userInfo = userSaveDto.changeSaveUserInfo();
        userInfo.setPassword(this.encrypt.encode(userSaveDto.getPassword()));

        //生成唯一头像名
        String fileName = this.getFileName(userSaveDto.getLoginAccount(), userInfo.getHeadPortrait());
        //上传用户头像
        MinIoUtils.fileUpload(MinioConstants.BucketName.HEAD_PORTRAIT, fileName, userInfo.getHeadPortrait());
        //把唯一头像名保存到头像字段中
        userInfo.setHeadPortrait(fileName);
        return this.save(userInfo);
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户id
     * @return 是否删除成功
     */
    public Boolean removeUserInfo(Long userId) {

        //检查用户id是否为空
        this.checkUserId(userId);

        //用户头像存在，删除此用户的头像文件
        UserInfo userInfo = this.getById(userId);
        if (ObjectUtil.isNotNull(userInfo)) {
            if (StrUtil.isNotBlank(userInfo.getHeadPortrait())) {
                MinIoUtils.delFile(MinioConstants.BucketName.HEAD_PORTRAIT, userInfo.getHeadPortrait());
            }
        }

        return this.removeById(userId);
    }

    /**
     * 更新用户信息
     *
     * @param userUpdateDto 用户更新dto
     * @return 是否修改成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUserInfo(UserUpdateDto userUpdateDto) {

        //拿到原始和现在更新的用户信息
        UserInfo userInfo = this.getById(userUpdateDto.getUserId());
        UserInfo userUpdateInfo = userUpdateDto.changeUpdateUserInfo();

        //更新账号和原始账号是否不一致
        String updateAccount = userUpdateDto.getLoginAccount();
        if (StrUtil.isNotBlank(updateAccount)
                && ! StrUtil.equals(userInfo.getLoginAccount(), updateAccount)) {
            //条件成立后，检查登入账号是否存在，存在报错，不存在执行更新
            this.checkUserExist(userUpdateDto.getLoginAccount());
            //更新账号
            userUpdateInfo.setLoginAccount(updateAccount);
        }

        //更新密码和原始密码是否不一致
        String updatePassword = userUpdateDto.getPassword();
        if (StrUtil.isNotBlank(updatePassword) && ! this.encrypt.matches(updatePassword, userInfo.getPassword())) {
            //更新密码
            userUpdateInfo.setPassword(this.encrypt.encode(updatePassword));
        }

        //原始和更新头像是否不相等
        String headPortrait = userInfo.getHeadPortrait();
        String updateHeadPortrait = userUpdateInfo.getHeadPortrait();
        if (StrUtil.isNotBlank(headPortrait)
                && StrUtil.isNotBlank(updateHeadPortrait)
                && ! StrUtil.equals(headPortrait, updateHeadPortrait)) {
            //通过头像文件名删除原始文件
            MinIoUtils.delFile(MinioConstants.BucketName.HEAD_PORTRAIT, headPortrait);
            //生成唯一头像名
            String fileName = this.getFileName(userUpdateDto.getLoginAccount(), updateHeadPortrait);
            //上传最新头像图片
            MinIoUtils.fileUpload(MinioConstants.BucketName.HEAD_PORTRAIT, fileName, updateHeadPortrait);
            //把唯一头像名更新到头像字段中
            userUpdateInfo.setHeadPortrait(fileName);
        }

        return this.updateById(userUpdateInfo);
    }

    /**
     * 通过登入账号查询用户信息
     *
     * @param loginAccount 登入账号
     * @return 用户登入信息
     */
    public UserLoginVo queryUserInfo(String loginAccount) {

        //检查登入账号、用户信息是否为空
        if (StrUtil.isBlank(loginAccount)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.LOGIN_ACCOUNT_CANNOT_BE_EMPTY);
        }
        UserInfo userInfo = this.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginAccount, loginAccount));
        if (ObjectUtil.isNull(userInfo)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.USER_INFORMATION_NOT_FOUND);
        }

        //用户头像存在，通过头像文件名获取文件访问外链
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

        //检查登入账号、密码是否为空
        if (StrUtil.isBlank(loginAccount)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.LOGIN_ACCOUNT_CANNOT_BE_EMPTY);
        }
        if (StrUtil.isBlank(password)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.PASSWORD_CANNOT_BE_EMPTY);
        }

        //登入结果
        boolean loginFlag = Boolean.FALSE;

        //拿到用户信息，密码解密是否一致
        UserInfo userInfo = this.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginAccount, loginAccount));
        if (ObjectUtil.isNotNull(userInfo) && this.encrypt.matches(password, userInfo.getPassword())) {
            loginFlag = Boolean.TRUE;
        }

        return loginFlag;
    }

    /**
     * 用户分页查询
     *
     * @param iPage 分页插件
     * @param userPageDto 用户分页dto
     * @return 用户分页vo
     */
    public PageRespVo<UserPageVo> pageQueryUserInfo(IPage<UserInfo> iPage, UserPageDto userPageDto) {

        //通过登入账号、状态、名称中文、名称英文、电话、住址进行模糊查询
        IPage<UserInfo> userPageInfo = this.page(iPage, Wrappers.<UserInfo>lambdaQuery()
                .like(StrUtil.isNotBlank(userPageDto.getLoginAccount()), UserInfo::getLoginAccount, userPageDto.getLoginAccount())
                .like(StrUtil.isNotBlank(userPageDto.getStatus()), UserInfo::getStatus, userPageDto.getStatus())
                .like(StrUtil.isNotBlank(userPageDto.getNameCn()), UserInfo::getNameCn, userPageDto.getNameCn())
                .like(StrUtil.isNotBlank(userPageDto.getNameEn()), UserInfo::getNameEn, userPageDto.getNameEn())
                .like(StrUtil.isNotBlank(userPageDto.getMobile()), UserInfo::getMobile, userPageDto.getMobile())
                .like(StrUtil.isNotBlank(userPageDto.getAddress()), UserInfo::getAddress, userPageDto.getAddress()));

        //把结果转为分页用户vo，用户中存在头像的获取头像外链
        List<UserPageVo> userPageVos = userPageInfo.getRecords().stream().map(UserPageVo::new).collect(Collectors.toList());
        userPageVos.forEach(userPageVo -> {
            if (StrUtil.isNotBlank(userPageVo.getHeadPortrait())) {
                userPageVo.setHeadPortrait(MinIoUtils.getFileAccessPath(MinioConstants.BucketName.HEAD_PORTRAIT, userPageVo.getHeadPortrait()));
            }
        });

        return new PageRespVo<>(userPageInfo, userPageVos);
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
     * 检查登入账号是否存在
     *
     * @param loginAccount 登入账号
     */
    public void checkUserExist(String loginAccount) {
        if (this.count(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getLoginAccount, loginAccount))
                > DigitalConstants.ZERO) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    UserInfoConstants.ForegroundPrompt.LOGIN_ACCOUNT_ALREADY_EXISTS);
        }
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
     * @param loginAccount 登入账号
     * @param filePath 文件路径
     * @return 登入账号+唯一id+文件名=唯一文件名标识
     */
    private String getFileName(String loginAccount, String filePath) {
        return StrUtil.concat(Boolean.TRUE, loginAccount, StringPool.COLON, IdUtil.randomUUID(), StrUtil.DASHED,
                StrUtil.sub(filePath, filePath.lastIndexOf(StringPool.BACK_SLASH), filePath.length()).substring(DigitalConstants.ONE));
    }
}
