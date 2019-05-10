package com.ray.study.springboot05tkmybatis.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.study.springboot05tkmybatis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

/**
 * description
 *
 * @author shira 2019/05/10 14:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testUserMapper() {
		//1. 测试插入，主键回写
		for(int i = 0; i < 30; i++) {
			User user = new User();
			user.setName("tom"+i);
			user.setAge(20+i);

			userMapper.insertSelective(user);
			assertThat(user.getId(), is(notNullValue()));
		}

		//2. 测试手写sql
		User  user0 =userMapper.findByName("tom0");
		assertThat(user0, is(notNullValue()));


		// 3.分页+排序
		int pageSize =10;
		// 3.1 lambda 写法
		final PageInfo<User> pageInfo = PageHelper
				.startPage(1, pageSize)
				.setOrderBy("id desc")
				.doSelectPageInfo(() -> this.userMapper.selectAll());
		assertThat(pageInfo.getSize(), lessThanOrEqualTo(pageSize));

		// 3.2 普通写法
		PageHelper.startPage(1, pageSize).setOrderBy("id desc");
		List<User> userList = userMapper.selectAll();
		final PageInfo<User> userPageInfo = new PageInfo<>(userList);
		assertThat(userPageInfo.getSize(), lessThanOrEqualTo(pageSize));

	}
}