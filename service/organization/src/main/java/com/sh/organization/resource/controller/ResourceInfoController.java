package com.sh.organization.resource.controller;

import com.sh.api.common.util.R;
import com.sh.organization.resource.service.ResourceInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 资源信息管理
 *
 *
 * @author 盛浩
 * @date 2021/2/6 14:59
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/resource")
public class ResourceInfoController {

    private final ResourceInfoServiceImpl resourceInfoService;

    /**
     * 查询用户资源地址列表
     *
     * @param roleId 角色id
     * @return 资源地址列表
     */
    @GetMapping(value = "/user_all")
    public R<List<String>> queryResourcePaths(Long roleId) {
        return R.ok(resourceInfoService.queryResourcePaths(roleId));
    }

    /**
     * 通过资源地址请求类型返回具体的请求类型
     *
     * @param requestPath 请求地址
     * @param requestType 请求类型
     * @return 具体的请求类型
     */
    @GetMapping(value = "/request_type")
    public String returnRequestType(String requestPath, String requestType) {
        return resourceInfoService.returnRequestType(requestPath, requestType);
    }
}
