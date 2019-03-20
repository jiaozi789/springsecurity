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
    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "hello";
    }
}
