package com.ray.study.springboot07cacheredis.service.impl;

import com.ray.study.springboot07cacheredis.entity.User;
import com.ray.study.springboot07cacheredis.repository.UserRepository;
import com.ray.study.springboot07cacheredis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author shira 2019/05/13 18:58
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	@CachePut(value = "user", key = "#user.name")
	public User save(User user) {
		User u = userRepository.save(user);
		log.info("新增：缓存用户，用户id为:{}", u.getId());
		return u;
	}


	@Override
	@CacheEvict(value = "user")
	public void remove(Long id) {
		log.info("删除：删除缓存，用户id为:{}",id);
		userRepository.deleteById(id);
	}

	@Override
	@Cacheable(value = "user", key = "#user.name")
	public User findByNameAndAge(User user) {
		user = userRepository.findByNameAndAge(user.getName(), user.getAge());
		log.info("查询：缓存用户，用户id为:{}",user.getId());
		return user;
	}

}
