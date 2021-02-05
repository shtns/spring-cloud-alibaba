package com.sh.msg.receive;

import com.sh.api.common.constant.RabbitmqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收全局消息
 *
 *
 * @author 盛浩
 * @date 2021/1/19 22:43
 */
@Slf4j
@Component
public class ReceiveGlobalMsgService {

    /**
     * 接收国内消息
     *
     * @param message 消息
     */
    @RabbitListener(
            bindings = {
                    @QueueBinding(value = @Queue(autoDelete = "true"),
                            exchange = @Exchange(value = RabbitmqConstants.Config.Exchange.Dom.EXCHANGE_NAME),
                            key = RabbitmqConstants.Config.Routing.Dom.KEY_NAME)
            })
    public void receiveMessageTms(String message) {
        log.info("国内消息：{}", message);
    }

    /**
     * 接收国际消息
     *
     * @param message 消息
     */
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(autoDelete = "true"),
                    exchange = @Exchange(value = RabbitmqConstants.Config.Exchange.Inter.EXCHANGE_NAME),
                    key = RabbitmqConstants.Config.Routing.Inter.KEY_NAME)
    })
    public void receiveMessageCity(String message) {
        log.info("国际消息：{}", message);
    }
}
