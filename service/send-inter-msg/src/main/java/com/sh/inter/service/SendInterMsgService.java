package com.sh.inter.service;

import cn.hutool.core.util.StrUtil;
import com.sh.api.common.constant.RabbitmqConstants;
import com.sh.api.msg.MsgDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

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
     * 3分钟发送一次消息
     *
     */
    @Scheduled(cron = "0 0/3 * * * ?")
    public void sendMessage() {

        String departureDate = LocalDate.now().toString();

        this.rabbitTemplate.convertAndSend(
                RabbitmqConstants.Config.Exchange.RESERVATION_NAME,
                RabbitmqConstants.Config.Routing.Inter.KEY_NAME,
                new MsgDto("15555288543", "吉祥航空", "李四",
                        StrUtil.sub(departureDate, departureDate.indexOf("-"), departureDate.length()),
                        "HO1792", 7, 20, "testUrl"));
        log.info("发送国际预定成功消息");
    }
}
