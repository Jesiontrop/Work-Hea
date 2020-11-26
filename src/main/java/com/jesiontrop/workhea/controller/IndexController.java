package com.jesiontrop.workhea.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String showIndex() {

        return "index";
    }
}
