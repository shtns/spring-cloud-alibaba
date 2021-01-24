package com.sh.api.organization.notable.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单号自动生成
 *
 *
 * @author 盛浩
 * @date 2021/1/22 17:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NoTable extends Model<NoTable> {

    /**
     * 订单类型（TR：出差单号  I：国际订单单号 D：国内订单单号 ）
     */
    private String noType;

    /**
     * 记录是否已经使用
     */
    private Integer noValue;
}
