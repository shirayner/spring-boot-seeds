package com.ray.study.springboot06nosqlredis;

import com.ray.study.springboot06nosqlredis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * description
 *
 * @author shira 2019/05/10 22:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
	private static final Logger log = LoggerFactory.getLogger(RedisTemplateTest.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Serializable> redisCacheTemplate;


	@Test
	public void get() throws InterruptedException {

		// 1.测试线程安全
		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		IntStream.range(0, 1000).forEach( i ->
				// increment("k", 1): 将redis中 k 的值，自增1
				executorService.execute(() -> stringRedisTemplate.opsForValue().increment("k", 1))
		);

		Thread.sleep(6000);
		// public class StringRedisTemplate extends RedisTemplate<String, String> ,值是以String类型存放，因此与"1000"对比
		assertThat(stringRedisTemplate.opsForValue().get("k"), is("1000"));


		// 2.测试字符串存取
		stringRedisTemplate.opsForValue().set("name", "tom");
		assertThat(stringRedisTemplate.opsForValue().get("name"), is("tom"));


		// 3.测试对象存取
		String key = "battcn:user:1";
		User user = new User(1L, "u1", 20);
		redisCacheTemplate.opsForValue().set(key, user);
		assertThat((User) redisCacheTemplate.opsForValue().get(key), equalTo(user));

	}
}
