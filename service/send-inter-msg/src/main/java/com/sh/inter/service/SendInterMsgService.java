package com.sh.inter.service;

import com.sh.api.common.constant.RabbitmqConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 发送国际消息服务
 *
 *
 * @author 盛浩
 * @date 2021/1/19 22:21
 */
@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class SendInterMsgService {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void sendMessage() {
        this.rabbitTemplate.convertAndSend(RabbitmqConstants.Config.Exchange.Inter.EXCHANGE_NAME,
                RabbitmqConstants.Config.Routing.Inter.KEY_NAME, RabbitmqConstants.Msg.SEND_INTER_TICKET_RESERVE_SUCCESS_SMS);
        log.info("国内发送消息：{}", RabbitmqConstants.Msg.SEND_INTER_TICKET_RESERVE_SUCCESS_SMS);
    }
}
