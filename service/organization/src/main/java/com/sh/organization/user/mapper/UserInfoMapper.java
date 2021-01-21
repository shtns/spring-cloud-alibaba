package com.sh.organization.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sh.api.organization.user.entity.UserInfo;
import java.util.List;

/**
 * 用户信息映射
 *
 *
 * @author 盛浩
 * @date 2021/1/16 1:47
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 查询用户权限列表
     *
     * @param userId 用户id
     * @return 用户权限列表
     */
    List<String> queryUserPermissions(Long userId);
}
