package com.ray.study.springboot05tkmybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * description
 *
 * @author shira 2019/05/09 21:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 8655851615465363473L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer age;

	private Date creationDate;

	private Date lastUpdateDate;

}
