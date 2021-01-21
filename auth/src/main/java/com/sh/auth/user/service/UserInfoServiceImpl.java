package com.sh.auth.user.service;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.common.constant.RedisConstants;
import com.sh.api.common.constant.SentinelConstants;
import com.sh.api.common.util.R;
import com.sh.api.organization.user.vo.login.UserLoginVo;
import com.sh.auth.feign.OrganizationService;
import com.sh.auth.user.handler.CustomBlockHandler;
import com.sh.auth.user.handler.CustomFallbackHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * 用户信息业务
 *
 *
 * @author 盛浩
 * @date 2021/1/17 21:54
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class    UserInfoServiceImpl implements UserDetailsService {

    private final OrganizationService organizationService;

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过登入账号查询用户信息
     *
     * @param loginAccount 登入账号
     * @return 用户详情
     * @throws UsernameNotFoundException 用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String loginAccount) throws UsernameNotFoundException {
        Map<String, String> permissionMap = CollUtil.newHashMap();
        UserLoginVo userLoginVo = this.organizationService.queryUserInfo(loginAccount).getData();
        List<String> permissions = this.organizationService.queryUserPermissions(userLoginVo.getUserId()).getData();
        permissions.forEach(permission -> permissionMap.put(permission, permission));
        String[] permissionArray = new String[permissions.size()];
        return User.withUsername(userLoginVo.getLoginAccount()).password(userLoginVo.getPassword())
                .authorities(permissions.toArray(permissionArray)).build();
    }

    @PostConstruct
    public void initData() {
        Map<String, String> permissionMap = CollUtil.newHashMap();
        List<String> permissions = this.organizationService.queryAccessPaths().getData();
        permissions.forEach(permission -> permissionMap.put(permission, permission));
        this.redisTemplate.opsForHash().putAll(RedisConstants.PermissionCacheKey.ACCESS_PATHS, permissionMap);
    }

    /**
     * sentinel测试
     *
     * @param id id
     * @return 端口+id
     */
    @SentinelResource(value = "sentinelTest",
            blockHandlerClass = CustomBlockHandler.class, blockHandler = SentinelConstants.BlockHandler.SPECIFIC_TREATMENT_METHOD,
            fallbackClass = CustomFallbackHandler.class, fallback = SentinelConstants.Fallback.SPECIFIC_TREATMENT_METHOD)
    public R<String> sentinelTest(Long id) {

        if (id == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, SentinelConstants.ForegroundPrompt.ID_CANNOT_BE_EMPTY);
        }
        if (id.equals(Long.valueOf(DigitalConstants.ZERO))) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, SentinelConstants.ForegroundPrompt.ID_CANNOT_BE_ZERO);
        }
        return this.organizationService.sentinelTest(id);
    }
}
