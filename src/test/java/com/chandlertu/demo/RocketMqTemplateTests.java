package com.chandlertu.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketMqTemplateTests {

  @Autowired
  private RocketMqProperties rocketMqProperties;

  @Autowired
  private RocketMqTemplate rocketMqTemplate;

  @Test
  public void send() {
    rocketMqTemplate.send(rocketMqProperties.getConsumerTopic(), "rocketmq-demo-message-中文");
  }

}
