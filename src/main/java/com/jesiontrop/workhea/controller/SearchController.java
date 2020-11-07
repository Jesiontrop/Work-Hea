package com.jesiontrop.workhea.controller;

import com.jesiontrop.workhea.model.Offer;
import com.jesiontrop.workhea.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {

    private final OfferRepository offerRepository;

    @Autowired
    public SearchController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping("/vacancy")
    public String showVacancy(@RequestParam(defaultValue = "") String q, Model model) {
        List<Offer> offers = offerRepository.findAllByVacancyTitleContains(q);
        boolean hasSearchError = false;

        final String searchError = "No results found for \"" + q + "\"";

        if (offers.size() == 0)
            hasSearchError = true;

        model.addAttribute("searchError", searchError);
        model.addAttribute("hasSearchError", hasSearchError);
        model.addAttribute("offers", offers);


        return "/search/vacancy";
    }
}
