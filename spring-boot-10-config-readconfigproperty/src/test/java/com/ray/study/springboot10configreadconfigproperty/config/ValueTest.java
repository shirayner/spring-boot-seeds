package com.ray.study.springboot10configreadconfigproperty.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * description
 *
 * @author shira 2019/05/16 21:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValueTest {

	@Value("${csdn.blog.name}")
	private String blogName;

	@Value("${csdn.blog.homePage}")
	private String homePage;

	@Test
	public void testGetValue(){
		assertThat(blogName,is("shirayner的博客"));
		assertThat(homePage,is("https://blog.csdn.net/qq_26981333"));
	}


}
