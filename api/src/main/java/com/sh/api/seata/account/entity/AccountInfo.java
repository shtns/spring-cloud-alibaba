package com.sh.api.seata.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * 账户信息
 *
 *
 * @author 盛浩
 * @date 2020/12/25 21:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountInfo extends Model<AccountInfo> {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long accountId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 总额度
     */
    private BigDecimal total;

    /**
     * 已用额度
     */
    private BigDecimal used;

    /**
     * 剩余可用额度
     */
    private BigDecimal residue;
}
