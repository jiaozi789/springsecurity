package io.github.lzeqian;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @author 廖敏
 * 创建日期 2019-03-12 16:03
 **/
@Controller
public class TestController {
    @GetMapping("/index")
    public String index(Principal principal, Model model) {
        if(principal == null ){
            return "index";
        }
        System.out.println(principal.toString());
        model.addAttribute("principal", principal);
        return "index";
    }
}

