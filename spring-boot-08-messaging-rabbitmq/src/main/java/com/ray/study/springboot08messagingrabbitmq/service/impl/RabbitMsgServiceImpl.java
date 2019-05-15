package com.ray.study.springboot08messagingrabbitmq.service.impl;


import com.ray.study.springboot08messagingrabbitmq.service.RabbitMsgService;
import com.ray.study.springboot08messagingrabbitmq.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author shira 2019/05/15 13:49
 */
@Slf4j
@Service
public class RabbitMsgServiceImpl implements RabbitTemplate.ConfirmCallback, RabbitMsgService {


	@Value("${rabbitmq.queue.msg}")
	private String msgRouting;

	@Value("${rabbitmq.queue.user}")
	private String userRouting;

	@Autowired
	private RabbitTemplate rabbitTemplate;



	@Override
	public void sendMsg(String msg) {
		log.info("发送消息：{}", msg);
		// 设置回调
		rabbitTemplate.setConfirmCallback(this);
		// 发送消息，通过	msgRouting 确定队列
		rabbitTemplate.convertAndSend(msgRouting, msg);
	}

	@Override
	public void sendUser(User user) {
		log.info("发送用户消息：{}", user);
		// 设置回调
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.convertAndSend(userRouting, user);
	}


	/**
	 *  回调确认方法
	 *   spring.rabbitmq.publisher-confirms = true 时，才会进行回调确认，也就是会执行回调方法
	 *   spring.rabbitmq.publisher-confirms = false 时，不管有没有设置回调方法，都不会进行回调确认
	 *
	 * @param correlationData
	 * @param ack
	 * @param cause
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if(ack){
			log.info("消息消费成功");
		}else{
			log.info("消息消费失败：{}"+cause);
		}
	}

}
