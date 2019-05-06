package com.ray.study.springboot02.restfultest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ray.study.springboot02.restfultest.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * description
 *
 * @author shira 2019/05/05 19:01
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		//mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
		mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
	}


	@Test
	public void getUserList() throws Exception {

		RequestBuilder request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk());

	}

	@Test
	public void postUser() throws Exception {
		User user =  new User(1L, "测试大师", 20);
		String postJson = objectMapper.writeValueAsString(user);

		RequestBuilder request = post("/users/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(postJson);

		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("测试大师"))
				.andDo(print());
	}


	@Test
	public void testAll() throws Exception {
		RequestBuilder request;

		// 1、list查一下user列表，应该为空
		request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));

		// 2、post提交一个user
		User user =  new User(1L, "测试大师", 20);
		String postJson = objectMapper.writeValueAsString(user);
		request = post("/users/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(postJson);
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("测试大师"))
				.andDo(print());

		// 3、list获取user列表，应该有刚才插入的数据
		request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1));


		// 4、put修改id为1的user
		User user1 =  new User(1L, "测试大师1", 21);
		String postJson1 = objectMapper.writeValueAsString(user1);
		request = put("/users/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(postJson1);
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("测试大师1"))
				.andDo(print());

		// 5、get一个id为1的user
		request = get("/users/1");
		mvc.perform(request)
				.andExpect(content().string(equalTo(postJson1)));

		// 6、del删除id为1的user
		request = delete("/users/1");
		mvc.perform(request)
				.andExpect(status().isOk());

		// 7、get查一下user列表，应该为空
		request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}

}