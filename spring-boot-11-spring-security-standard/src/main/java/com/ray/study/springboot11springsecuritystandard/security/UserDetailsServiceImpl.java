package com.ray.study.springboot11springsecuritystandard.security;

/**
 * description
 *
 * @author shira 2019/09/02 15:08
 */
import com.ray.study.springboot11springsecuritystandard.entity.RoleDO;
import com.ray.study.springboot11springsecuritystandard.entity.UserDO;
import com.ray.study.springboot11springsecuritystandard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsServiceImpl
 *
 * @author shira 2019/04/26 10:15
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDO userDO = userRepository.findByUsername(s);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleDO roleDO : userDO.getRoleList()) {
            authorities.add(new SimpleGrantedAuthority(roleDO.getName()));
        }
        return new User(userDO.getUsername(), userDO.getPassword(), authorities);
    }

}
