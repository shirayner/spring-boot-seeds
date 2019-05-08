package com.ray.study.springboot04swagger2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description
 *
 * @author shira 2019/04/28 15:01
 */
@ApiModel(description = "用户信息实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@ApiModelProperty("用户ID")
	private Long id;

	@ApiModelProperty("姓名")
	private String name;

	@ApiModelProperty("年龄")
	private Integer age;
}