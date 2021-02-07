package com.sh.organization.config;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.sh.api.common.constant.ClassConstants;
import com.sh.api.common.constant.MenuInfoConstants;
import com.sh.api.common.constant.ResourceConstants;
import com.sh.api.organization.resource.dto.ResourceSaveDto;
import com.sh.api.organization.resource.entity.ResourceInfo;
import com.sh.organization.city.controller.CityInfoController;
import com.sh.organization.country.controller.CountryInfoController;
import com.sh.organization.menu.controller.MenuInfoController;
import com.sh.organization.resource.controller.ResourceInfoController;
import com.sh.organization.resource.mapper.ResourceInfoMapper;
import com.sh.organization.role.controller.RoleInfoController;
import com.sh.organization.user.controller.UserInfoController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 扫描接口信息
 *
 *
 * @author 盛浩
 * @date 2021/2/5 15:39
 */
@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScanningInterfaceInfo {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    private final ResourceInfoMapper resourceInfoMapper;

    /**
     * 项目启动时执行
     */
    @PostConstruct
    public void removeAllResourceInfo() {
        //删除所有资源信息
        this.resourceInfoMapper.removeAllResourceInfo();
    }

    /**
     * 项目启动后，延迟一秒执行
     * 获取当前项目中的所有接口，通过一定格式保存到资源信息表中
     * */
    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
    public void addResourceInfo() {

        //遍历扫描到的请求路径
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : this.requestMappingHandlerMapping.getHandlerMethods().entrySet()) {

            //获取当前类绝对路径，如果为spring自带、资源信息管理、不进行保存
            String classAbsolutePath = m.getValue().getBeanType().getName();
            if (StrUtil.equals(classAbsolutePath, BasicErrorController.class.getName())) {
                continue;
            }
            if (StrUtil.equals(classAbsolutePath, ResourceInfoController.class.getName())) {
                continue;
            }

            //资源信息保存对象
            ResourceInfo resourceInfo = new ResourceSaveDto(
                    null,
                    this.removeRedundantSymbol(StrUtil.concat(Boolean.TRUE,
                            ResourceConstants.Url.ORGANIZATION,
                            m.getKey().getPatternsCondition().getPatterns().toString())),
                    this.removeRedundantSymbol(m.getKey().getMethodsCondition().getMethods().toString()))
                    .changeSaveResourceInfo();

            if (StrUtil.equals(classAbsolutePath, UserInfoController.class.getName())) {
                resourceInfo.setMenuId(MenuInfoConstants.Id.USER);
                resourceInfo.insert();
            } else if (StrUtil.equals(classAbsolutePath, RoleInfoController.class.getName())) {
                resourceInfo.setMenuId(MenuInfoConstants.Id.ROLE);
                resourceInfo.insert();
            } else if (StrUtil.equals(classAbsolutePath, MenuInfoController.class.getName())) {
                resourceInfo.setMenuId(MenuInfoConstants.Id.MENU);
                resourceInfo.insert();
            } else if (StrUtil.equals(classAbsolutePath, CountryInfoController.class.getName())) {
                resourceInfo.setMenuId(MenuInfoConstants.Id.COUNTRY);
                resourceInfo.insert();
            } else if (StrUtil.equals(classAbsolutePath, CityInfoController.class.getName())) {
                resourceInfo.setMenuId(MenuInfoConstants.Id.CITY);
                resourceInfo.insert();
            } else {
                //都不匹配的情况下，可能是新加的某信息管理这里需要配置，直接报错提示
                throw new RuntimeException(ClassConstants.SystemRuntime.NO_MATCHING_CLASSPATH_INFORMATION_WAS_FOUND);
            }
        }
    }

    /**
     * 删除获取到的资源路径、请求类型默认带[]符号问题
     *
     * @param beAboutToRemoveStr 即将删除的字符串
     * @return 处理完成的字符串
     */
    private String removeRedundantSymbol(String beAboutToRemoveStr) {
        return cn.hutool.core.util.StrUtil.removeAll(beAboutToRemoveStr, CharUtil.BRACKET_START, CharUtil.BRACKET_END);
    }
}
