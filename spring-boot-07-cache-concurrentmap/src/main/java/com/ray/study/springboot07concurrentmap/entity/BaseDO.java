package com.ray.study.springboot07concurrentmap.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * BaseDO
 *
 * @author shira 2019/05/08 17:17
 */
@Data
@NoArgsConstructor
@MappedSuperclass  // 声明子类可继承基类字段
public class BaseDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	private Date creationDate;

	@JsonIgnore
	private Date lastUpdateDate;
}

