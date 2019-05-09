package com.ray.study.springboot05jpa.repository;

import com.ray.study.springboot05jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description
 *
 * @author shira 2019/04/26 10:15
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

	/**
	 * 查询年龄大于等于指定年龄的用户
	 * @param age 年龄
	 * @return userList
	 */
	List<User> findByAgeGreaterThanEqualOrderById(Integer age);

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param name 用户名
	 * @return userList
	 */
	List<User> findAllByName(String name);


	/**
	 * 根据用户名模糊查询
	 * @param name username
	 * @return userList
	 */
	List<User> findByNameLike(String name);

	/**
	 * 模糊查询
	 * @param name
	 * @return
	 */
	List<User> findByNameContaining(String name);
}
