package com.ray.study.springboot02.restfultest.service;

import com.ray.study.springboot02.restfultest.model.User;

import java.util.List;

/**
 * description
 *
 * @author shira 2019/04/28 15:45
 */
public interface UserService {

	/**
	 * 获取用户列表
	 * @return
	 */
	List<User> list();

	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	User get(Long id);

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	User insert(User user);

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	User update(User user);

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	boolean delete(Long id);

}
