package com.sh.organization.notable.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.api.common.constant.DigitalConstants;
import com.sh.api.common.constant.NoTableConstants;
import com.sh.api.common.constant.StringFormattingConstants;
import com.sh.api.common.constant.DateTimeFormatConstants;
import com.sh.api.organization.notable.entity.NoTable;
import com.sh.organization.notable.mapper.NoTableMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

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

        if (StrUtil.isBlank(orderType)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    NoTableConstants.Error.ORDER_TYPE_CANNOT_BE_EMPTY);
        }

        NoTable noTable = this.getOne(Wrappers.<NoTable>lambdaQuery().eq(NoTable::getNoType, orderType));
        if (ObjectUtil.isNull(noTable)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    NoTableConstants.Error.NO_ORDER_NUMBER_OF_THIS_TYPE_WAS_FOUND);
        }

        int nowValue = noTable.getNoValue() + DigitalConstants.ONE;
        if (! this.update(Wrappers.<NoTable>lambdaUpdate()
                .set(NoTable::getNoValue, nowValue).eq(NoTable::getNoType, orderType)
                .eq(NoTable::getNoValue, noTable.getNoValue()))) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    NoTableConstants.Error.ORDER_NUMBER_GENERATION_FAILED);
        }

        return StrUtil.concat(Boolean.TRUE, orderType, DateUtil.format(DateUtil.date(), DateTimeFormatConstants.TIME_FORMAT_SHORT),
                String.format(StringFormattingConstants.FORMATTING_TYPE, nowValue));
    }
}
