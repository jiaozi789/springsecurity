package io.github.jiaozi789;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author 廖敏
 * 创建日期 2019-03-12 16:15
 **/
@Configuration
@EnableWebSecurity
/**
 *  三种方法级权限控制
 * @author luenxin
 * 1.securedEnabled: Spring Security’s native annotation
 * 2.jsr250Enabled: standards-based and allow simple role-based constraints
 * 3.prePostEnabled: expression-based
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("test").password(new BCryptPasswordEncoder().encode("123456")).roles("USER").build());
//        return manager;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/test").authenticated()
                    .regexMatchers("/images/.+\\.jpg").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/rlogin")
                    .loginProcessingUrl("/myauth")
                    .failureForwardUrl("/rlogin")
                    .usernameParameter("user")
                    .passwordParameter("pass")

                    .permitAll()
                //注意 如果你访问 / 跳转到登录页面，当登录成功后会重新重定向到/ true可以设置 不跳转到之前这个页面 登录后直接到这个页面
                .defaultSuccessUrl("/toSuc",true)

                .and()
                .httpBasic().and().csrf().disable();

    }
//    @Autowired DataSource dataSource;
//    @Autowired PasswordEncoder passwordEncoder;
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(8);
//        return bCryptPasswordEncoder;
//    }
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
//    }
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(myUserDetailService).passwordEncoder(md5PasswordEncoder);
    }
}
