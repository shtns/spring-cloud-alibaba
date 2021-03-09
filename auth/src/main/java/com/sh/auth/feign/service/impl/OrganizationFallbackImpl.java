package com.sh.auth.feign.service.impl;

import com.sh.api.common.constant.CommonConstant;
import com.sh.api.common.util.R;
import com.sh.api.organization.user.vo.login.UserLoginVo;
import com.sh.auth.feign.service.OrganizationService;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 调用组织服务宕机时返回默认值
 *
 *
 * @author 盛浩
 * @date 2021/1/20 13:54
 */
@Component
public class OrganizationFallbackImpl implements OrganizationService {

    @Override
    public R<UserLoginVo> queryUserInfo(String loginAccount) {
        return R.failed(CommonConstant.ForegroundPrompt.SERVER_BUSY_PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public R<Boolean> userLogin(String loginAccount, String password) {
        return R.failed(CommonConstant.ForegroundPrompt.SERVER_BUSY_PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public R<List<String>> queryResourcePaths(Long roleId) {
        return R.failed(CommonConstant.ForegroundPrompt.SERVER_BUSY_PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public R<String> sentinelTest(Long id) {
        return R.failed(CommonConstant.ForegroundPrompt.SERVER_BUSY_PLEASE_TRY_AGAIN_LATER);
    }
}
