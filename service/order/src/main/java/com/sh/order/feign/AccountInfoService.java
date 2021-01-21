package com.sh.order.feign;

import com.sh.api.common.constant.MicroServiceNameConstants;
import com.sh.api.common.util.R;
import com.sh.api.seata.account.dto.reduce.AccountReduceDto;
import com.sh.api.seata.account.entity.AccountInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 账户信息服务
 *
 *
 * @author 盛浩
 * @date 2020/12/27 20:17
 */
@FeignClient(value = MicroServiceNameConstants.ACCOUNT_SERVICE)
public interface AccountInfoService {

    /**
     * 减余额
     *
     * @param accountReduceDto 余额减少dto
     * @return 是否修改成功
     */
    @PostMapping(value = "/account/lose_balance")
    R<Boolean> loseBalance(@RequestBody AccountReduceDto accountReduceDto);

    /**
     * 查询账户信息
     *
     * @param userId 用户id
     * @return 账户信息
     */
    @GetMapping(value = "/account/{userId}")
    R<AccountInfo> queryAccountInfo(@PathVariable(value = "userId") Long userId);
}
