package com.ray.study.springboot08messagingrabbitmq.receiver;

import com.rabbitmq.client.Channel;
import com.ray.study.springboot08messagingrabbitmq.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author shira 2019/05/15 14:11
 */
@Component
@Slf4j
public class RabbitMsgReceiver {

	/**
	 * 定义监听字符串队列名称
	 * @param msg
	 */
	@RabbitListener(queues = {"${rabbitmq.queue.msg}"})
	public void reveiveMsg(String msg){
		log.info("收到消息：{}",msg);
	}


	/**
	 * 定义监听用户消息队列名称
	 * @param user
	 */
	@RabbitListener(queues = {"${rabbitmq.queue.user}"})
	public void reveiveUser(User user, Message message, Channel channel){
		log.info("收到用户消息：{}",user);
	}

}
