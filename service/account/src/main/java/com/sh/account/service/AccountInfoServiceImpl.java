package com.sh.account.service;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.account.mapper.AccountInfoMapper;
import com.sh.api.seata.account.dto.reduce.AccountReduceDto;
import com.sh.api.seata.account.entity.AccountInfo;
import org.springframework.stereotype.Service;

/**
 * 账户信息业务
 *
 *
 * @author 盛浩
 * @date 2020/12/27 23:00
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements IService<AccountInfo> {

    /**
     * 减余额
     *
     * @param accountReduceDto 余额减少dto
     * @return 是否修改成功
     */
    public Boolean loseBalance(AccountReduceDto accountReduceDto) {

        //模拟超时，全局事务回滚
        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }

        AccountInfo account = this.getOne(Wrappers.<AccountInfo>lambdaQuery().eq(AccountInfo::getUserId, accountReduceDto.getUserId()));

        return this.update(Wrappers.<AccountInfo>lambdaUpdate()
                    .set(AccountInfo::getUsed, NumberUtil.add(accountReduceDto.getMoney(), account.getUsed()))
                    .set(AccountInfo::getResidue, NumberUtil.sub(account.getResidue(), accountReduceDto.getMoney()))
                    .eq(AccountInfo::getUserId, accountReduceDto.getUserId()));
    }

    /**
     * 查询账户信息
     *
     * @param userId 用户id
     * @return 账户信息
     */
    public AccountInfo queryAccountInfo(Long userId) {
        return this.getOne(Wrappers.<AccountInfo>lambdaQuery().eq(AccountInfo::getUserId, userId));
    }
}
