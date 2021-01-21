package com.sh.api.seata.account.dto.reduce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 余额减少dto
 *
 *
 * @author 盛浩
 * @date 2020/12/28 15:28
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountReduceDto {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 金额
     */
    private BigDecimal money;
}
