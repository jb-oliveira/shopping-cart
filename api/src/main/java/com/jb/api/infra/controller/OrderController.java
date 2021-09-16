package com.jb.api.infra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @RequestMapping("/")
    public @ResponseBody
    String greeting() {
        return "Hello, World";
    }

}
