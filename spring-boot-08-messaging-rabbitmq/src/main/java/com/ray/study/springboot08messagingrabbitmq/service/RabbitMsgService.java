package com.ray.study.springboot08messagingrabbitmq.service;

import com.ray.study.springboot08messagingrabbitmq.entity.User;

/**
 * description
 *
 * @author shira 2019/05/15 13:45
 */
public interface RabbitMsgService {

	void sendMsg(String msg);

	void sendUser(User user);

}
