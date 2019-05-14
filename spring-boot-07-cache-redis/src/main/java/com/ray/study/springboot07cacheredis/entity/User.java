package com.ray.study.springboot07cacheredis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 用户模型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseDO{

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;


}

