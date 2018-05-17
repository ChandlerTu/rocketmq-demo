package com.chandlertu.demo;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMqConfiguration {

  @Autowired
  private RocketMqProperties rocketMqProperties;

  @Bean(destroyMethod = "shutdown")
  DefaultMQPushConsumer rocketMqConsumer(RocketMqMessageListener rocketMqMessageListener)
      throws MQClientException {
    DefaultMQPushConsumer consumer =
        new DefaultMQPushConsumer(rocketMqProperties.getConsumerGroup());
    consumer.setNamesrvAddr(rocketMqProperties.getNameServer());
    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
    consumer.subscribe(rocketMqProperties.getConsumerTopic(), rocketMqProperties.getConsumerTags());
    consumer.registerMessageListener(rocketMqMessageListener);
    consumer.setVipChannelEnabled(false);
    consumer.start();
    return consumer;
  }

  @Bean(destroyMethod = "shutdown")
  DefaultMQProducer rocketMqProducer() throws MQClientException {
    DefaultMQProducer producer = new DefaultMQProducer(rocketMqProperties.getProducerGroup());
    producer.setNamesrvAddr(rocketMqProperties.getNameServer());
    producer.setVipChannelEnabled(false);
    producer.start();
    return producer;
  }

}
