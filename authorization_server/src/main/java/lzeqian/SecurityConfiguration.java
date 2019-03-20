package lzeqian;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author 廖敏
 * 创建日期 2019-03-12 16:15
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public UserDetailsService userDetailsService11() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("test").password(new BCryptPasswordEncoder().encode("123456")).roles("USER").build());
        return manager;
    }

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
                .defaultSuccessUrl("/toSuc",false)

                .and()
                .httpBasic().and().csrf().disable();

    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService11()).passwordEncoder(new BCryptPasswordEncoder());
    }
}
