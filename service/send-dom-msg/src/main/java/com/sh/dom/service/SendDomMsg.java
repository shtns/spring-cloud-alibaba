package com.sh.dom.service;

import cn.hutool.core.util.StrUtil;
import com.sh.api.common.constant.RabbitmqConstant;
import com.sh.api.msg.MsgDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

/**
 * 发送国内消息服务
 *
 *
 * @author 盛浩
 * @date 2021/1/19 22:21
 */
@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class SendDomMsg {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 3分钟发送一次消息
     *
     */
    @Scheduled(cron = "0 0/3 * * * ?")
    public void sendMessage() {

        String departureDate = LocalDate.now().toString();

        this.rabbitTemplate.convertAndSend(
                RabbitmqConstant.Config.Exchange.RESERVATION_NAME,
                RabbitmqConstant.Config.Routing.Dom.KEY_NAME,
                new MsgDto("15555288543", "山东航空", "张三",
                        StrUtil.sub(departureDate, departureDate.indexOf("-"), departureDate.length()),
                        "9C6659", 7, 0, "testUrl"));
        log.info("发送国内预定成功路由key：{}", RabbitmqConstant.Config.Routing.Dom.KEY_NAME);
    }
}
