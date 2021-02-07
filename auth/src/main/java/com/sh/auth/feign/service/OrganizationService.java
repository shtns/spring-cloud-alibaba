package com.sh.auth.feign.service;

import com.sh.api.common.constant.MicroServiceNameConstants;
import com.sh.api.common.util.R;
import com.sh.api.organization.user.vo.login.UserLoginVo;
import com.sh.auth.feign.service.impl.OrganizationFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * 组织服务
 *
 *
 * @author 盛浩
 * @date 2021/1/16 2:14
 */
@FeignClient(value = MicroServiceNameConstants.ORGANIZATION_SERVICE, fallback = OrganizationFallbackImpl.class)
public interface OrganizationService {

    /**
     * 用户登入
     *
     * @param loginAccount 登入账号
     * @param password 密码
     * @return 是否登入成功
     */
    @GetMapping(value = "/user/login")
    R<Boolean> userLogin(@RequestParam(value = "loginAccount") String loginAccount, @RequestParam(value = "password") String password);

    /**
     * 查询用户信息
     *
     * @param loginAccount 登入账号
     * @return 用户登入信息
     */
    @GetMapping(value = "/user/account")
    R<UserLoginVo> queryUserInfo(@RequestParam(value = "loginAccount") String loginAccount);

    /**
     * 查询资源地址列表
     *
     * @param roleId 角色id
     * @return 资源地址
     */
    @GetMapping(value = "/resource/all")
    R<List<String>> queryResourcePaths(@RequestParam(value = "roleId") Long roleId);

    /**
     * sentinel测试
     *
     * @param id id
     * @return 端口+id
     */
    @GetMapping(value = "/user/sentinel_test")
    R<String> sentinelTest(@RequestParam(value = "id") Long id);
}
