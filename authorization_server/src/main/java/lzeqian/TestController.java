package lzeqian;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 廖敏
 * 创建日期 2019-03-12 16:03
 **/
@Controller
public class TestController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @PreAuthorize("hasAuthority('ADMIN1')")
    @ResponseBody
    @GetMapping("/a")
    public String a() {
        return "hello";
    }


    @RequestMapping("/toSuc")
    public String suc() {
        return "suc";
    }
    @RequestMapping("/rlogin")
    public String rlogin(String name) {
        return "lg";
    }
}
