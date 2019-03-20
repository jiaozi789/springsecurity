package io.github.jiaozi789;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 廖敏
 * 创建日期 2019-03-13 9:39
 **/
@Service
public class MyUserDetailService implements UserDetailsService {
    private String userName="zs";
    private String password="UI30yy9Nj4BRklYljPuXXw==";
    private String role="ROLE_ADMIN";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equalsIgnoreCase(userName)){
            List<GrantedAuthority> authorityList=new ArrayList<>();
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(role);
            authorityList.add(simpleGrantedAuthority);
            User user=new User(userName,password,authorityList);
            return user;
        }
        throw new UsernameNotFoundException(username);
    }
}
