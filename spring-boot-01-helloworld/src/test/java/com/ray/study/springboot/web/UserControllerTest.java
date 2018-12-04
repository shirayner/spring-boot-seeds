package com.ray.study.springboot.web;

import com.ray.study.springboot.ApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @desc
 *
 * @author shirayner
 * @date 2018/12/4
 */

@Slf4j
public class UserControllerTest extends ApplicationTests {

	RequestBuilder request = null;

	@Test
	public void getUserList() throws Exception {
		// 1、get查一下user列表，应该为空
		request = get("/users/");

		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));
	}

	@Test
	public void postUser() throws Exception {
		// 2、post提交一个user
		request = post("/users/").param("id", "1").param("name", "测试大师").param("age", "20");

		mockMvc.perform(request).andExpect(content().string(equalTo("success")));


	}


	@Test
	public void putUser() throws Exception {
		// 4、put修改id为1的user
		request = put("/users/1").param("name", "测试终极大师").param("age", "30");
		mockMvc.perform(request).andExpect(content().string(equalTo("success")));


	}

	@Test
	public void getUser() throws Exception {
		// 5、get一个id为1的user
		request = get("/users/1");
		mockMvc.perform(request).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));

	}


	@Test
	public void deleteUser() throws Exception {
		// 6、del删除id为1的user
		request = delete("/users/1");
		mockMvc.perform(request).andExpect(content().string(equalTo("success")));
	}

	@Test
	public void testAll() throws Exception {
		// 测试UserController

		// 1、get查一下user列表，应该为空
		request = get("/users/");

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));

		// 2、post提交一个user
		request = post("/users/")
				.param("id", "1")
				.param("name", "测试大师")
				.param("age", "20");

		mockMvc.perform(request)
				.andExpect(content().string(equalTo("success")));

		// 3、get获取user列表，应该有刚才插入的数据
		request = get("/users/");
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

		// 4、put修改id为1的user
		request = put("/users/1")
				.param("name", "测试终极大师")
				.param("age", "30");
		mockMvc.perform(request)
				.andExpect(content().string(equalTo("success")));

		// 5、get一个id为1的user
		request = get("/users/1");
		mockMvc.perform(request)
				.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));

		// 6、del删除id为1的user
		request = delete("/users/1");
		mockMvc.perform(request).andExpect(content().string(equalTo("success")));

		// 7、get查一下user列表，应该为空
		request = get("/users/");
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));


	}

}