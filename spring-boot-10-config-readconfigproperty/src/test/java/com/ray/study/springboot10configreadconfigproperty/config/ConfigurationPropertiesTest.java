package com.ray.study.springboot10configreadconfigproperty.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * description
 *
 * @author shira 2019/05/16 21:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationPropertiesTest {

	@Autowired
	BlogProperties blogProperties;

	@Test
	public void testGetConfigurationProperties() {
		assertThat(blogProperties.getName(),is("shirayner的博客"));
		assertThat(blogProperties.getArticles().size(),is(2));
	}

}