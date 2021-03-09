package com.sh.organization.notable.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.config.ServerErrorException;
import com.sh.api.common.constant.DateTimeFormatConstant;
import com.sh.api.common.constant.DigitalConstant;
import com.sh.api.common.constant.NoTableConstant;
import com.sh.api.common.constant.StringFormattingConstant;
import com.sh.api.organization.notable.entity.NoTable;
import com.sh.organization.notable.mapper.NoTableMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单号自动生成业务
 *
 *
 * @author 盛浩
 * @date 2021/1/22 17:41
 */
@Service
public class NoTableServiceImpl extends ServiceImpl<NoTableMapper, NoTable> implements IService<NoTable> {

    /**
     * 通过订单类型生成唯一订单号
     * 订单生成规则为 订单类型+当前日期+订单序列
     *
     * @return 唯一订单号
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public String generateOrderNumber(String orderType) {

        //检查订单类型、类型订单号信息是否为空
        if (StrUtil.isBlank(orderType)) {
            throw new ServerErrorException(NoTableConstant.Error.ORDER_TYPE_CANNOT_BE_EMPTY);
        }
        NoTable noTable = this.getOne(Wrappers.<NoTable>lambdaQuery().eq(NoTable::getNoType, orderType));
        if (ObjectUtil.isNull(noTable)) {
            throw new ServerErrorException(NoTableConstant.Error.NO_ORDER_NUMBER_OF_THIS_TYPE_WAS_FOUND);
        }

        //当前订单号序列值+1
        int nowValue = noTable.getNoValue() + DigitalConstant.ONE;
        //通过原始序列值进行修改，失败报错
        if (! this.update(Wrappers.<NoTable>lambdaUpdate()
                .set(NoTable::getNoValue, nowValue)
                .eq(NoTable::getNoType, orderType)
                .eq(NoTable::getNoValue, noTable.getNoValue()))) {
            throw new ServerErrorException(NoTableConstant.Error.ORDER_NUMBER_GENERATION_FAILED);
        }

        //返回唯一订单号
        return StrUtil.concat(Boolean.TRUE, orderType, DateUtil.format(DateUtil.date(), DateTimeFormatConstant.TIME_FORMAT_SHORT),
                String.format(StringFormattingConstant.FORMATTING_TYPE, nowValue));
    }
}
