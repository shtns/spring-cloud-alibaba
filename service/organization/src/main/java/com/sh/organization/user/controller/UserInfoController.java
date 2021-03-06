package com.sh.organization.user.controller;

import com.sh.api.common.dto.PageReqDto;
import com.sh.api.common.util.R;
import com.sh.api.common.vo.PageRespVo;
import com.sh.api.organization.user.dto.page.UserPageDto;
import com.sh.api.organization.user.dto.save.UserSaveDto;
import com.sh.api.organization.user.dto.update.UserUpdateDto;
import com.sh.api.organization.user.entity.UserInfo;
import com.sh.api.organization.user.vo.login.UserLoginVo;
import com.sh.api.organization.user.vo.page.UserPageVo;
import com.sh.organization.user.service.UserInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 用户管理
 *
 *
 * @author 盛浩
 * @date 2021/1/16 1:46
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserInfoController {

    private final UserInfoServiceImpl userInfoService;

    /**
     * 保存用户信息
     *
     * @param userSaveDto 用户保存dto
     * @return 是否保存成功
     */
    @PostMapping
    public R<Boolean> saveUserInfo(@RequestBody @Valid UserSaveDto userSaveDto) {
        return R.ok(userInfoService.saveUserInfo(userSaveDto));
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户id
     * @return 是否删除成功
     */
    @DeleteMapping
    public R<Boolean> removeUserInfo(Long userId) {
        return R.ok(userInfoService.removeUserInfo(userId));
    }

    /**
     * 更新用户信息
     *
     * @param userUpdateDto 用户更新dto
     * @return 是否修改成功
     */
    @PutMapping
    public R<Boolean> updateUserInfo(@RequestBody @Valid UserUpdateDto userUpdateDto) {
        return R.ok(userInfoService.updateUserInfo(userUpdateDto));
    }

    /**
     * 通过登入账号查询用户信息
     *
     * @param loginAccount 登入账号
     * @return 用户登入信息
     */
    @GetMapping(value = "/account")
    public R<UserLoginVo> queryUserInfo(String loginAccount) {
        return R.ok(userInfoService.queryUserInfo(loginAccount));
    }

    /**
     * 用户登入
     *
     * @param loginAccount 登入账号
     * @param password 密码
     * @return 是否登入成功
     */
    @GetMapping(value = "/login")
    public R<Boolean> userLogin(String loginAccount, String password) {
        return R.ok(userInfoService.userLogin(loginAccount, password));
    }

    /**
     * 用户分页查询
     *
     * @param pageReqDto 分页插件
     * @param userPageDto 用户分页dto
     * @return 用户分页vo
     */
    @GetMapping(value = "/page")
    public R<PageRespVo<UserPageVo>> pageQueryUserInfo(PageReqDto<UserInfo> pageReqDto, UserPageDto userPageDto) {
        return R.ok(userInfoService.pageQueryUserInfo(pageReqDto.toPlusPage(), userPageDto));
    }

    /**
     * sentinel测试
     *
     * @param id id
     * @return 端口+id
     */
    @GetMapping(value = "/sentinel_test")
    public R<String> sentinelTest(Long id) {
        return R.ok(userInfoService.sentinelTest(id));
    }
}
