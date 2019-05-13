package com.ray.study.springboot06nosqlmongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * description
 *
 * @author shira 2019/05/13 11:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 8655851615465363473L;

	@Id
	private String id;

	private String name;

	private Integer age;

	private Date creationDate;

	private Date lastUpdateDate;


	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
		this.creationDate = new Date();
		this.lastUpdateDate = new Date();
	}
}