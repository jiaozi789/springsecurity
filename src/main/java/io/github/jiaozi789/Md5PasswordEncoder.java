package io.github.jiaozi789;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 使用md5算法
 * @author 廖敏
 * 创建日期 2019-03-13 9:41
 **/
@Component
public class Md5PasswordEncoder implements PasswordEncoder {
    /**
     * 密码加密
     * @param rawPassword 原始密码
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            return Base64.getEncoder().encodeToString(messageDigest.digest(rawPassword.toString().getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 比较原始密码和加密后的密码是否相等
     * @param rawPassword 原始密码，用户文本框输入的
     * @param encodedPassword 加密后密码，数据库提取的
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            String encodPass=Base64.getEncoder().encodeToString(messageDigest.digest(rawPassword.toString().getBytes()));
            if(encodPass.equalsIgnoreCase(encodedPassword)){
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}
