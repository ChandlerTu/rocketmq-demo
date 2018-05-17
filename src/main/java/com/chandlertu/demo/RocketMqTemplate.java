package com.chandlertu.demo;

import lombok.extern.apachecommons.CommonsLog;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@CommonsLog
@Component
public class RocketMqTemplate {

  @Autowired
  @Qualifier("rocketMqProducer")
  private DefaultMQProducer rocketMqProducer;

  public void send(String topic, String message) {
    send(topic, null, message);
  }

  public void send(String topic, String tags, String message) {
    try {
      log.info("Send Message: " + message);
      byte[] body = message.getBytes(RemotingHelper.DEFAULT_CHARSET);

      Message msg;
      if (tags == null) {
        msg = new Message(topic, body);
      } else {
        msg = new Message(topic, tags, body);
      }

      SendResult sendResult = rocketMqProducer.send(msg);
      log.info(sendResult.getSendStatus());
    } catch (Exception e) {
      log.error("", e);
    }
  }

}
