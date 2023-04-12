package cn.codingjc.peekaboo.api.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth: coding_jc
 * @date: 2023/4/12 22:10
 * @desc:
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
