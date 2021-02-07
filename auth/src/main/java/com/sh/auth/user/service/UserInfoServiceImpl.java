package com.sh.auth.user.service;

import com.sh.api.organization.user.vo.login.UserLoginVo;
import com.sh.auth.feign.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 用户信息业务
 *
 *
 * @author 盛浩
 * @date 2021/1/17 21:54
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserDetailsService {

    private final OrganizationService organizationService;

    /**
     * 通过登入账号查询用户信息
     *
     * @param loginAccount 登入账号
     * @return 用户详情
     * @throws UsernameNotFoundException 用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String loginAccount) throws UsernameNotFoundException {
        UserLoginVo userLoginVo = this.organizationService.queryUserInfo(loginAccount).getData();
        List<String> resourcePaths = this.organizationService.queryResourcePaths(userLoginVo.getRoleId()).getData();
        String[] resourceArray = new String[resourcePaths.size()];
        return User.withUsername(userLoginVo.getLoginAccount()).password(userLoginVo.getPassword())
                .authorities(resourcePaths.toArray(resourceArray)).build();
    }
}
