package com.ray.study.springboot02.restfultest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ray.study.springboot02.restfultest.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
 * @author shira 2019/05/06 14:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MockMvcTest {

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
		// 构造get请求
		RequestBuilder request = get("/users/");

		// 执行get请求
		mvc.perform(request)
				.andExpect(status().isOk()); // 对请求结果进行期望，响应的状态为200

	}


	@Test
	public void postUser() throws Exception {
		User user =  new User(1L, "测试大师", 20);
		String postJson = objectMapper.writeValueAsString(user);

		RequestBuilder request = post("/users/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(postJson);      // 提交Json请求参数

		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("测试大师"))  // jsonPath取值
				.andDo(print());  // 打印响应结果
	}


	@Test
	public void postUser2() throws Exception {
		User user =  new User(1L, "测试大师", 20);
		String postJson = objectMapper.writeValueAsString(user);

		RequestBuilder request = post("/users/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(postJson);      // 提交Json请求参数

		MvcResult mvcResult = mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("测试大师"))  // jsonPath取值
				.andDo(print())  // 打印响应结果
				.andReturn();    // 返回响应结果


		MockHttpServletResponse response = mvcResult.getResponse();
		//拿到响应状态码
		int status = response.getStatus();
		//拿到结果
		String contentAsString = response.getContentAsString();
	}




	@Test
	public void putUser() throws Exception {
		// 4、put修改id为1的user
		User user1 =  new User(1L, "测试大师1", 21);
		String postJson1 = objectMapper.writeValueAsString(user1);
		RequestBuilder request = put("/users/")
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
	}


	@Test
	public void deleteUser() throws Exception {
		RequestBuilder request = delete("/users/1");
		mvc.perform(request)
				.andExpect(status().isOk());

		// 7、get查一下user列表，应该为空
		request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}


}
