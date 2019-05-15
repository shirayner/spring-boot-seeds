package com.ray.study.springboot08messagingrabbitmq.service;

import com.ray.study.springboot08messagingrabbitmq.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description
 *
 * @author shira 2019/05/15 14:19
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMsgServiceTest {

	@Autowired
	RabbitMsgService rabbitMsgService;

	@Test
	public void testRabbit() {
		rabbitMsgService.sendMsg("冷风如刀，以大地为砧板；万里飞雪，将苍穹作烘炉");

		User user = new User();
		user.setId(1L);
		user.setName("阿飞");
		user.setName("18");

		rabbitMsgService.sendUser(user);
	}


}