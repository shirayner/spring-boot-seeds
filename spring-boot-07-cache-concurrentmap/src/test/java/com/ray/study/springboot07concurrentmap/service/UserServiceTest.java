package com.ray.study.springboot07concurrentmap.service;

import com.ray.study.springboot07concurrentmap.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author shira 2019/05/13 19:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UserServiceTest {

	private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

	@Autowired
	UserService userService;


	@Test
	public void testCache() {
		List<User> userToAddList = new ArrayList<>();
		userToAddList.add(new User("aaspring000",20));
		userToAddList.add(new User("spring",21));
		userToAddList.add(new User("ttspring",22));
		userToAddList.add(new User("ttspring111",23));
		userToAddList.add(new User("springaaa",24));

		// 新增用户
		userToAddList.forEach(user -> {
			userService.save(user);
		});
		log.info("==========新增用户完毕============");

		User user0 = userService.findByNameAndAge(new User("spring",21));
		log.info("用户查询：user0 :"+ user0.getName());
		User user1 = userService.findByNameAndAge(new User("ttspring",22));
		log.info("用户查询：user1 :"+ user1.getName());
		User user2 = userService.findByNameAndAge(new User("spring",21));
		log.info("用户查询：user3 :"+ user2.getName());



		userService.remove(user0.getId());
	}


}