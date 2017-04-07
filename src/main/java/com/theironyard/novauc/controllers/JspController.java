package com.theironyard.novauc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

    @RequestMapping(value="/jspTest")
    public String jspTest() {
        return "test/test";
    }
}
