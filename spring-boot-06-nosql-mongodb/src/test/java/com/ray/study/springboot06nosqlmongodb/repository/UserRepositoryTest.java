package com.ray.study.springboot06nosqlmongodb.repository;


import com.ray.study.springboot06nosqlmongodb.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * description
 *
 * @author shira 2019/05/08 17:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

	@Autowired
	UserRepository userRepository;


	/**
	 * 查询用户
	 */
	@Test
	public void queryUser(){
		// 先清空数据
		userRepository.deleteAll();

		List<User> userToAddList = new ArrayList<>();
		userToAddList.add(new User("aatomcat000",20));
		userToAddList.add(new User("tomcat",21));
		userToAddList.add(new User("tom",21));
		userToAddList.add(new User("tttomcat",22));
		userToAddList.add(new User("tttomcat111",23));
		userToAddList.add(new User("tomcataaa",24));

		// 新增用户
		userToAddList.forEach(user -> {
			userRepository.save(user);
		});


		// 查询所有名字为tom的用户
		List<User> userList = userRepository.findAllByName("tom");
		assertThat(userList.size(), is(1));
		log.info("[findAllByName:tom]:  {}",userList);

		// 查询所有年龄大于等于 21 的用户
		List<User> userList2 = userRepository.findByAgeGreaterThanEqualOrderById(21);
		assertThat(userList2.size(), is(5));
		log.info("[findByAgeGreaterThanEqualOrderById:21]:  {}",userList2);

		// 精确查询，这里的like 并不是模糊查询
		List<User> userList3 = userRepository.findByNameLike("tom");
		// TODO 验证 Spring Data JPA  Like 的查询逻辑
		// assertThat(userList3.size(), is(not(equalTo(6)))); // 等于6？确实是等于6
		log.info("[findByNameLike:tom:size()]:  {}",userList3.size());
		log.info("[findByNameLike:tom]:  {}",userList3);

		// 模糊查询: Containing才是模糊查询
		List<User> userList4 = userRepository.findByNameContaining("tom");
		assertThat(userList4.size(), is(6));
		log.info("[findByNameContaining:tom]:  {}",userList4);
	}


	/**
	 * 新增用户
	 */
	@Test
	public void insertUser(){

		User user = new User();
		user.setName("tom");
		user.setAge(21);
		User user1 = userRepository.save(user);

		assertThat(user.getId(),is(notNullValue()));
	}


	/**
	 * 更新用户
	 */
	@Test
	public void updateUser(){

		User user = new User();
		user.setId("11111111111");
		user.setName("tom2");
		user.setAge(1212);
		User user1 = userRepository.save(user);

		Optional<User> optionalUser = userRepository.findById("11111111111");
		User updatedUser = null;
		if(optionalUser.isPresent()){
			updatedUser = optionalUser.get();
		}

		assertThat(updatedUser.getAge(),is(1212));
	}


	/**
	 * 删除用户
	 */
	@Test
	public void deleteUser(){
		User user = new User();
		user.setName("tom2");
		userRepository.delete(user);

		List<User> userList = userRepository.findAllByName("tom2");
		assertThat(userList, is(nullValue()));

	}

}