package com.sh.msg.receive;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.sh.api.common.constant.RabbitmqConstant;
import com.sh.api.msg.MsgDto;
import com.sh.msg.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Map;

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

    private final Map<String, String> header = CollUtil.newHashMap();

    private final Map<String, String> query = CollUtil.newHashMap();

    private final Map<String, String> body = CollUtil.newHashMap();

    /**
     * 项目启动后，延迟一秒执行
     * */
    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
    private void buildInfo() {
        this.header.put("Authorization", "APPCODE " + RabbitmqConstant.Msg.APP_CODE);
        this.query.put("tpl_id", "TP1710262");
    }

    /**
     * 接收国内消息
     *
     * @param msgDto 消息dto
     */
    @RabbitListener(
            bindings = {
                    @QueueBinding(value = @Queue(autoDelete = "true"),
                            exchange = @Exchange(value = RabbitmqConstant.Config.Exchange.RESERVATION_NAME),
                            key = RabbitmqConstant.Config.Routing.Dom.KEY_NAME)
            })
    public void receiveMessageTms(MsgDto msgDto) {

        this.query.put("mobile", msgDto.getMobilePhoneNo());
        this.query.put("param", StrUtil.concat(Boolean.TRUE,
                "airlineName:" + msgDto.getAirlineName(),
                "boardingPersonName:" + msgDto.getBoardingPersonName(),
                "departureDate:" + msgDto.getDepartureDate(),
                "handLuggage:" + msgDto.getHandLuggage(),
                "registeredLuggage:" + msgDto.getRegisteredLuggage(),
                "testUrl:" + msgDto.getTestUrl()));
        this.query.put("tpl_id", "TP1710262");

        try {

            HttpResponse response = HttpUtil.doPost(RabbitmqConstant.Msg.HOST, RabbitmqConstant.Msg.REQUEST_PATH, HttpMethod.POST,
                    this.header, this.query, this.body);


            log.info("发送国内消息成功");


        } catch (Exception e) {
            e.printStackTrace();
            log.info("发送国内消息失败：{}", e.getMessage());
        }
    }

    /**
     * 接收国际消息
     *
     * @param msgDto 消息dto
     */
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(autoDelete = "true"),
                    exchange = @Exchange(value = RabbitmqConstant.Config.Exchange.RESERVATION_NAME),
                    key = RabbitmqConstant.Config.Routing.Inter.KEY_NAME)
    })
    public void receiveMessageCity(MsgDto msgDto) {

        this.query.put("mobile", msgDto.getMobilePhoneNo());
        this.query.put("param", StrUtil.concat(Boolean.TRUE,
                "airlineName:" + msgDto.getAirlineName(),
                "boardingPersonName:" + msgDto.getBoardingPersonName(),
                "departureDate:" + msgDto.getDepartureDate(),
                "handLuggage:" + msgDto.getHandLuggage(),
                "registeredLuggage:" + msgDto.getRegisteredLuggage(),
                "testUrl:" + msgDto.getTestUrl()));

        try {

            HttpResponse response = HttpUtil.doPost(RabbitmqConstant.Msg.HOST, RabbitmqConstant.Msg.REQUEST_PATH, HttpMethod.POST,
                    this.header, this.query, this.body);



            log.info("发送国际消息成功");

        } catch (Exception e) {
            log.info("发送国际消息失败：{}", e.getMessage());
        }
    }
}
