package com.sh.organization.tms.controller;

import com.sh.api.common.util.R;
import com.sh.api.organization.tms.entity.TmsUserInfo;
import com.sh.organization.tms.service.TmsUserInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 数据同步管理
 *
 *
 * @author 盛浩
 * @date 2021/1/1 12:56
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/data_sync")
public class TmsUserInfoController {

    private final TmsUserInfoServiceImpl tmsUserInfoService;

    /**
     * 查询数据同步列表
     *
     * @return 数据同步列表
     */
    @GetMapping(value = "/list")
    public R<List<TmsUserInfo>> queryTmsUserInfos() {
        return R.ok(this.tmsUserInfoService.queryTmsUserInfos());
    }
}
