package com.ray.study.springboot08messagingrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 *
 * @author shira 2019/05/15 13:32
 */

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.msg}")
	private String msgQueueName;

	@Value("${rabbitmq.queue.user}")
	private String userQueueName;

	@Bean
	public Queue defaultBookQueue() {
		// 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
		return new Queue(msgQueueName, true);
	}

	@Bean
	public Queue manualBookQueue() {
		// 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
		return new Queue(userQueueName, true);
	}
}