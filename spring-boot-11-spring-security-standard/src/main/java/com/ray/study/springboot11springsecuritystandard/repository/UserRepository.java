package com.ray.study.springboot11springsecuritystandard.repository;

import com.ray.study.springboot11springsecuritystandard.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author shira 2019/04/26 10:15
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {

    UserDO findByUsername(String username);
}
