package com.ray.study.springboot02.restfultest.service.impl;

import com.ray.study.springboot02.restfultest.model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * description
 *
 * @author shira 2019/05/06 13:40
 */

public class HamcrestTest {

	/**
	 * 1.测试核心匹配符的用法： is / anything
	 */
	@Test
	public void testCore(){
		String name ="tomcat";
		int n = 100;
		double d = 100.12;

		// is 是 is(equalTo(value)) 的缩写
		assertThat(name, is("tomcat"));
		assertThat(n, is(100));
		assertThat(d, is(100.12));

		// anything: 无论什么条件，测试都通过: Creates a matcher that always matches
		assertThat(d, anything(name));
		assertThat(d, anything());

	}


	/**
	 * 2.测试逻辑匹配符的用法：allOf / anyOf / not
	 */
	@Test
	public void testLogical(){
		double d = 3.35;

		// 与
		assertThat(d, allOf(greaterThan(3.0), lessThan(3.5)));   //  区间：(3.0,3.5)
		// 或
		assertThat(d, anyOf(greaterThan(3.3), lessThan(3.2)));   //  区间：d<3.2, d>3.3
		// 非，是 not(equalTo(x)) 的缩写
		assertThat(d, is(not(3.3)));   //  区间：d<3.2, d>3.3

	}



	/**
	 * 3.测试对象匹配符的相关用法
	 */
	@Test
	public void testObject(){

		User user = new User();
		user.setId(1L);

		assertThat(user.getId(),is(notNullValue()));
		assertThat(user.getName(), is(nullValue()));

	}


	/**
	 * 4.测试文本匹配的相关用法
	 */
	@Test
	public void testText(){
		String name = "tomcat";

		// 文本相等
		assertThat(name, equalTo("tomcat"));
		assertThat(name, equalToIgnoringCase("TomCat"));

		// 文本包含
		assertThat(name, containsString("ca"));
		assertThat(name, startsWith("tom"));
		assertThat(name, endsWith("cat"));
	}


	/**
	 * 5.测试数值匹配的相关用法
	 */
	@Test
	public void testNumber(){

		double d = 3.35;

		assertThat(d, closeTo(3.0, 0.5));   //closeTo：浮点型变量的值在3.0±0.5范围内，测试通过
		assertThat(d, greaterThan(3.0));
		assertThat(d, lessThan(3.5));
		assertThat(d, greaterThanOrEqualTo(3.3));
		assertThat(d, lessThanOrEqualTo(3.4));
	}


	/**
	 * 6.测试集合匹配的相关用法
	 */
	@Test
	public void testCollection(){
		User user1 = new User(1L, "tomcat", 21);
		User user2 = new User(2L, "springboot", 22);
		User user3 = new User(2L, "springboot", 22);

		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);

		Map<String, User> userMap = new HashMap<>();
		userMap.put(user1.getName(), user1);
		userMap.put(user2.getName(), user2);

		// list
		assertThat(userList, hasItem(user1));
		assertThat(userList, hasItem(user3));
		assertThat(userList.size(), is(2));
		assertThat(userList,is(not(empty())));

		// map
		assertThat(userMap, hasEntry(user1.getName(),user1));
		assertThat(userMap, hasKey(user1.getName()));
		assertThat(userMap, hasValue(user1));
	}

}
