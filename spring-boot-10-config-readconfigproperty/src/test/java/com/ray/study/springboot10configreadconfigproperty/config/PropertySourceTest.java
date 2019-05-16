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
 * @author shira 2019/05/16 21:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertySourceTest {

	@Autowired
	UserProperties userProperties;

	@Test
	public void testGetProperty(){
		assertThat(userProperties.getNikeName(), is("shirayner"));
		assertThat(userProperties.getAge(), is(25L));
		assertThat(userProperties.getEmail(), is("1766211120@qq.com"));
	}
}
