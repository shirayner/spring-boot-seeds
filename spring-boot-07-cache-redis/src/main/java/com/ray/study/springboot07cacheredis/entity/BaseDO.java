package com.ray.study.springboot07cacheredis.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * BaseDO
 *
 * @author shira 2019/05/08 17:17
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass  // 声明子类可继承基类字段
public class BaseDO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	private Date creationDate;

	@JsonIgnore
	private Date lastUpdateDate;
}

