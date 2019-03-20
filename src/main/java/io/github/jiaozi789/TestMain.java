package io.github.jiaozi789;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @Author 廖敏
 * @Date 2019-03-01 10:17
 **/
@SpringBootApplication
public class TestMain {

    public TestMain(){

    }
    public void TestMain(){

    }
    public static void main(String[] args) {
        SpringApplication.run(TestMain.class);
    }
}
