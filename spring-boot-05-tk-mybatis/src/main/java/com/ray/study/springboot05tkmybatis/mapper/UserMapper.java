package com.ray.study.springboot05tkmybatis.mapper;

import com.ray.study.springboot05tkmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * description
 *
 * @author shira 2019/05/09 21:27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 根据用户名查询用户
	 * @param name 用户名
	 * @return  user
	 */
	User findByName(String name);


}