package com.chandlertu.demo;

import java.util.List;

import lombok.extern.apachecommons.CommonsLog;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;

@CommonsLog
@Component
public class RocketMqMessageListener implements MessageListenerConcurrently {

  @Override
  public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
      ConsumeConcurrentlyContext context) {
    msgs.forEach(msg -> {
      try {
        log.info("Receive New Messages: " + msg);
        String body = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
        log.info(body);
      } catch (Exception e) {
        log.error("", e);
      }
    });
    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
  }

}
