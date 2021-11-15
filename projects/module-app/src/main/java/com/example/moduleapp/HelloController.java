package com.example.moduleapp;

import com.xiaojin.lib.A;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String SayHello() {
        return A.transformString("hello") ;
    }
}
