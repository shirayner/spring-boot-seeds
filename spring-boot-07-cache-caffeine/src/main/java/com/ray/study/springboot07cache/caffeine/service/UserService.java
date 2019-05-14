package com.ray.study.springboot07cache.caffeine.service;

import com.ray.study.springboot07cache.caffeine.entity.User;

/**
 * description
 *
 * @author shira 2019/05/13 18:54
 */
public interface UserService {

	User save(User user);

	void remove(Long id);

	User findByNameAndAge(User user);

}
