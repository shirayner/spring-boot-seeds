package com.ray.study.springboot.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @desc
 *
 * @author shirayner
 * @date 2018/12/4
 */

@Setter
@Getter
@NoArgsConstructor
public class User {
	private Long id;
	private String name;
	private Integer age;
}
