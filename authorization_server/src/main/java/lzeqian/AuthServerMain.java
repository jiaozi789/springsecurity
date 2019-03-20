package lzeqian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @Author 廖敏
 * @Date 2019-03-01 10:17
 **/
@SpringBootApplication
public class AuthServerMain {
    public static void main(String[] args) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException {
        SpringApplication.run(AuthServerMain.class);


    }
}
