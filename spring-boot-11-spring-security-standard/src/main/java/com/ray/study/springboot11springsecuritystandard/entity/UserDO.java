package com.ray.study.springboot11springsecuritystandard.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 用户
 *
 * @author shira 2019/09/02 14:55
 */
@Data
@Entity
@Table(name = "user")
public class UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<RoleDO> roleList;

}
