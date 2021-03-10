package com.jesiontrop.workhea.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String showIndex(Model model) {

        return "index";
    }
}
