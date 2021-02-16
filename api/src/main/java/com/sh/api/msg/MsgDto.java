package com.sh.api.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 消息dto
 *
 *
 * @author 盛浩
 * @date 2021/2/16 17:07
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MsgDto {

    /**
     * 手机号
     */
    private String mobilePhoneNo;

    /**
     * 航空公司名称
     */
    private String airlineName;

    /**
     * 登机人姓名
     */
    private String boardingPersonName;

    /**
     * 出发日期（月份+日期）
     */
    private String departureDate;

    /**
     * 航班号
     */
    private String flightNo;

    /**
     * 手提行李（KG）
     */
    private Integer handLuggage;

    /**
     * 托运行李（KG）
     */
    private Integer registeredLuggage;

    /**
     * 测试url
     */
    private String testUrl;
}
