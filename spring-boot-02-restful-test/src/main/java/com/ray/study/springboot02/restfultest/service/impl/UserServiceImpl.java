package com.ray.study.springboot02.restfultest.service.impl;

import com.ray.study.springboot02.restfultest.model.User;
import com.ray.study.springboot02.restfultest.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * UserServiceImpl
 *
 * @author shira 2019/04/28 15:46
 */
@Service
public class UserServiceImpl  implements UserService {

	/** 创建线程安全的Map **/
	private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

	@Override
	public List<User> list() {
		return new ArrayList<>(users.values());
	}

	@Override
	public User get(Long id) {
		return users.get(id);
	}

	@Override
	public User insert(User user) {
		users.put(user.getId(), user);
		return user;
	}



	@Override
	public User update(User user) {
		User u = users.get(user.getId());
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(user.getId(), u);
		return u;
	}


	@Override
	public boolean delete(Long id) {
		users.remove(id);
		return true;
	}

}
