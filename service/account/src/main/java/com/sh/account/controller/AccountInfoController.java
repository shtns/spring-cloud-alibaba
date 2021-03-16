package com.sh.account.controller;

import com.sh.account.service.AccountInfoServiceImpl;
import com.sh.api.common.util.R;
import com.sh.api.seata.account.dto.reduce.AccountReduceDto;
import com.sh.api.seata.account.entity.AccountInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 账户信息管理
 *
 *
 * @author 盛浩
 * @date 2020/12/27 22:57
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/account")
public class AccountInfoController {

    private final AccountInfoServiceImpl accountInfoService;

    /**
     * 减余额
     *
     * @param accountReduceDto 余额减少dto
     * @return 是否修改成功
     */
    @PostMapping(value = "/lose_balance")
    public R<Boolean> loseBalance(@RequestBody AccountReduceDto accountReduceDto) {
        return R.ok(accountInfoService.loseBalance(accountReduceDto));
    }

    /**
     * 查询账户信息
     *
     * @param userId 用户id
     * @return 账户信息
     */
    @GetMapping
    public R<AccountInfo> queryAccountInfo(Long userId) { return R.ok(accountInfoService.queryAccountInfo(userId)); }
}
