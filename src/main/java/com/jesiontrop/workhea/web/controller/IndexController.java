package com.jesiontrop.workhea.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/")
public class IndexController {

    private final String isSearchVacancy = "isSearchVacancy";
    private final String isSearchOrganization = "isSearchOrganization";

    @GetMapping
    public String showIndex(Model model) {
        model.addAttribute("isSearchVacancy", isSearchVacancy);
        model.addAttribute("isSearchOrganization", isSearchOrganization);

        return "index";
    }

    @PostMapping
    public String processSearch(@RequestParam(value = "isSearch") String isSearch,
                                @RequestParam("q") String q) {

        if (isSearch.equals(isSearchVacancy))
            return "redirect:/search/vacancy?q=" + q;

        if (isSearch.equals(isSearchOrganization))
            return "redirect:/search/organization?q=" + q;

        return "redirect:/";
    }
}
