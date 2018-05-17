package com.chandlertu.demo;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rocketmq")
@Data
public class RocketMqProperties {

  private String consumerGroup;

  private String consumerTags;

  private String consumerTopic;

  private String nameServer;

  private String producerGroup;

}
