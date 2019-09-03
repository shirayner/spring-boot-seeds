package com.ray.study.springboot11springsecuritystandard.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色
 *
 * @author shira 2019/09/02 14:57
 */
@Data
@Entity
@Table(name = "role")
public class RoleDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
