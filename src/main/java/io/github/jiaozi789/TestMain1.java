package io.github.jiaozi789;

import java.util.regex.Pattern;

/**
 * @author 廖敏
 * 创建日期 2019-03-12 20:37
 **/
public class TestMain1 {
    public static void main1(String[] args) {
        Pattern BCRYPT_PATTERN = Pattern
                .compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
        String g="$2a$08$sK5xRx6hjylSOrjFgSkLauaJayPIT980M.SPv30RDi7Js.rueAvGe";
        System.out.println(BCRYPT_PATTERN.matcher(g).matches());
    }

    public static void main(String[] args) {
        Md5PasswordEncoder mpe=new Md5PasswordEncoder();
        System.out.println(mpe.encode("234567"));
    }
}
