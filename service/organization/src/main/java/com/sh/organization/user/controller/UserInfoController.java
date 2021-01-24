package com.sh.organization.user.controller;

import com.sh.api.common.constant.MinioConstants;
import com.sh.api.common.util.R;
import com.sh.api.organization.user.dto.save.UserSaveDto;
import com.sh.api.organization.user.dto.update.UserUpdateDto;
import com.sh.api.organization.user.vo.login.UserLoginVo;
import com.sh.organization.config.MinIoUtils;
import com.sh.organization.user.service.UserInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户管理
 *
 *
 * @author 盛浩
 * @date 2021/1/16 1:46
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
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
        return R.ok(this.userInfoService.saveUserInfo(userSaveDto));
    }

    /**
     * 修改用户信息
     *
     * @param userUpdateDto 用户修改dto
     * @return 是否修改成功
     */
    @PutMapping
    public R<Boolean> updateUserInfo(@RequestBody @Valid UserUpdateDto userUpdateDto) {
        return R.ok(this.userInfoService.updateUserInfo(userUpdateDto));
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户id
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/{userId}")
    public R<Boolean> delUserInfo(@PathVariable Long userId) {
        return R.ok(this.userInfoService.delUserInfo(userId));
    }

    /**
     * 通过登入账号查询用户信息
     *
     * @param loginAccount 登入账号
     * @return 用户登入信息
     */
    @GetMapping(value = "/account/{loginAccount}")
    public R<UserLoginVo> queryUserInfo(@PathVariable String loginAccount) {
        return R.ok(this.userInfoService.queryUserInfo(loginAccount));
    }

    /**
     * 用户登入
     *
     * @param loginAccount 登入账号
     * @param password 密码
     * @return 是否登入成功
     */
    @GetMapping(value = "/login/{loginAccount}/{password}")
    public R<Boolean> userLogin(@PathVariable String loginAccount, @PathVariable String password) {
        return R.ok(this.userInfoService.userLogin(loginAccount, password));
    }

    /**
     * 查询用户权限列表
     *
     * @param userId 用户id
     * @return 用户权限列表
     */
    @GetMapping(value = "/permissions/{userId}")
    public R<List<String>> queryUserPermissions(@PathVariable Long userId) {
        return R.ok(this.userInfoService.queryUserPermissions(userId));
    }

    /**
     * sentinel测试
     *
     * @param id id
     * @return 端口+id
     */
    @GetMapping(value = "/sentinel_test/{id}")
    public R<String> sentinelTest(@PathVariable Long id) {
        return R.ok(this.userInfoService.sentinelTest(id));
    }
}
