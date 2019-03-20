package lzeqian;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import sun.security.rsa.RSAKeyPairGenerator;

import java.security.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 廖敏
 * 创建日期 2019-03-13 15:14
 **/
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //clients.withClientDetails(new JdbcClientDetailsService(null));
        //添加客户端信息
        clients.inMemory()
                // 使用in-memory存储客户端信息
                .withClient("client")       // client_id
                .secret("{noop}secret")                   // client_secret 需要加上{noop}指定使用NoOpPasswordEncoder给DelegatingPasswordEncoder去校验密码
                .authorizedGrantTypes("authorization_code")     // 该client允许的授权类型
                .redirectUris("http://www.baidu.com")
                .scopes("all");                     // 允许的授权范围

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);



        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //一定要设置对称的SigningKey 用于验证之后的token是否有效的
        jwtAccessTokenConverter.setSigningKey("123456");


        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        keyStore.load(OAuth2AuthorizationServerConfig.class.getResourceAsStream("/a.keystore"),"123456".toCharArray());
        PublicKey publicKey = keyStore.getCertificate("test1").getPublicKey();
        PrivateKey privateKey = (PrivateKey)keyStore.getKey("test1", "123456".toCharArray());
        KeyPair kp=new KeyPair(publicKey,privateKey);
        jwtAccessTokenConverter.setKeyPair(kp);
        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter);
        endpoints.pathMapping("/oauth/confirm_access ","/extenal/oauth/confirm_access")

                .tokenStore(jwtTokenStore)
                .tokenEnhancer(jwtAccessTokenConverter)

                //添加额外信息到token中
//                .tokenEnhancer(new TokenEnhancer() {
//                    @Override
//                    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
//                        DefaultOAuth2AccessToken doat= (DefaultOAuth2AccessToken) oAuth2AccessToken;
//                        Map<String, Object> additionalInfo = new HashMap<>();
//                        additionalInfo.put("myname", "jiaozi");
//                        doat.setAdditionalInformation(additionalInfo);
//                        return doat;
//                    }
//                })
        ;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        security
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
        //security.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
