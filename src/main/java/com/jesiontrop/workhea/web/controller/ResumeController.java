package com.jesiontrop.workhea.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @GetMapping
    public String resumeForm() {
        return "resume";
    }
}
