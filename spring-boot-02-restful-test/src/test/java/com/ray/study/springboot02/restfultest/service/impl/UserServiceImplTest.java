package com.ray.study.springboot02.restfultest.service.impl;

import com.ray.study.springboot02.restfultest.model.User;
import com.ray.study.springboot02.restfultest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;


/**
 * description
 *
 * @author shira 2019/04/28 17:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	UserService userService;

	@Test
	public void addUser() {
		User user = new User();
		user.setId(1L);
		user.setName("张三");
		user.setAge(23);

		user = userService.insert(user);
		assertThat(user.getId(),is(notNullValue()));

	}

	@Test
	public void testAll(){
		// 1. add
		User user = new User();
		user.setId(1L);
		user.setName("张三");
		user.setAge(23);

		user = userService.update(user);
		assertThat(user.getId(),is(notNullValue()));
		assertThat(user.getName(), equalTo("张三"));


		// 2.list
		List<User>  userList= userService.list();
		assertThat(userList.size(),is(1));
		//assertThat(userList,is(not(empty())));


		// 3.get
		User user1 = userService.get(user.getId());
		assertThat(user1.getName(),equalTo("张三"));


		// 4.update
		user.setName("李四");
		userService.update(user);
		User user2 = userService.get(user.getId());
		assertThat(user2.getName(), equalTo("李四"));

		// 5.delete
		userService.delete(user.getId());
		User user3 = userService.get(user.getId());
		assertThat(user3, is(nullValue()));

	}


}